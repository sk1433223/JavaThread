package cn.ybzy.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sk143
 * @Name：阿康
 * @Description：编写一个程序，开启三个线程，这三个线程的ID分别为A、B、C，
 * 每个线程将自己的ID在屏幕上打印10遍，要求输出的结果必须按顺序显示，如:ABCABCABC……依次递归。
 * @Date：2020/2/21 14:47
 */
public class ThreadTest2 {
    public static void main(String[] args) {
        ABCABCThread t = new ABCABCThread();
        // 创建并启动3条线程
        new Thread(() -> {
           for (int i=1;i<11;i++){
               t.loopA(i);
           }
        },"A").start();

        new Thread(() -> {
            for (int i=1;i<11;i++){
                t.loopB(i);
            }
        },"B").start();

        new Thread(() -> {
            for (int i=1;i<11;i++){
                t.loopC(i);
            }
        },"C").start();
    }
}

class ABCABCThread {
    // 起到一会个标记的作用，用它来判断我们执行的是哪个线程。
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    //A
    public void loopA (int totalLoop){
        lock.lock();
        try{
            if(number != 1){
               condition1.await();  //阻塞当前线程
            }
            System.out.println(Thread.currentThread().getName() + "\t" + totalLoop);
            number = 2;
            condition2.signal(); // 唤醒B线程
        }catch (Exception ignored){

        }finally {
            lock.unlock();
        }
    }

    //B
    public void loopB (int totalLoop){
        lock.lock();
        try{
            if(number != 2){
                condition2.await();  //阻塞当前线程
            }
            System.out.println(Thread.currentThread().getName() + "\t" + totalLoop);
            number = 3;
            condition3.signal(); // 唤醒C线程
        }catch (Exception ignored){

        }finally {
            lock.unlock();
        }
    }

    //C
    public void loopC (int totalLoop){
        lock.lock();
        try{
            if(number != 3){
                condition3.await();  //阻塞当前线程
            }
            System.out.println(Thread.currentThread().getName() + "\t" + totalLoop);
            number = 1;
            condition1.signal(); // 唤醒A线程
        }catch (Exception ignored){

        }finally {
            lock.unlock();
        }
    }
}
