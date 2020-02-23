package cn.ybzy.demo;

/**
 * @author sk143
 * @description: 多线程实现--匿名内部类-实现Runnable接口
 * @data 2020/2/15 12:50
 */
public class ThreadTest3 {
    public static void main(String[] args) {
        int num = 10;
        for(int j=1;j<=num;j++){
            System.out.println("主线程的名字：" + Thread.currentThread().getName()+ "-" + j);
            if(j == 1){
                // 创建第一条子线程Thread-0,并且启动该子线程
                // () ->lambda表达式
                new Thread(() -> {
                    //线程执行体
                    for(int i=1;i<=num;i++){
                        System.out.println("子线程的名字：" + Thread.currentThread().getName() + "-" + i);
                    }
                }, "子线程1").start();

                //创建第二条子线程Thread-1，并启动
                new Thread(() -> {
                    //线程执行体
                    for(int i=1;i<=num;i++){
                        System.out.println("子线程的名字：" + Thread.currentThread().getName() + "-" + i);
                    }
                }, "子线程2").start();
            }
        }
    }
}
