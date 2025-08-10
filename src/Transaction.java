import java.util.concurrent.atomic.AtomicInteger;

class DbReadOperation{
   private volatile boolean isRead=true;
   private final int id=1;
   private int count=0;

   public void read(){

       synchronized (this){
           System.out.println(Thread.currentThread().getName()+" id:: "+id+" isRead::"+isRead+" count:: "+count);
       }
   }
}

class DbWriteOperation{
    private volatile boolean isRead=true;
    private final int id=2;
    private int count=0;

    public void update()  {
        synchronized (this){
            try {
                Thread.sleep(1000);
                count++;
                isRead=false;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(Thread.currentThread().getName()+" id:: "+id+" isRead::"+isRead+" count:: "+count);
        }
    }
}

public class Transaction {
    public static void main(String[] args){
        DbReadOperation dbR=new DbReadOperation();
        DbWriteOperation dbW=new DbWriteOperation();

        Thread t1=new Thread(dbR::read,"DB Read");
        Thread t2=new Thread(dbW::update, "DB Update");

        t1.start();
        t2.start();
    }
}
