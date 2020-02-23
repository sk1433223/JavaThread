package cn.ybzy.demo;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author sk143
 * @Name：阿康
 * @Description：线程池的使用--ScheduledThreadPoolExecutor接口
 * @Date：2020/2/23 12:47
 */
public class ThreadTest2 {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);
        executor.scheduleAtFixedRate(() -> System.out.println(Thread.currentThread().getName()),0,1, TimeUnit.SECONDS);
    }
}
