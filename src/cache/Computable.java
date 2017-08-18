package cache;

import java.util.Map;

/**
 * Created by ZSL on 2017/6/19.
 */
public interface Computable<A,V> {
    V computable(A args) throws InterruptedException;
    Map getCache();
    Integer getCount();//计算次数
}
