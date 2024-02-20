public class Counter implements Runnable, Subscriber {
    private static int ID = 0;
    private final int id;
    private final int step;
    private long sum;
    private volatile boolean isStopped = false;
    private long additiveCount;

    public Counter(int step) {
        this.step = step;
        synchronized (this) {
            id = ++ID;
        }
    }

    @Override
    public void run() {
        while (!isStopped) {
            sum += step;
            additiveCount++;
        }
        System.out.println(this);
    }

    @Override
    public void stop() {
        isStopped = true;
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
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Counter counter = (Counter) obj;
        return id == counter.id;
    }
}
