package lockandsync.sync;

public class SyncTable {
    private final Object o = new Object();
    public void print(int n) {
        synchronized (o) {
            for (int i = 1; i <= 10; i++) {
                System.out.println(n * i);
            }
        }
        System.out.println("<------------------------------------------->");
    }
}
