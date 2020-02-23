package cn.ybzy.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sk143
 * @Name：阿康
 * @Description：线程通信--condition类的wait()/signal()/signalAll()方法
 * 注:wait()让当前线程阻塞,signal()唤醒一个正在等待这个同步锁对象的线程,signalAll唤醒所有正在等待这个同步锁对象的线程
 * @Date：2020/2/21 14:47
 */
public class ThreadTest2 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        P1 p = new P1(lock,condition);
        C1 c = new C1(lock,condition);
        p.start();
        c.start();
    }
}


//定义一个类，模拟生产者和消费者之间的缓冲区
class ValueObject1 {
    //模拟的缓冲区
    public static String value = "";
}

//实现生产者的线程类
class P1 extends Thread {

    private Lock lock;
    private Condition condition;

    public P1(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                //作为生产者的基本功能，生产数据
                if (!"".equals(ValueObject.value)) {
                    //缓存区里有数据的情况下，生产者不生产数据的
                    condition.await();//让当前线程阻塞，把当前线程的同步锁释放出去
                }
                //这时我们才真正的开始生产数据
                System.out.println("缓存区没的数据，这时我们才真正的开始生产数据");
                ValueObject.value = String.valueOf(System.currentTimeMillis());
                //这个生产数据完毕，放进了缓冲区了，通知消费者线程，消费数据
                condition.signal();
            } catch (Exception ignored) {

            } finally {
                lock.unlock();
            }
        }

    }
}

//实现消费者线程类
class C1 extends Thread {

    private Lock lock;
    private Condition condition;

    public C1(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                //判断缓冲区里有数据没的
                if ("".equals(ValueObject.value)) {
                    condition.await();
                }
                System.out.println("缓冲区里有数据，现在把数据消费掉！");
                ValueObject.value = "";
                //消费者把数据消费后，通知生产者，生产数据
                condition.signal();

            } catch (Exception ignored) {

            } finally {
                lock.unlock();
            }
        }

    }
}
