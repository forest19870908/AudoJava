package cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ZSL on 2017/6/19.
 */
public class Memoizer2<A,V> implements Computable<A,V> {
    private final Map<A, V> cache=new ConcurrentHashMap<A, V>();
    private final Computable<A,V> c;
    private AtomicInteger count=new AtomicInteger(0);
    @Override
    public Map getCache() {
        return cache;
    }
    public Memoizer2(Computable<A,V> c){
        this.c=c;
    }

    @Override
    public Integer getCount() {
        return count.get();
    }

    @Override
    public V computable(A arg) throws InterruptedException {
        V result=cache.get(arg);
        if(result==null){
            result=c.computable(arg);
            count.incrementAndGet();
            cache.put(arg,result);
        }
        return result;
    }
}
