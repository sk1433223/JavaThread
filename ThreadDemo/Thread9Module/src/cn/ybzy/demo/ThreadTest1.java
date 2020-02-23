package cn.ybzy.demo;

import java.util.concurrent.*;

/**
 * @author sk143
 * @Name：阿康
 * @Description：线程池的使用--ThreadPoolExecutor接口
 * @Date：2020/2/21 14:47
 */
public class ThreadTest1 {
    public static void main(String[] args) {
        //设置核心池大小
        int corePoolSize = 5;
        //设置线程池最大能接受多少线程
        int maximumPoolSize = 10;
        //当前线程数大于corePoolSize、小于maximumPoolSize时，超出corePoolSize的线程数的生命周期
        long keepActiveTime = 200;
        //设置时间单位，秒
        TimeUnit timeUnit = TimeUnit.SECONDS;
        //设置线程池缓存队列的排队策略为FIFO，并且指定缓存队列大小为5
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(5);
        //根据前面我们的设置参数，来创建线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepActiveTime, timeUnit, workQueue);

        //创建15个任务
        for(int i=1;i<=15;i++){
            PoolThread pt = new PoolThread(i);
            executor.execute(pt);
            //获取线程池中线程的相应参数
            System.out.println("线程池中线程数目：" +executor.getPoolSize() + "，队列中等待执行的任务数目："+executor.getQueue().size() + "，已执行完的任务数目："+executor.getCompletedTaskCount());
        }

        //使用线程池，一定要注意，用完后，要自己关掉线程池
        executor.shutdown();
    }
}

class PoolThread implements Runnable{
    //传入一个任务编号
    private int num;
    public PoolThread(int num){
        this.num = num;
    }


    @Override
    public void run() {
        System.out.println("任务" + num + "开始执行");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("任务" + num + "执行完毕");
    }
}
