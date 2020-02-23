package cn.ybzy.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sk143
 * @Name：阿康
 * @Description：多线程--避免死锁(定时锁)
 * @Date：2020/2/20 14:11
 */
public class ThreadTest9 {
    public static void main(String[] args) {
        System.out.println("开始");
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            try {
                if(lock.tryLock()){
                    System.out.println("获取到了锁");
                    try {
                        int num = 6;
                        for (int i=1;i<num;i++){
                            System.out.println(Thread.currentThread().getName() + "--" + i);
                        }
                    }catch (Exception ignored){

                    }finally {
                        // 释放锁
                       lock.unlock();
                    }
                }else{
                    System.out.println("没获取到锁");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "线程1").start();

        new Thread(() -> {
            try {
                if(lock.tryLock()){
                    System.out.println("获取到了锁");
                    try {
                        int num = 6;
                        for (int i=1;i<num;i++){
                            System.out.println(Thread.currentThread().getName() + "--" + i);
                        }
                    }catch (Exception ignored){

                    }finally {
                        lock.unlock();
                    }
                }else{
                    System.out.println("没获取到锁");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "线程2").start();

        System.out.println("结束");
    }
}
