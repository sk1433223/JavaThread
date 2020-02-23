package cn.ybzy.demo;

/**
 * @author sk143
 * @Name：阿康
 * @Description：线程通信--Object类的wait()/notify()/notifyAll()方法
 * @Date：2020/2/21 14:47
 */
public class ThreadTest1 {
    public static void main(String[] args) {
        Thread myt = new MyThead();
        myt.start();
        // 给myt线程打一个中断标记
        myt.interrupt();
        // 判断是否被中断
        System.out.println(myt.isInterrupted());
        System.out.println(myt.isInterrupted());
        System.out.println(myt.isAlive());

        Thread.currentThread().interrupt();  //给当前线程打中断标记

        System.out.println(Thread.interrupted());
        System.out.println(Thread.interrupted());

        //myt.interrupt();

    }

}


class MyThead extends Thread {

    @Override
    public void run() {
        for (int i = 1; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "--" + i);
//            if(Thread.currentThread().isInterrupted()){
//                System.out.println("有没有被打中断标记，进来了就是被打了:" + Thread.currentThread().isInterrupted());
//                System.out.println("isInterrupted，也可以通过：" + Thread.interrupted());
//                System.out.println("isInterrupted，也可以通过：" + Thread.interrupted());
//                return;//结束掉run方法，线程就是中断了
//            }
        }
    }
}
