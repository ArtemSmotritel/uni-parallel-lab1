public class Counter implements Runnable {
    private static int ID = 0;
    private final int id;
    private final Stopper stopper;
    private final int step;
    private long sum;
    private long additiveCount;
    public Counter(Stopper stopper, int step) {
        this.stopper = stopper;
        this.step = step;
        synchronized (this) {
            id = ++ID;
        }
    }

    @Override
    public String toString() {
        return "Counter{" +
                "id=" + id +
                ", step=" + step +
                ", sum=" + sum +
                ", additiveCount=" + additiveCount +
                '}';
    }

    @Override
    public void run() {
        while (!stopper.isStopped()) {
            sum += step;
            additiveCount++;
        }
        System.out.println(this);
    }
}
