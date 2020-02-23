package cn.ybzy.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author sk143
 * @description: 多线程实现--匿名内部类-实现Runnable接口
 * @data 2020/2/18 16:30
 */
public class ThreadTest4 {
    public static void main(String[] args) {
        ThirdThread thirdThread = new ThirdThread();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(thirdThread);
        int num = 10;
        for (int j = 1; j <= num; j++) {
            System.out.println("主线程的名字：" + Thread.currentThread().getName() + "-" + j);
            if (j == 1) {
                //创建第一条子线程1,并且启动该子线程
                new Thread(futureTask, "子线程1").start();
            }
        }
        try {
            System.out.println("子线程返回的值是：" + futureTask.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ThirdThread implements Callable {
    @Override
    public Object call() {
        int i = 1;
        //线程执行体
        int num = 10;
        for (; i <= num; i++) {
            System.out.println("子线程的名字：" + Thread.currentThread().getName() + "-" + i);
        }
        return i;
    }
}
