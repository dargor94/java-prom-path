package lockandsync.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

public class LockTable {
    private final AtomicInteger counter = new AtomicInteger(0);
    private final Lock lock;

    public LockTable(Lock lock) {
        this.lock = lock;
    }

    public void print(int n) {
        while (counter.intValue() < 6) {
            if (lock.tryLock()) {
                for (int i = 1; i <= 10; i++) {
                    System.out.println(n * i);
                }
                counter.getAndIncrement();
                lock.unlock();

            }
        }
    }
}
