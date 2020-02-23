package cn.ybzy.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sk143
 * @Name：阿康
 * @Description：多线程--volatile使用(原子性操作问题)
 * 注:使用AtomicInteger类--对volatile进行了封装
 * @Date：2020/2/19 21:11
 */
public class ThreadTest2 {
    public static void main(String[] args) {
        AtomicThread at = new AtomicThread();
        int num = 10;
        // 创建10条子线程并启动
        for(int i=0;i<num;i++){
            new Thread(at,"线程"+i).start();
        }
    }
}
class AtomicThread implements  Runnable{
    // 是线程共享变量
    // private volatile int serialNum = 0;// (会有多线程原子性不安全问题)
    private AtomicInteger serialNum = new AtomicInteger(0);

    public int getSerialNum() {
        return serialNum.getAndIncrement();
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("显示serialNum完后，然它自增一：" + getSerialNum());
    }
}
