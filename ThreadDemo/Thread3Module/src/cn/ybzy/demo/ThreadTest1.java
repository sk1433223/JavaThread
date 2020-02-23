package cn.ybzy.demo;

/**
 * @author sk143
 * @description: 线程安全--了解线程安全
 * @data 2020/2/19 15:12
 */
public class ThreadTest1 {
    public static void main(String[] args) {
        SynchronizedThread st = new SynchronizedThread();
        //创建并开启4个线程来卖100张票
        new Thread(st,"线程1").start();
        new Thread(st,"线程2").start();
        new Thread(st,"线程3").start();
        new Thread(st,"线程4").start();
    }
}

class SynchronizedThread implements Runnable{
    /**
     * 定义在这里的属性是所有线程共享的变量数据
     */
    private int ticketNumber = 100;

    @Override
    public void run() {
        //子线程做的任务的卖票
        while(true){
            if(ticketNumber > 0){
                System.out.println("线程" + Thread.currentThread().getName() + "卖掉第" + ticketNumber + "号票");
                ticketNumber--;
            }else {
               break;
            }
        }
    }
}
