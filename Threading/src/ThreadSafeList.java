import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
* Coarse-grained locking:

One lock for the entire list → easy to implement, ensures atomic operations.

Downside:

Only one thread can access it at a time → not optimal for high concurrency.
* */

public class ThreadSafeList<T> {
    private final List<T>list = new ArrayList<>();
    private final Lock lock = new ReentrantLock();

    public void add(T element){
        lock.lock();
        try {
            list.add(element);
        }finally {
            lock.unlock();
        }
    }

    public void remove(T element){
        lock.lock();
        try {
            list.remove(element);
        }finally {
            lock.unlock();
        }
    }

    public void get(int index){
        lock.lock();
        try {
            list.get(index);
        } finally {
            lock.unlock();
        }
    }

    public int size(){
        lock.lock();
        try {
            return list.size();
        }finally {
            lock.unlock();
        }
    }
}

