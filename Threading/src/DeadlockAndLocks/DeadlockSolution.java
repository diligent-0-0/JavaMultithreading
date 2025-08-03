package DeadlockAndLocks;

class ResourceASoln{
    public void methodA(ResourceBSoln b){
        synchronized (this){
            System.out.println("Thread 1: Locked resource A");

            synchronized (b){
                System.out.println("Thread 1: Locked ResourceB");
                b.last();
            }
        }
    }

    public void last(){
        System.out.println("method A's last has been called");
    }
}

class ResourceBSoln{
    public void methodB(ResourceASoln a)
    {
        synchronized (a){
            System.out.println("Thread 2: Locked ResourceA");
        }

        synchronized (this){
            System.out.println("Thread 2: Locked Resiource B");
            a.last();
        }
    }

    public void last(){
        System.out.println("method B has been called");
    }
}

public class DeadlockSolution {
    public static void main(String[] args){
        ResourceASoln rA=new ResourceASoln();
        ResourceBSoln rB=new ResourceBSoln();

        Thread t1=new Thread(()->rA.methodA(rB));
        Thread t2=new Thread(()->rB.methodB(rA));

        t1.start();
        t2.start();
    }
}
