class Counter implements Runnable{
    int count=0;

    @Override
    public void run(){
        for(int i=0;i<1000;i++)
        {
            count++;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class BasicCounterWithRaceCondition {
    public static void main(String[] args) throws InterruptedException
    {
        Counter c=new Counter();
        Thread t1=new Thread(c);
        Thread t2=new Thread(c);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Count "+c.count);
    }
}
