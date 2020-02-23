package cn.ybzy.demo;

/**
 * @author sk143
 * @Name：阿康
 * @Description：多线程--造成死锁的多线程
 * @Date：2020/2/19 21:11
 */
public class ThreadTest6_1 {
    public static void main(String[] args) {
        ThreadDemo dl = new ThreadDemo();
        new Thread(dl,"线程1").start();
        new Thread(dl,"线程2").start();

    }
}

/**
 * 线程m1需要suo1和suo2,m2需要suo2和suo1;当同时占有琐时
 * 而不释放则造成死锁
 */
class ThreadDemo implements Runnable {

    final Object suo1 = new Object();
    final Object suo2 = new Object();

    @Override
    public void run() {
        m1();
        m2();
    }

    public void m1 () {
        synchronized (suo1) {
            System.out.println(Thread.currentThread().getName()+"的m1方法拿到了锁1还要锁2");
            synchronized (suo2) {
                System.out.println(Thread.currentThread().getName()+"的m1方法拿到了锁1和要锁2");
            }
        }
    }

    public void m2 () {
        synchronized (suo2) {
            System.out.println(Thread.currentThread().getName()+"的m2方法拿到了锁2还要锁1");
            synchronized (suo1) {
                System.out.println(Thread.currentThread().getName()+"的m2方法拿到了锁2和要锁1");
            }
        }

    }
}
