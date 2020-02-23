package cn.ybzy.demo;

import java.util.concurrent.CountDownLatch;

/**
 * @author sk143
 * @Name：阿康
 * @Description：Java并发包(用于同步一批线程的行为)--CountDownLatch(计数器闭锁)
 * @Date：2020/2/21 14:47
 */
public class ThreadTest4 {
    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(50);
        CountDownLatchThread cdt = new CountDownLatchThread(latch);

        long start = System.currentTimeMillis();
        int num = 51;
        for (int i = 1; i < num; i++) {
            new Thread(cdt, "线程" + i).start();
        }

        try {
            // 这个方法在main线程里调用，阻塞的就是main线程，什么时候放开，初始化50的计数器倒计时为0的时候
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("50个子线运行了多长时间：" + ((end - start) / 1000) + "秒");
    }
}

class CountDownLatchThread implements Runnable {
    private CountDownLatch latch;

    public CountDownLatchThread(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            int num = 50000;
            for (int i = 0; i < num; i++) {
                if (i % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + "打印0-50000直接的偶数：" + i);
                }
            }
        } finally {
            // 让CountDownLatch里的计数器-1
            latch.countDown();
        }
    }
}
