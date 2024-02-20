import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class StopPublisherImpl implements StopPublisher {
    private final Map<Subscriber, Duration> subscribers = new HashMap<>();

    @Override
    public void addSubscriber(Subscriber subscriber, Duration duration) {
        subscribers.put(subscriber, duration);
    }

    @Override
    public void notifyAllSubscribers() {
        subscribers.forEach((subscriber, duration) -> new Thread(() -> {
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            subscriber.stop();
        }).start());
    }
}
