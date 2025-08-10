package BlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessagePassing {
    public static void main(String[] args){
        BlockingQueue<String>queue=new LinkedBlockingQueue<>();

        // Producer
        Thread producer=new Thread(()-> {
            try {
                queue.put("Message 1");
                System.out.println("Produced: Message 1");
                Thread.sleep(500);
                queue.put("Message 2");
                System.out.println("Produced: Message 2");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        });


        // Consumer
        Thread consumer=new Thread(()->{
            try {
                System.out.println("Consumed: "+queue.take());
                System.out.println("Consumed: "+queue.take());
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}
