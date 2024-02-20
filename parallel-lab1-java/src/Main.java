import java.time.Duration;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        int threadCount;
        Scanner scanner = new Scanner(System.in);
        threadCount = Integer.parseInt(scanner.nextLine());

        StopPublisher stopPublisher = new StopPublisherImpl();

        for (int i = 0; i < threadCount; i++) {
            int step = i * 3 + 1;
            var counter = new Counter(step);
            stopPublisher.addSubscriber(counter, Duration.ofSeconds(ThreadLocalRandom.current().nextInt(1, threadCount)));
            new Thread(counter).start();
        }

        stopPublisher.notifyAllSubscribers();

        System.out.println("Main thread is done");
    }
}