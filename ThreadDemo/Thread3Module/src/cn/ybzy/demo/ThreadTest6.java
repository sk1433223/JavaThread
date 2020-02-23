package cn.ybzy.demo;

/**
 * @author sk143
 * @description: 线程同步--死锁问题:
 * @data 2020/2/19 21:02
 */
public class ThreadTest6 {
    public static void main(String[] args) {
        DeadLockDemo1 dl = new DeadLockDemo1();
        new Thread(dl,"线程1").start();
        new Thread(dl,"线程2").start();
    }
}

class DeadLockDemo1 implements Runnable{


    /**
     * 定义两把锁
     */
    final private  Object suo1 = new Object();
    final private Object suo2 = new Object();

    /**
     * 加一把所
     */
    final private  Object jiaSou = new Object();
    // 在极少的情况下回用到，任然要准备

    /**
     * 拿到原来的两把锁的hashcode
     */
    int suo1HashCode = System.identityHashCode(suo1);
    int suo2HashCode = System.identityHashCode(suo2);

    // 定义一个规则：加所的顺序和hash值的大小关联起来

    @Override
    public void run() {
        m1();
        m2();
    }

    public void m1(){
        if(suo1HashCode > suo2HashCode){
            //拿到锁1
            synchronized (suo1){
                System.out.println(Thread.currentThread().getName() + "的m1方法拿到了锁1还要锁2" );
                synchronized (suo2){
                    System.out.println(Thread.currentThread().getName() + "的m1方法拿到了锁1和锁2" );
                }
            }
        }else if(suo1HashCode < suo2HashCode){
            //先拿到锁2，和m1方法获取锁的顺序刚好相反
            synchronized (suo2){
                System.out.println(Thread.currentThread().getName() + "的m2方法拿到了锁2还要锁1" );
                synchronized (suo1){
                    System.out.println(Thread.currentThread().getName() + "的m2方法拿到了锁2和锁1" );
                }
            }
        }else {
            synchronized (jiaSou){
                //拿到锁1
                synchronized (suo1){
                    System.out.println(Thread.currentThread().getName() + "的m1方法拿到了锁1还要锁2" );
                    synchronized (suo2){
                        System.out.println(Thread.currentThread().getName() + "的m1方法拿到了锁1和锁2" );
                    }
                }
            }
        }
    }

    public void m2(){
        if(suo1HashCode > suo2HashCode){
            //拿到锁1
            synchronized (suo1){
                System.out.println(Thread.currentThread().getName() + "的m1方法拿到了锁1还要锁2" );
                synchronized (suo2){
                    System.out.println(Thread.currentThread().getName() + "的m1方法拿到了锁1和锁2" );
                }
            }
        }else if(suo1HashCode < suo2HashCode){
            //先拿到锁2，和m1方法获取锁的顺序刚好相反
            synchronized (suo2){
                System.out.println(Thread.currentThread().getName() + "的m2方法拿到了锁2还要锁1" );
                synchronized (suo1){
                    System.out.println(Thread.currentThread().getName() + "的m2方法拿到了锁2和锁1" );
                }
            }
        }else {
            synchronized (jiaSou){
                //拿到锁1
                synchronized (suo1){
                    System.out.println(Thread.currentThread().getName() + "的m1方法拿到了锁1还要锁2" );
                    synchronized (suo2){
                        System.out.println(Thread.currentThread().getName() + "的m1方法拿到了锁1和锁2" );
                    }
                }
            }
        }
    }

}
