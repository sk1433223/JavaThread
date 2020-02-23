package cn.ybzy.demo;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author sk143
 * @Name：阿康
 * @Description：多线程--协同对象发生死锁及解决方式(开放调用锁)
 * @Date：2020/2/19 21:11
 */
public class ThreadTest8 {

}

class Taxi {
    /**
     * 定义位置
     *
     * @location 位置
     * @destination 终点
     */
    private Point location, destination;
    private Dispatcher dispatcher;

    public Taxi(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }


    public synchronized Point getLocation() {
        return location;
    }

    // setLocation 需要Taxi内置锁
    public void setLocation(Point location) {
//        this.location = location;
//        if (location.equals(destination)) //理解为location的位置是destination的5km之内
//            // 调用notifyAvailable()需要Dispatcher内置锁
//            dispatcher.notifyAvailable(this);

        boolean flag;

        // 内置锁保护的范围做了缩小
        synchronized (this) {
            this.location = location;
            flag = location.equals(destination);
        }
        // 当程序运行完毕了此代码块，锁就被释放了

        if (flag) {
            // 在这里地方需要Dispatcher锁，就没关系了，没有锁交替
            // 开放调用
            dispatcher.notifyAvailable(this);
        }

    }
}


class Dispatcher {
    private final Set<Taxi> taxis;
    private final Set<Taxi> availableTaxis;

    public Dispatcher() {
        taxis = new HashSet<>();
        availableTaxis = new HashSet<>();
    }

    // 共享方法避免线程安全问题
    public synchronized void notifyAvailable(Taxi taxi) {
        availableTaxis.add(taxi);
    }

    // 调用getImage()需要Dispatcher内置锁
    public Image getImage() {
//        Image image = new Image();
//        for (Taxi t : availableTaxis) {
//            // 调用getLocation()需要Taxi内置锁
//            image.drawMarker(t.getLocation());
//        }
//        return image;
        Set<Taxi> copy;
        synchronized (this) {
            copy = new HashSet<>(availableTaxis);
        }
        Image image = new Image();
        for (Taxi t : copy) {
            // 调用getLocation()需要Taxi内置锁
            image.drawMarker(t.getLocation());
        }
        return image;
    }
}

/**
 * 地图类
 */
class Image {
    public void drawMarker(Point p) {
    }
}
