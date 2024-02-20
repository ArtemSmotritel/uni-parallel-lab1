import java.time.Duration;

public interface StopPublisher {
    void addSubscriber(Subscriber subscriber, Duration duration);
    void notifyAllSubscribers();
}
