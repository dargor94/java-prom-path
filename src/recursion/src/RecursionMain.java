import java.util.ArrayList;
import java.util.List;

public class RecursionMain {
    public static void main(String[] args) {
        var main = new RecursionMain();
        main.deepRecursion();
        main.tailRecursion();
    }

    private void deepRecursion() {
        var sum = deepSum(new ArrayList<>() {
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

    private Integer deepSum(List list) {
        if (list.isEmpty()) return 0;
        var first = list.get(0);
        return (
                first instanceof List
                        ? deepSum((List) first) + deepSum(list.subList(1, list.size()))
                        : list
                        .stream()
                        .mapToInt(
                                (o) -> o instanceof Integer ? (Integer) o : deepSum((List) o)).sum()
        );
    }

    /**
     * Tail recursion es un caso de recursividad en el cual la llamada recursiva se da
     * en la ultima instruccion de la funcion o porcion de codigo, teniendo ademas la particularidad de que
     * el resultado de esta recursion nunca es manipulado, si no que es devuelto inmediatamente.
     * Esto resulta en una optimizacion ya que el compilador no necesita procesar mas informacion
     * luego de la llamada recursiva.
     */
    private void tailRecursion() {
        var list = List.of(1, 2, 3, 4, 5);
        var list1 = List.of(10);
        var list2 = List.of(10, 3, 4);

        var avg = tailAvg(list, 0, list.size());
        System.out.println("avg = " + avg);

        var avg1 = tailAvg(list1, 0, list1.size());
        System.out.println("avg1 = " + avg1);

        var avg2 = tailAvg(list2, 0, list2.size());
        System.out.println("avg2 = " + avg2);
    }

    private float tailAvg(List<Integer> integers, float acc, int size) {
        if (integers.size() == 1)
            return (acc + integers.get(0)) / size;
        acc = (acc + integers.get(0));
        var sublist = integers.subList(1, integers.size());
        return tailAvg(sublist, acc, size);
    }

}