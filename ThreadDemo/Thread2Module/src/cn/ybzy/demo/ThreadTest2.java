package cn.ybzy.demo;

/**
 * @author sk143
 * @description: 多线程--后台线程
 * @data 2020/2/15 12:50
 */
public class ThreadTest2 {
    public static void main(String[] args) {
        Thread daemonThread = new DaemonThread("后台子线程");
        // 设置daemonThread线程为后台线程的关键语句
        daemonThread.setDaemon(true);
        daemonThread.start();
        try {
            // main线程沉睡5s
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //5s后，main线程会回来，执行主线程里的任务代码
        int num = 101;
        for(int i=1;i<num;i++){
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
        new ForegroundThread("前台子线程").start();
    }
}

class DaemonThread extends Thread{
    public DaemonThread(String name){
        super(name);
    }

    @Override
    public void run() {
        int num = 101;
        for(int i=1;i<num;i++){
            try {
                // 让该线程沉睡0.5s
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName() + ":" + i);
        }
    }
}

class ForegroundThread extends Thread{
    public ForegroundThread(String name){
        super(name);
    }

    @Override
    public void run() {
        int num = 101;
        for(int i=1;i<num;i++){
            try {
                // 每次输出的时候，沉睡1s
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName() + ":" + i);
        }
    }

}
