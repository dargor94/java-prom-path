package locks;

public class Consumer implements Runnable {
    private final Resource resource;

    public Consumer(Resource resource) {
        this.resource = resource;
    }


    @Override
    public void run() {
        try {
            resource.consume();
        } catch (InterruptedException e) {
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            e.printStackTrace();
        }
    }


}
