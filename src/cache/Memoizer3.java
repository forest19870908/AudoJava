package cache;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ZSL on 2017/6/19.
 */
public class Memoizer3<A,V> implements Computable<A,V> {
    private final Map<A, Future<V>> cache=new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A,V> c;
    private AtomicInteger count=new AtomicInteger(0);
    public Memoizer3(Computable<A,V> c){
        this.c=c;
    }
    @Override
    public Map getCache() {
        return cache;
    }

    @Override
    public Integer getCount() {
        return count.get();
    }

    @Override
    public V computable(A arg) throws InterruptedException {
        Future<V> result=cache.get(arg);
        if(result==null){
            Callable<V> cal=new Callable<V>() {
                @Override
                public V call() throws Exception {
                    count.incrementAndGet();
                    return c.computable(arg);
                }
            };
            FutureTask<V> ft=new FutureTask<V>(cal);
            result=ft;
            cache.put(arg,result);
            ft.run();//在这里将调用c.computable
        }
        try {
            return result.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
