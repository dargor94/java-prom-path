import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio realzado en curso udemy
 *
 * @url: <a href="https://globallogic.udemy.com/course/java-multithreading-concurrency-performance-optimization">...</a>
 */
public class MultiExecutor {

    private List<Runnable> tasks;

    /**
     * @param tasks to be executed concurrently
     */
    public MultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    public static void main(String[] args) {
        var main = new MultiExecutor(null);
        main.getTasks();
        main.executeAll();
    }

    private void getTasks() {
        tasks = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            tasks.add(() -> System.out.println(Thread.currentThread().getName()));
        }
    }

    /**
     * Starts and executes all the tasks concurrently
     */
    private void executeAll() {
        List<Thread> threads = new ArrayList<>(tasks.size());
        for (Runnable task : tasks) {
            Thread thread = new Thread(task);
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }
}