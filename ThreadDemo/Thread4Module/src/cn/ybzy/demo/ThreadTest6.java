package cn.ybzy.demo;

import java.util.concurrent.CyclicBarrier;

/**
 * @author sk143
 * @Name：阿康
 * @Description：Java并发包(用于同步一批线程的行为)--CyclicBarrier(回环栅栏)
 * @Date：2020/2/21 14:47
 */
public class ThreadTest6 {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        CyclicBarrierThread cbt = new CyclicBarrierThread(cyclicBarrier);

        for(int i=1;i<4;i++){
            new Thread(cbt,"张"+i).start();
        }
    }
}

class CyclicBarrierThread implements Runnable{
    private CyclicBarrier cyclicBarrier;
    public CyclicBarrierThread(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((long)(Math.random()*10000));

            System.out.println(Thread.currentThread().getName() + "到了第一个同步点" +
                    "除了他以为，已经有多少个人（线程）到达了"  +
                    cyclicBarrier.getNumberWaiting());
            cyclicBarrier.await();//就是第一个同步点

            Thread.sleep((long)(Math.random()*10000));


            System.out.println(Thread.currentThread().getName() + "到了第二个同步点" +
                    "除了他以为，已经有多少个人（线程）到达了"  +
                    cyclicBarrier.getNumberWaiting());
            cyclicBarrier.await();//就是第二个同步点

            Thread.sleep((long)(Math.random()*10000));

            System.out.println(Thread.currentThread().getName() + "到了第二个同步点" +
                    "除了他以为，已经有多少个人（线程）到达了"  +
                    cyclicBarrier.getNumberWaiting());
            cyclicBarrier.await();//就是第三个同步点
            System.out.println("大家一起集合回家了！");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
