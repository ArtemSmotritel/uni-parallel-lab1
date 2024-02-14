import java.time.Duration;

public class IndependentThreadStopper implements Stopper {
    private final Duration delay;
    private volatile boolean stopped = false;

    public IndependentThreadStopper(Duration delay) {
        this.delay = delay;
    }

    @Override
    public boolean isStopped() {
        return stopped;
    }

    @Override
    public void stop() {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                stopped = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
