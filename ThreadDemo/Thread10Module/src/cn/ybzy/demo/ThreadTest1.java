package cn.ybzy.demo;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author sk143
 * @Name：阿康
 * @Description：读写锁ReadWriteLock--
 * @Date：2020/2/23 12:47
 */
public class ThreadTest1 {
    public static void main(String[] args) {
        ReadWriteLockTest rwlt = new ReadWriteLockTest();
        //开启10条线程去读
        for(int i=1;i<=10;i++){
            new Thread(() -> {
                rwlt.get();//读取共享资源
            },"A").start();
        }



        //开启一条线程来写
        new Thread(() -> rwlt.set(12),"B").start();
    }
}

class ReadWriteLockTest {
    // 定义一个线程间会共享的数据
    private int num = 0;

    // 用到读写锁
    ReadWriteLock rwl = new ReentrantReadWriteLock();

    // 定义两个方法
    public void get(){
        rwl.readLock().lock(); // 使用读锁
        try {
            System.out.println(Thread.currentThread().getName() + ":" + num);
        }finally {
           rwl.readLock().unlock(); // 释放读锁
        }
    }

    public void set(int num){
        // 用到写锁
        rwl.writeLock().lock();
        try {
            this.num = num;
        }finally {
            rwl.writeLock().unlock();// 释放写锁
        }
    }






}
