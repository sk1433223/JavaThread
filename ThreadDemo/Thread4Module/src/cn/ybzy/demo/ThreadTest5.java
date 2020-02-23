package cn.ybzy.demo;

import java.util.concurrent.Semaphore;

/**
 * @author sk143
 * @Name：阿康
 * @Description：Java并发包(用于同步一批线程的行为)--Semaphore(可以控同时访问的线程个数)
 * @Date：2020/2/21 14:47
 */
public class ThreadTest5 {
    public static void main(String[] args) {
        // 控制的线程数(当聚集到5个时进行统一释放)
        Semaphore semaphore = new Semaphore(5);
        SemaphoreThread spt = new SemaphoreThread(semaphore);

        // 创建的线程个数
        int num = 9;
        for (int i = 1; i < num; i++) {
            new Thread(spt, "" + i).start();
        }
    }
}

class SemaphoreThread implements Runnable {
    private Semaphore semaphore;

    public SemaphoreThread(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            // 第二句代码开始才是，线程体里的具体的任务代码
            System.out.println("工人" + Thread.currentThread().getName() + "占用一个机器在生产...");
            Thread.sleep(2000);
            System.out.println("工人" + Thread.currentThread().getName() + "释放出机器");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
