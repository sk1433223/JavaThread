package cn.ybzy.demo;

/**
 * @author sk143
 * @description: 线程安全--ThreadLocal管理变量使用:
 * @data 2020/2/19 20:46
 */
public class ThreadTest5 {
    public static void main(String[] args) {
        SynchronizedThread5 st = new SynchronizedThread5();
        // 创建并开启4个线程来卖100张票
        new Thread(st, "线程1").start();
        new Thread(st, "线程2").start();
        new Thread(st, "线程3").start();
        new Thread(st, "线程4").start();
    }
}

class SynchronizedThread5 implements Runnable {
    /**
     * 定义在这里的属性是所有线程共享的变量数据
     */
    ThreadLocal<Integer> ticketNumber = ThreadLocal.withInitial(() -> {
        // ticketNumber的初始值(票数)
        return 10;
    });

    @Override
    public void run() {
        //子线程做的任务的卖票
        while (true) {
            if (ticketNumber.get() > 0) {
                try {
                    // 起到放大线程安全问题的作用
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + Thread.currentThread().getName() + "卖掉第" + ticketNumber.get() + "号票");
                ticketNumber.set(ticketNumber.get()-1);
            } else {
                break;
            }
        }
    }
}