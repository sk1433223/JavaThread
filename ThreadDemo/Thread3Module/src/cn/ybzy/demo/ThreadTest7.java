package cn.ybzy.demo;

/**
 * @author sk143
 * @Name：阿康
 * @Description：多线程--避免死锁的方法(固定加锁顺序)
 * @Date：2020/2/19 21:11
 */
public class ThreadTest7 {
    public static void main(String[] args) {
        DeadLockDemo2 dl = new DeadLockDemo2();
        new Thread(dl, "线程1").start();
        new Thread(dl, "线程2").start();
    }
}

class DeadLockDemo2 implements Runnable {

    /**
     * 初始化两个账户
     */
    Account a = new Account("A", 1000);
    Account b = new Account("B", 1000);

    final private Object jiaSuo = new Object();


    @Override
    public void run() {
        transferMoney(a, b, 100);
        transferMoney(b, a, 100);
    }


    public void transferMoney(Account fromAccount, Account toAccount, int money) {
        int faHash = System.identityHashCode(fromAccount);
        int taHash = System.identityHashCode(toAccount);

        class Helper {
            public void transfer() {
                System.out.println("线程" + Thread.currentThread().getName() + "得到锁" + toAccount.getName());
                if (fromAccount.getMoney() < money) {
                    System.out.println("转出账号的余额不足！");
                } else {

                    // 进行转账
                    fromAccount.setMoney(fromAccount.getMoney() - money);
                    toAccount.setMoney(toAccount.getMoney() + money);

                    // 执行完转账后，我们输出一下两个账号的余额
                    System.out.println("转账后：" + fromAccount.getName() + "有：" + fromAccount.getMoney());
                    System.out.println("转账后：" + toAccount.getName() + "有：" + toAccount.getMoney());
                }
            }
        }


        if (faHash > taHash) {
            synchronized (fromAccount) {
                System.out.println("线程" + Thread.currentThread().getName() + "得到锁" + fromAccount.getName());
                synchronized (toAccount) {
                    new Helper().transfer();
                }
            }
        } else if (faHash < taHash) {
            synchronized (toAccount) {
                System.out.println("线程" + Thread.currentThread().getName() + "得到锁" + fromAccount.getName());
                synchronized (fromAccount) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (jiaSuo) {
                synchronized (fromAccount) {
                    System.out.println("线程" + Thread.currentThread().getName() + "得到锁" + fromAccount.getName());
                    synchronized (toAccount) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }

}

/**
 * 定义账户类
 */
class Account {
    private String name;
    private int money;

    /**
     * @param name  账户名
     * @param money 账户金额
     */
    public Account(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
