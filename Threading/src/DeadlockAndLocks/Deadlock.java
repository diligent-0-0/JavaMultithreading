package DeadlockAndLocks;

class ResourceA{
    synchronized void methodA(ResourceB b){
        System.out.println("Thread 1: Locked ResourceA, waiting for ResourceB...");
        try { Thread.sleep(100); } catch (InterruptedException e) {}
        b.last();
    }

    synchronized public void last(){
        System.out.println("method A's last has been called");
    }
}

class ResourceB{
    synchronized void methodB(ResourceA a){
        System.out.println("Thread 2: Locked ResourceB, waiting for ResourceA...");
        try { Thread.sleep(100); } catch (InterruptedException e) {}
        a.last();
    }

    synchronized public void last(){
        System.out.println("method B has been called");
    }
}


public class Deadlock {
    public static void main(String[] args)
    {
        ResourceA rA=new ResourceA();
        ResourceB rB=new ResourceB();


        Thread t1=new Thread(()->rA.methodA(rB));
        Thread t2=new Thread(()->rB.methodB(rA));

        t1.start();
        t2.start();
    }
}
