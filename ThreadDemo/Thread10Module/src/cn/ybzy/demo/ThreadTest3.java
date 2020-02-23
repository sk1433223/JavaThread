package cn.ybzy.demo;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author sk143
 * @Name：阿康
 * @Description：读写锁ReadWriteLock--使用键值对数组实现
 * @Date：2020/2/23 12:47
 */
public class ThreadTest3 {
    public static void main(String[] args) {

    }
}


class CollectionRWLTest{
    //线程共享的集合
    private Map<String,Object> m = new TreeMap<>();
    //获取读写锁
    private ReadWriteLock rw = new ReentrantReadWriteLock();
    //获取写锁
    private Lock w = rw.writeLock();
    //获取读锁
    private Lock r = rw.readLock();

    //通过集合的key获取集合的值
    public Object get(String key){
        r.lock();
        try {
            return m.get(key);
        }finally {
            r.unlock();
        }
    }

    //获取集合里的所有的key
    public String[] allKeys(){
        r.lock();
        try{
            Set<String> rsSet = m.keySet();
            return rsSet.toArray(new String[rsSet.size()]);
        }finally {
            r.unlock();
        }
    }

    //放置进入集合
    public Object put(String key,Object val){
        w.lock();
        try{
            return m.put(key,val);
        }finally {
            w.unlock();
        }
    }

}
