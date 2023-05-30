package lockandsync.lock;

import java.util.concurrent.locks.ReentrantLock;

public class LockMain {
    public static void main(String[] args) {
        var lock = new ReentrantLock(true);
        var table = new LockTable(lock);

        var t1 = new Thread(() -> table.print(5));
        var t2 = new Thread(() -> table.print(1000));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}