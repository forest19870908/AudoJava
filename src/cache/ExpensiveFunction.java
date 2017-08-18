package cache;

import java.math.BigInteger;
import java.util.Map;

/**
 * Created by ZSL on 2017/6/19.
 */
public class ExpensiveFunction implements Computable<String,BigInteger> {
    @Override
    public BigInteger computable(String args) throws InterruptedException {
        Thread.sleep(3000);//3秒
        return new BigInteger(args);
    }

    @Override
    public Map getCache() {
        return null;
    }

    @Override
    public Integer getCount() {
        return null;
    }
}
