import java.util.ArrayList;
import java.util.List;

public class RecursionMain {
    public static void main(String[] args) {
        var main = new RecursionMain();
        main.deepRecursion();
        main.tailRecursion();
    }

    private void tailRecursion() {
    }

    private void deepRecursion() {
        var sum = sum(new ArrayList<>() {
            {
                add(1);
                add(List.of(5, List.of(6), List.of(7, 8)));
                add(List.of(1, 2, 3, 4));
                add(List.of(1, List.of(1, 2, List.of(3, 4))));
                add(2);
            }
        });

        System.out.println("sum = " + sum);
    }

    private Integer sum(List list) {
        if (list.isEmpty()) return 0;
        var first = list.get(0);
        return (
                first instanceof List
                        ? sum((List) first) + sum(list.subList(1, list.size()))
                        : list
                        .stream()
                        .mapToInt(
                                (o) -> o instanceof Integer ? (Integer) o : sum((List) o)).sum()
        );
    }
}