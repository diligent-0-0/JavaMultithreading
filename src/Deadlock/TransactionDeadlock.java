package Deadlock;

class DbReadOperation{
    private volatile boolean isRead=true;
    private final int id=1;
    private int count=0;

    synchronized public void read(DbWriteOperation dbW){
            System.out.println(Thread.currentThread().getName()+" id:: "+id+" isRead::"+isRead+" count:: "+count);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            dbW.update(this);
    }

    public synchronized void readOnly() {
        System.out.println(Thread.currentThread().getName() + " inside DbReadOperation readOnly()");
    }
}

class DbWriteOperation{
    private volatile boolean isRead=true;
    private final int id=2;
    private int count=0;

    synchronized public void update(DbReadOperation dbR)  {
            try {
                Thread.sleep(1000);
                count++;
                isRead=false;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(Thread.currentThread().getName()+" id:: "+id+" isRead::"+isRead+" count:: "+count);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            dbR.read(this);
    }
}

public class TransactionDeadlock {
    public static void main(String[] args){
        DbReadOperation dbR=new DbReadOperation();
        DbWriteOperation dbW=new DbWriteOperation();

        Thread t1=new Thread(()->dbR.read(dbW),"DB Read");
        Thread t2=new Thread(()->dbW.update(dbR), "DB Update");

        t1.start();
        t2.start();
    }
}
