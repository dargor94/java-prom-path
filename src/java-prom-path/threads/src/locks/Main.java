package locks;

public class Main {

    public static void main(String[] args) {
        var main = new Main();
        var resource = new Resource();
        var p = new Thread(new Producer(resource));
        var c = new Thread(new Consumer(resource));
        p.start();
        c.start();

        try {
            p.join();
            c.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}