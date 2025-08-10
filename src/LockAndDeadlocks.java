import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SharedResouce {
    private int count=0;
    private final Lock lock=new ReentrantLock();

    public void increment(){
        lock.lock(); // Aquire lock
        try{
            count++;
            System.out.println(Thread.currentThread().getName()+" incemented "+count);
        }
        finally {
            lock.unlock();
        }
    }
}

public class LockAndDeadlocks {

    public static void main(String[] args)
    {
        SharedResouce resouce=new SharedResouce();
        Runnable task=new Runnable() {
            @Override
            public void run() {
                resouce.increment();
            }
        };

        Thread t1=new Thread(task, "Thread-1");
        Thread t2=new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }
}
