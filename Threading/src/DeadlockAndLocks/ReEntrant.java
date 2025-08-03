package DeadlockAndLocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ResourceRA{
    final Lock lock=new ReentrantLock();

    public void last(){
        System.out.println("method A's last has been called");
    }
}

class ResourceRB{
    final Lock lock=new ReentrantLock();

    public void last(){
        System.out.println("method B's last has been called");
    }
}

public class ReEntrant {

    public static void main(String[] args){
        ResourceRA rA=new ResourceRA();
        ResourceRB rB=new ResourceRB();

        Thread t1=new Thread(()->safeMethod(rA, rB));
        Thread t2=new Thread(()->safeMethod(rA, rB));

        t1.start();
        t2.start();
    }

    public static void safeMethod(ResourceRA a, ResourceRB b){
        a.lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" locked Resource A");
            b.lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+" locked Resource B");
                a.last();
                b.last();
            }
            finally {
                b.lock.unlock();
            }
        }
        finally{
            a.lock.unlock();
        }
    }
}
