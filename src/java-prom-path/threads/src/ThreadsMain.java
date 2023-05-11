import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class ThreadsMain {
    ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

    public static void main(String[] args) {
        var main = new ThreadsMain();
        main.forkJoin();
        main.fibonacci();
    }


    void forkJoin() {

        var longList = LongStream.rangeClosed(1, 10000000).boxed().collect(Collectors.toList());
        var action = new ListPrinter(longList);

        System.out.println("---------- Printing integers (Parallel) ----------");
        var parallelInitTime = System.currentTimeMillis();
        action.invoke();
        var parallelFinishTime = System.currentTimeMillis();

        System.out.println("Running Time = " + (parallelFinishTime - parallelInitTime));

    }

    void fibonacci() {

        System.out.println("---------- Printing fibonacci ----------");
        var initTime = System.currentTimeMillis();
        System.out.println(pool.invoke(new FibonacciTask(40)));
        var finishTime = System.currentTimeMillis();

        System.out.println("---------- Printing fibonacci v2----------");
        var initTime2 = System.currentTimeMillis();
        System.out.println(pool.invoke(new FibonacciTaskVersionTwo(40)));
        var finishTime2 = System.currentTimeMillis();

        System.out.println("Running Time v1 = " + (finishTime - initTime));
        System.out.println("Running Time v2 = " + (finishTime2 - initTime2));
    }

    static class ListPrinter extends RecursiveAction {
        private final List<Long> longList;

        ListPrinter(List<Long> intsList) {
            this.longList = intsList;
        }

        @Override
        protected void compute() {
            //Sequential
            if (longList.size() < 2) {
                for (Long l : longList)
                    System.out.println(l);
                return;
            }
            //Parallel
            var firstAction = new ListPrinter(getSplittedList(longList, 0, longList.size() / 2));
            var secondAction = new ListPrinter(getSplittedList(longList, longList.size() / 2, longList.size()));
            invokeAll(firstAction, secondAction);

        }

        private List<Long> getSplittedList(List<Long> longList, int firstIndex, int lastIndex) {
            return longList.subList(firstIndex, lastIndex);
        }
    }

    static class FibonacciTask extends RecursiveTask<Integer> {

        private final Integer num;

        public FibonacciTask(Integer num) {
            this.num = num;
        }

        @Override
        protected Integer compute() {
            if (num <= 1)
                return num;
            FibonacciTask taskA = new FibonacciTask(num - 1);
            FibonacciTask taskB = new FibonacciTask(num - 2);
            taskA.fork();
            taskB.fork();
            return taskA.join() + taskB.join();

        }
    }


    /**
     * Compute es llamado por el hilo principal.
     */
    static class FibonacciTaskVersionTwo extends RecursiveTask<Integer> {

        private final Integer num;

        public FibonacciTaskVersionTwo(Integer num) {
            this.num = num;
        }

        @Override
        protected Integer compute() {
            if (num <= 1)
                return num;
            FibonacciTask taskA = new FibonacciTask(num - 1);
            FibonacciTask taskB = new FibonacciTask(num - 2);
            taskA.fork();
            taskB.fork();

            return taskA.compute() + taskB.join();

        }
    }
}
