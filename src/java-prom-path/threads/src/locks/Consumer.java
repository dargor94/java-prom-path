package locks;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Consumer implements Runnable {

    private final Lock lock;
    private final List<Integer> list;
    private final Condition condition;

    public Consumer(Lock reentrantLock, List<Integer> list, Condition condition) {
        this.lock = reentrantLock;
        this.list = list;
        this.condition = condition;
    }

    @Override
    public void run() {
        try {
            consume();
        } catch (InterruptedException e) {
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            e.printStackTrace();
        }
    }

    private void consume() throws InterruptedException {
        if (lock.tryLock()) {
            if (list.isEmpty())
                condition.await();
            System.out.println("lock acquired on consumer");
            for (int i = 0; i < list.size(); i++) {
                System.out.println("removed " + list.get(i));
                list.remove(list.get(i));
            }
            condition.signal();
            lock.unlock();
        } else {
            System.out.println(Thread.currentThread().getName() + " cannot acquire lock on Consumer");
        }

    }
}
