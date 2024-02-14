import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int threadCount = 3;
        List<Stopper> stoppers = new ArrayList<>(threadCount);

        for (int i = 0; i < threadCount; i++) {
            Stopper stopper = new IndependentThreadStopper(Duration.ofSeconds(i * 2));
            int step = i * 3 + 1;
            var counter = new Counter(stopper, step);
            stoppers.add(stopper);
            new Thread(counter).start();
        }

        for (int i = 0; i < threadCount; i++) {
            stoppers.get(i).stop();
        }

        System.out.println("Main thread is done");
    }
}