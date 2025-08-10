package BlockingQueue.CustomBlockingQueue;


class CustomBlockingQueue<T>{
    private final Object lock=new Object();
    private final T[] queue;
    private int head=0, tail=0, count=0;
    private final int capacity;

    @SuppressWarnings("unchecked")
    public CustomBlockingQueue(int capacity){
        this.capacity=capacity;
        queue=(T[]) new Object[capacity];
    }

    public void put(T item) throws InterruptedException{
        synchronized (lock){
            while (count==capacity){
                lock.wait();
            }

            queue[tail]=item;
            tail=(tail+1)%capacity;
            count++;
            lock.notifyAll();
        }
    }

    public T take() throws InterruptedException{
        synchronized (lock){
            while (count==0){
                lock.wait();
            }
            T item=queue[head];
            head=(head+1)%capacity;
            count--;
            lock.notifyAll();
            return item;
        }
    }

    public int size(){
        synchronized (lock){
            return count;
        }
    }
}


public class BlockingQueueEg {
    public static void main(String[] args){
        CustomBlockingQueue<Integer>queue=new CustomBlockingQueue<>(5);

        Runnable producer=()->{
            try {
                for(int i=1;i<=10;i++){
                    queue.put(i);
                    System.out.println("Produced: "+i);
                    Thread.sleep(100);
                }
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        };


        Runnable consumer=()->{
            try {
                for(int i=1;i<=10;i++){
                    int item=queue.take();
                    System.out.println("Consumed: "+item);
                    Thread.sleep(150);
                }
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
