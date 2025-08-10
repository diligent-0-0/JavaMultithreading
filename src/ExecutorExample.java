import java.util.concurrent.*;

public class ExecutorExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService singleTExecutor=Executors.newSingleThreadExecutor();
//        singleTExecutor.submit(()->{
//            System.out.println("Task 1");
//        });
//        singleTExecutor.submit(()->{
//            System.out.println("Task 2");
//        });
//        singleTExecutor.shutdown();
//
//
//        ExecutorService executor= Executors.newFixedThreadPool(2);
//        Callable<Integer>task=()->42;
//        Future<Integer>future=executor.submit(task);
//        System.out.println(future.get());
//        executor.shutdown();


//        ExecutorService fixedTPExecutor=Executors.newFixedThreadPool(3);
//        for(int i=0;i<10;i++)
//        {
//            fixedTPExecutor.submit(()->{
//                System.out.println("Task "+Thread.currentThread().getName());
//            });
//        }
//        fixedTPExecutor.shutdown();;
//

        ExecutorService cachedTPexecutor = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            cachedTPexecutor.submit(() -> {
                System.out.println("Task " + Thread.currentThread().getName());
            });
        }
        cachedTPexecutor.shutdown();


        ScheduledExecutorService scheduledExecutor=Executors.newScheduledThreadPool(2);
        // Schedule a task to run after 3 seconds
        scheduledExecutor.schedule(()->System.out.println("Task executed after 3 seconds"), 3, TimeUnit.SECONDS);
        // Schedule a task to run repeatedly every 1 second
        scheduledExecutor.scheduleAtFixedRate(() -> System.out.println("Task executed every 1 second"), 0, 1, TimeUnit.SECONDS);

        
    }
}
