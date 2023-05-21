package locks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock ;

public class Resource {

    private static final int MAX_CAPACITY = 10;
    private final Lock lock = new ReentrantLock(true);
    private final List<Integer> list = new ArrayList<>();
    private final Condition isFull = lock.newCondition();
    private final Condition isEmpty = lock.newCondition();
    private final Random rnd = new Random();

    public void produce() throws InterruptedException {
        lock.lock();
        try {
            while (list.size() == MAX_CAPACITY) {
                System.out.println("queue is full, waiting..");
                isFull.await();
            }
            System.out.println("lock acquired on producer");

            for (int i = 0; i < MAX_CAPACITY; i++) {
                var number = rnd.nextInt();
                System.out.println("number = " + number);
                list.add(number);
            }
            isEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void consume() throws InterruptedException {
        lock.lock();

        try {
            while (list.isEmpty()) {
                System.out.println("queue is empty, waiting..");
                isEmpty.await();
            }
            System.out.println("lock acquired on consumer");
            for (int i = 0; i < list.size(); i++) {
                System.out.println("removed " + list.get(i));
                list.remove(list.get(i));
            }
            isEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
