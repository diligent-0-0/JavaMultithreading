import java.util.concurrent.Semaphore;

public class RateLimiter {

    private final Semaphore semaphore;

    public RateLimiter(int maxConcurrentReq){
        semaphore=new Semaphore(maxConcurrentReq, true);
    }

    public void handleReq(){
        try{
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName()+" is handling req:");
            Thread.sleep((long)(Math.random()+1000));
            System.out.println(Thread.currentThread().getName()+" has finished");

        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            semaphore.release();
        }
    }

    public static void main(String[] args){
        RateLimiter rateLimiter=new RateLimiter(3);

        for (int i=0;i<10;i++){
            new Thread(rateLimiter::handleReq).start();
        }
    }
}
