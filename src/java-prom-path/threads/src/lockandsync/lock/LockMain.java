package lockandsync.lock;

import java.util.concurrent.locks.ReentrantLock;

public class LockMain {
    public static void main(String[] args) {
        var lock = new ReentrantLock();
        var table = new LockTable(lock);

        new Thread(() -> table.print(5)).start();
        new Thread(() -> table.print(1000)).start();

    }

}