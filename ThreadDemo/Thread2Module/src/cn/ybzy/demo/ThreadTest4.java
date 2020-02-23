package cn.ybzy.demo;

import java.util.concurrent.TimeUnit;
/**
 * @author sk143
 * @description: 多线程--sleep用法
 * @data 2020/2/15 12:50
 */
public class ThreadTest4 {
    public static void main(String[] args) {
        MyThread2 t = new MyThread2();
        t.start();
        try {
            // t.sleep(3000); //是用MyThread2来调用sleep方法，或用t来调用sleep方法
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int num = 4;
        for(int i=1;i<num;i++){
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}

class MyThread2 extends Thread{
    @Override
    public void run() {
        int num = 4;
        for(int i=1;i<num;i++){
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}
