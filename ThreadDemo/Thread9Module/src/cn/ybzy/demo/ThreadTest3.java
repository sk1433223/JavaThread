package cn.ybzy.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author sk143
 * @Name：阿康
 * @Description：线程池的使用--Executors接口
 * 注:不建议这种创建方式,有风险
 * @Date：2020/2/23 12:47
 */
public class ThreadTest3 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        service.submit(() -> System.out.println(Thread.currentThread().getName()));
        service.shutdown();
    }
}
