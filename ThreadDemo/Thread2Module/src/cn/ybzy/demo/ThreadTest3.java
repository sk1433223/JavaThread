package cn.ybzy.demo;

/**
 * @author sk143
 * @description: 多线程--Thread.sleep()用法
 * 注:sleep(),使当前线程沉睡
 * @data 2020/2/15 12:50
 */
public class ThreadTest3 {
    public static void main(String[] args) {
        new Thread(new MyThread1(),"子线程").start();
        int num = 4;
        for(int i=1;i<num;i++){
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}

class MyThread1 implements Runnable{

    @Override
    public void run() {
        try {
            // 最佳写的位置是run方法里
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int num = 4;
        for(int i=1;i<num;i++){
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}
