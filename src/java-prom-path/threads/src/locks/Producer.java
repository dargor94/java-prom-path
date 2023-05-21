package locks;

public class Producer implements Runnable {

    private final Resource resource;

    public Producer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        try {
            resource.produce();
        } catch (InterruptedException e) {
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            e.printStackTrace();
        }
    }
}



    