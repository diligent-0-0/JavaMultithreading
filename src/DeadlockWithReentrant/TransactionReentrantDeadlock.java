package DeadlockWithReentrant;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class DbReadOperation{
    private volatile boolean isRead=true;
    private final int id=1;
    private int count=0;
    Lock lock=new ReentrantLock();

    public void read(DbWriteOperation dbW){
        lock.lock();
        try {
        System.out.println(Thread.currentThread().getName()+" id:: "+id+" isRead::"+isRead+" count:: "+count);
            Thread.sleep(100);
            dbW.update(this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}

class DbWriteOperation{
    private volatile boolean isRead=true;
    private final int id=2;
    private int count=0;
    Lock lock=new ReentrantLock();

    public void update(DbReadOperation dbR)  {
        lock.lock();
        try {
            Thread.sleep(1000);
            count++;
            isRead=false;
            System.out.println(Thread.currentThread().getName()+" id:: "+id+" isRead::"+isRead+" count:: "+count);
            dbR.read(this);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }


        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}



public class TransactionReentrantDeadlock {
    public static void main(String[] args){
        DbReadOperation dbR=new DbReadOperation();
        DbWriteOperation dbW=new DbWriteOperation();

        Thread t1=new Thread(()->dbR.read(dbW),"DB Read");
        Thread t2=new Thread(()->dbW.update(dbR), "DB Update");

        t1.start();
        t2.start();
    }
}

