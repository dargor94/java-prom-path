package locks;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Main {

    private final Lock lock = new ReentrantLock(true);
    private final List<Integer> list = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        var main = new Main();

        var p = new Thread(new Producer(main.lock, main.list, main.lock.newCondition()));
        var c = new Thread(new Consumer(main.lock, main.list, main.lock.newCondition()));
        p.start();
        c.start();

        try {
            p.join();
            c.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private static List<Integer> getIntegers() {
        return LongStream.rangeClosed(0, 100)
                .boxed()
                .mapToInt(Long::intValue)
                .boxed().collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

}
