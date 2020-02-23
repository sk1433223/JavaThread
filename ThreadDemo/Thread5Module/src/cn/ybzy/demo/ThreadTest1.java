package cn.ybzy.demo;

/**
 * @author sk143
 * @Name：阿康
 * @Description：synchronized的理解
 * @Date：2020/2/21 14:47
 */
public class ThreadTest1 {
    public static void main(String[] args) {
        Number numberOne = new Number();
        Number numberTwo = new Number();

        new Thread(Number::getOne).start();

        new Thread(Number::getTwo).start();

    }
}

class Number {
    public static synchronized void getOne() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }

    public static synchronized void getTwo() {
        System.out.println("two");
    }

}

