package lockandsync.sync;

public class SyncMain {

    public static void main(String[] args) {

        var table = new SyncTable();
        System.out.println("<------------------------------------------->");
        new Thread(() -> table.print(5)).start();
        new Thread(() -> {
            table.print(10);
        }).start();

    }

}
