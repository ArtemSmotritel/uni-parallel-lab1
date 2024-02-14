public class SimpleStopper implements Stopper {
    private volatile boolean stopped = false;
    @Override
    public boolean isStopped() {
        return stopped;
    }

    @Override
    public void stop() {
        stopped = true;
    }
}
