package cn.ybzy.demo;

/**
 * @author sk143
 * @description: 线程安全--Synchronized使用::同步普通方法,和静态方法
 * @data 2020/2/19 15:12
 */
public class ThreadTest3 {
    public static void main(String[] args) {
        SynchronizedThread3 st = new SynchronizedThread3();
        // 创建并开启4个线程来卖100张票
        new Thread(st, "线程1").start();
        new Thread(st, "线程2").start();
        new Thread(st, "线程3").start();
        new Thread(st, "线程4").start();
    }
}

class SynchronizedThread3 implements Runnable {
    /**
     * 定义在这里的属性是所有线程共享的变量数据
     */
    private static int ticketNumber = 100;

    @Override
    public void run() {
        // 子线程做的任务的卖票
        while (true) {
            // 注:同时调用不同锁的同步方法,会有不同锁的线程同时运行
            // 普通方法调用
            // this.sell();
            // 静态方法调用
            SynchronizedThread3.sells();
        }
    }

    /**
     * 普通同步方法的锁： st
     * @Deprecated Java过时元注解
     */
    @Deprecated
    public synchronized void sell(){
        if (ticketNumber > 0) {
            try {
                // 起到放大线程安全问题的作用
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程" + Thread.currentThread().getName() + "卖掉第" + ticketNumber + "号票");
            ticketNumber--;
        } else {
            System.exit(0);
        }
    }

    /**
     * 静态同步方法的锁：SynchronizedThread3.class
     */
    public synchronized static void sells(){
        if (ticketNumber > 0) {
            try {
                // 起到放大线程安全问题的作用
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程" + Thread.currentThread().getName() + "卖掉第" + ticketNumber + "号票");
            ticketNumber--;
        } else {
            System.exit(0);
        }
    }
}