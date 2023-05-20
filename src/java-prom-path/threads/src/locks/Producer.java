package locks;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Producer implements Runnable {

    private final Lock lock;
    private final List<Integer> list;
    private final Condition condition;
    private final Random rnd = new Random();

    public Producer(Lock lock, List<Integer> list, Condition condition) {
        this.lock = lock;
        this.list = list;
        this.condition = condition;
    }

    @Override
    public void run() {

        try {
            produce();
        } catch (InterruptedException e) {
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            e.printStackTrace();
        }

    }

    private void produce() throws InterruptedException {
        if (lock.tryLock()) {
            if (list.size() == 100)
                condition.await();

            System.out.println("lock acquired on producer");

            for (int i = 0; i < 100; i++) {
                var number = rnd.nextInt();
                System.out.println("number = " + number);
                list.add(number);
            }
            condition.signal();
            lock.unlock();

        } else {
            System.out.println(Thread.currentThread().getName() + " cannot acquire lock on Producer");
        }
    }
}



    