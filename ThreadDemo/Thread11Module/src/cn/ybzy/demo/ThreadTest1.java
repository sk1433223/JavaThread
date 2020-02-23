package cn.ybzy.demo;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class ThreadTest1 {
    public static void main(String[] args) {
        //创建大任务
        PrintTask pt = new PrintTask(1,300);
        //获取工具类
        ForkJoinPool fjp = new ForkJoinPool();//构造的参数，可以自己传入线程数量，默认空的，默认值会自动设置为计算机的cup个数
        fjp.submit(pt);
        try {
            fjp.awaitTermination(2, TimeUnit.SECONDS); //让线程池的关闭，等待2秒。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fjp.shutdown();//关闭线程池
    }
}

//大任务：打印1-300之间的整数
class PrintTask extends RecursiveAction{
    //定义一个极限值
    private static  final int THRESHOLD = 30; //单独的一条线程，一次最多打印30个数

    private int start; //小任务打印的开头的数
    private int end ;//小任务打印的尾数

    public PrintTask(int start,int end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() { //主要作用就实现拆分任务，使用递归的方式，把大任务一级一级的拆分成小任务
        if((end-start)<=THRESHOLD){
            for(int i=start;i<=end;i++){
                System.out.println(Thread.currentThread().getName() + "的i值：" + i);
            }
        }else{
           int middle = (end+start)/2;
           PrintTask left = new PrintTask(start,middle);
           PrintTask right = new PrintTask(middle+1,end);
           //上面的两行代码就是把大任务，对半拆分成了小任务，使用递归
            //拆分出来的小任务，并发执行
            left.fork();
            right.fork();
        }
    }
}
