package cn.ybzy.demo;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author sk143
 * @description:
 * @data 2020/2/15 12:50
 */
public class ThreadTest2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //大任务是求300元素的数组里的，成员之和
        //构造一个数组
        int[] arr = new int[300];
        Random random = new Random();
        int total = 0;
        for(int i=0;i<arr.length;i++){
            int tmp = random.nextInt(20);
            arr[i] = tmp;
            total += arr[i];
        }
        System.out.println("构造的数据的总和：" + total);

        //用工具来来实现求和
        SumTask st = new SumTask(arr,0,arr.length-1);
        ForkJoinPool pool = ForkJoinPool.commonPool();
        Future<Integer> future = pool.submit(st);
        System.out.println("最总的结果：" + future.get());
        pool.shutdown();
    }
}

class SumTask extends RecursiveTask<Integer>{
    private static final int THRESHOLD = 20;
    private int[] arr;
    /**
     * 要加总的数据元素在数组中的下标，开始元素
     */
    private int start;
    /**
     * 要加总的数据的元素在数组中的下标，结束元素
     */
    private int end;

    public SumTask(int[] arr,int start,int end){
        this.arr = arr;
        this.start = start;
        this.end = end;
    }


    @Override
    protected Integer compute() {
        int sum = 0;
        if(end-start <= THRESHOLD){
            for(int i=start;i<=end;i++){
                sum += arr[i];
            }
            return sum;
        }else{
            int middle = (start + end)/2;
            SumTask left = new SumTask(arr,start,middle);
            SumTask right = new SumTask(arr,middle+1,end);
            //让左右两个较小的任务，并发地执行
            left.fork();
            right.fork();
            //要把结果进行合并
            return left.join() + right.join();
        }
    }
}
