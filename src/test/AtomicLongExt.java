package test;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongExt extends AtomicLong {
	public final long incrementAndGet2() {
        return incrementAndGet();
    }
}
