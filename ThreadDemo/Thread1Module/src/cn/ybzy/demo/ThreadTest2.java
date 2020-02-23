package cn.ybzy.demo;

/**
 * @author sk143
 * @description: 多线程实现--实现Runnable接口
 * @data 2020/2/15 12:50
 */
public class ThreadTest2 {
    public static void main(String[] args) {
        SecondThread secondThread = new SecondThread();
        int num = 10;
        for(int j=1;j<=num;j++){
            System.out.println("主线程的名字：" + Thread.currentThread().getName()+ "-" + j);
            if(j == 1){
                // 创建线程参数(实例化线程类,定义Name)

                //创建第一条子线程Thread-0,并且启动该子线程
                new Thread(secondThread,"子线程1").start();
                //创建第二条子线程Thread-1，并启动
                new Thread(secondThread,"子线程2").start();
            }
        }
    }
}

/**
 * 实现Runnable接口
 */
class SecondThread implements Runnable{

    @Override
    public void run() {
        int num = 10;
        // 线程执行体
        for(int i=1;i<=num;i++){
            System.out.println("子线程的名字：" + Thread.currentThread().getName() + "-" + i);
        }
    }
}
