package BlockingQueue;

//https://medium.com/@ProgrammingTutorials/implementing-a-custom-blocking-queue-in-java-e348e525e87d
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueEg {
    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();

        Thread producer = new Thread(() -> {
            try {
                System.out.println("Producing: Immediate message");
                queue.put("Immediate message"); // waits until consumer takes it
                System.out.println("Message handed off");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                Thread.sleep(2000); // simulate some delay
                System.out.println("Consumed: " + queue.take());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}
