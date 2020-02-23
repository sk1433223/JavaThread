package cn.ybzy.demo;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author sk143
 * @Name：阿康
 * @Description：多线程--JUC同步容器
 * 注:JUC里的ConcurrentHashMap优于同步的HashMap
 *    JUC里的ConcurrentSkipListMap优于同步的TreeMap
 *    JUC里的CopyOnWriteArrayList优于同步的ArrayList
 *    JUC里的CopyOnWriteArraySet优于同步的ArraySet
 * @Date：2020/2/19 21:11
 */
public class ThreadTest3 {
    public static void main(String[] args) {
        CollectionsThread ct = new CollectionsThread();
        new Thread(ct,"1").start();
        new Thread(ct,"2").start();
        new Thread(ct,"3").start();
        new Thread(ct,"4").start();

    }
}

class CollectionsThread implements Runnable{
    // 线程共享变量
    // private static List<String> list = Collections.synchronizedList(new ArrayList<>());
    // ArrayList==等价==CopyOnWriteArrayList
    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    static {
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
    }

    @Override
    public void run() {
        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
            // 上面读，下面马上又写，一边读一遍写的操作就是集合的复合操作
            list.add("eee");
        }
    }
}
