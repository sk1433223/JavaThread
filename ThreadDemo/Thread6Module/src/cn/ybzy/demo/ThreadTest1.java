package cn.ybzy.demo;

/**
 * @author sk143
 * @Name：阿康
 * @Description：单例模式
 * @Date：2020/2/21 14:47
 */
public class ThreadTest1 {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> System.out.println(Singleton4.getInstance())).start();
        }
    }
}

// 饿汉(一开始创建实例)
class Singleton1 {
    private static Singleton1 singleton = new Singleton1();

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        return singleton;
    }
}

// 懒汉(调用时创建实例)
class Singleton2 {
    private volatile static Singleton2 singleton = null;

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        // 第一次检查，高并发，很多线程会同时到达这里
        if (singleton == null) {
            // 第二层保护
            synchronized (Singleton2.class) {
                // 第三层保护，配合volatile关键字
                if (singleton == null) {
                    // 除了第一次进来的线程，其他所有线程都进不来了
                    singleton = new Singleton2();
                }
            }
        }
        return singleton;
    }
}

//静态内部类的单例
class Singleton3 {
    private Singleton3() {
    }

    private static class SingleTonHaler {
        private static Singleton3 singleton = new Singleton3();
    }

    public static Singleton3 getInstance() {
        return SingleTonHaler.singleton;
    }
}


/**
 * 枚举的方式
 * 注:枚举类的对象是线程安全的,也是单例的
 */
enum Singleton4 {

    SINGLETON;

    // 可以省略此方法，通过Singleton.INSTANCE进行操作
    public static Singleton4 getInstance() {
        return Singleton4.SINGLETON;
    }
}
