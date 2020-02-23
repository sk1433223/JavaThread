package cn.ybzy.demo;

/**
 * @author sk143
 * @description: 多线程实现--继承Thread类
 * @data 2020/2/15 12:50
 */
public class ThreadTest {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        int num = 10;
        for(int j = 1;j <= num;j++){
            // Thread.currentThread().getName():拿到当前线程的名字
            System.out.println("主线程的名字：" + Thread.currentThread().getName()+ "-" + j);
            if(j == 5){
                //创建第一条子线程Thread-0
                new FirstThread().start(); //并且启动该子线程
                //创建第二条子线程Thread-1，并启动
                new FirstThread().start();
            }
        }
    }

}

/**
 * 继承Thread类
 */
class FirstThread extends Thread{
    @Override
    public void run() {
        super.run();
        //线程的执行体，此线程预实现的任务代码
        int num = 10;
        for(int i=1;i<=num;i++){
            System.out.println("子线程的名字：" + this.getName() + "-" + i);
        }
    }
}
