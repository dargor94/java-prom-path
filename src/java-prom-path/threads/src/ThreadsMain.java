import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ThreadsMain {
    public static void main(String[] args) {
        var main = new ThreadsMain();
        main.forkJoin();
        main.fibonacci();
        main.enhancedFibonacci();
    }

    void forkJoin() {
        var intsList = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        var action = new ListPrinter(intsList);
        action.invoke();
    }

    void fibonacci() {
        System.out.println(new FibonacciTask(0).compute());
    }

    void enhancedFibonacci() {
        System.out.println(new EnhancedFibonacciTask(0).compute());
    }

    static class ListPrinter extends RecursiveAction {
        private final List<Integer> intsList;

        ListPrinter(List<Integer> intsList) {
            this.intsList = intsList;
        }

        @Override
        protected void compute() {
            if (intsList.size() < 2) {

                for (Integer integer : intsList)
                    System.out.println("integer = " + integer);
                return;
            }

            var firstAction = new ListPrinter(getSplittedList(intsList, 0, intsList.size() / 2));
            var secondAction = new ListPrinter(getSplittedList(intsList, intsList.size() / 2, intsList.size()));
            invokeAll(firstAction, secondAction);

        }

        private List<Integer> getSplittedList(List<Integer> intList, int firstIndex, int lastIndex) {
            return intList.subList(firstIndex, lastIndex);
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
     * Compute es llamado por el hilo principal. Al generar menos hilos el rendimiento es mayor.
     */
    static class EnhancedFibonacciTask extends RecursiveTask<Integer> {

        private final Integer num;

        public EnhancedFibonacciTask(Integer num) {
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
