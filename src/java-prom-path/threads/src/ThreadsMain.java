import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ThreadsMain {
    public static void main(String[] args) {
        var main = new ThreadsMain();
        main.forkJoin();
    }

    void forkJoin() {
        var intsList = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        var action = new ListPrinter(intsList);
        action.invoke();
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

            } else {
                var firstAction = new ListPrinter(getSplittedList(intsList, 0, intsList.size() / 2));
                var secondAction = new ListPrinter(getSplittedList(intsList, intsList.size() / 2, intsList.size()));
                invokeAll(firstAction, secondAction);
            }

        }

        private List<Integer> getSplittedList(List<Integer> intList, int firstIndex, int lastIndex) {
            return intList.subList(firstIndex, lastIndex);
        }
    }

}
