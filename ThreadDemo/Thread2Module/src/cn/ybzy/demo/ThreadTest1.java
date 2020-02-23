package cn.ybzy.demo;

/**
 * @author sk143
 * @description: 多线程--Thread.join()用法
 * @data 2020/2/15 12:50
 */
public class ThreadTest1 {
    public static void main(String[] args) {
        // main方法是jvm进程的默认的主线程，main
        System.out.println("主线程的名字：" + Thread.currentThread().getName());
        // 在main线程中，创建一个新的线程，并且启动
        Thread t1 = new MyThread("子线程t1");
        t1.start();
        try {
            t1.join();
            // 调用了t1的join方法，main线程就会阻塞，等t1线程执行完毕后，才会到主线程继续执行
            // 参数的意义是main线程等待，阻塞的时间毫秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行到这里的时候，一定t1线程已经结束了！");
    }
}

class MyThread extends Thread{
    public MyThread(String name){
        super(name);
    }

    @Override
    public void run() {
        int num = 1001;
        for(int i=1;i<num;i++){
            System.out.println(this.getName() + ":" + i);
        }
    }
}
