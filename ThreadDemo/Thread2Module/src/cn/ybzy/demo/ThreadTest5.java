package cn.ybzy.demo;
/**
 * @author sk143
 * @description: 多线程--线程优先级(Thread.setPriority())和线程让步Thread.yield
 * @data 2020/2/15 12:50
 */
public class ThreadTest5 {
    public static void main(String[] args) {
        System.out.println("main线程的默认优先级别：" + Thread.currentThread().getPriority());
        Thread t1 = new PriorityThread("低级");
        t1.setPriority(Thread.MIN_PRIORITY);
        t1.start();

        Thread t2 = new PriorityThread("高级别");
        t2.setPriority(Thread.MAX_PRIORITY);
        t2.start();

        // new PriorityThread("线程1").start();
        // new PriorityThread("线程2").start();

    }
}

class PriorityThread extends Thread{
    public  PriorityThread(String name){
        super(name);
    }

    @Override
    public void run() {
        int num = 51;
        for(int i=1;i<num;i++){
            System.out.println(this.getName() + ":" + this.getPriority() + ":" + i);
            if(i%4 == 0){
                Thread.yield();  // 循环变量能整除4的，让线程让步
            }
        }
    }
}
