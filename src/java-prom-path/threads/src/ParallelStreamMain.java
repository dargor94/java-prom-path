import java.util.stream.LongStream;

public class ParallelStreamMain {

    public static void main(String[] args) {
        var parallelStreamMain = new ParallelStreamMain();
        parallelStreamMain.sumSequential();
        parallelStreamMain.sumParallel();
    }

    void sumSequential() {
        long start = System.currentTimeMillis();
        var sum = LongStream.rangeClosed(1, 1000000000).reduce(0L, Long::sum);
        System.out.println("sum = " + sum);
        System.out.println("Time for sequential method: " + (System.currentTimeMillis() - start) + "ms");
    }

    void sumParallel() {
        long start = System.currentTimeMillis();
        var sum = LongStream.rangeClosed(1, 1000000000).parallel().reduce(0L, Long::sum);
        System.out.println("sum = " + sum);
        System.out.println("Time for parallel method: " + (System.currentTimeMillis() - start) + "ms");
    }


}
