import java.util.ArrayList;
import java.util.List;

public class RecursionMain {
    public static void main(String[] args) {

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

    private static Integer sum(List lists) {
        if (lists.isEmpty()) return 0;
        var first = lists.get(0);
        return (
                first instanceof List
                        ? sum((List) first) + sum(lists.subList(1, lists.size()))
                        : lists.stream().mapToInt(
                        (o) -> o instanceof Integer ? (Integer) o : sum((List) o)).sum()
        );
    }
}