package BlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayQueueEg implements  Delayed{
    private String name;
    private long time;

    public DelayQueueEg(String name, long delayTime){
        this.name=name;
        this.time=System.currentTimeMillis()+delayTime;
    }

    @Override
    public long getDelay(TimeUnit unit){
        long diff=time-System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed obj){
        if(this.time<((DelayQueueEg)obj).time){
            return -1;
        }
        if(this.time>((DelayQueueEg)obj).time){
            return -1;
        }
        return 0;
    }

    @Override
    public String toString(){
        return "\n{"
                + "name=" + name
                + ", time=" + time
                + "}";
    }
}


public class DelayQueueImpl {

    public static void main(String[] args){
        BlockingQueue<DelayQueueEg>d=new DelayQueue<DelayQueueEg>();

        d.add(new DelayQueueEg("A", 1));
        d.add(new DelayQueueEg("B", 2));
        d.add(new DelayQueueEg("C", 3));
        d.add(new DelayQueueEg("D", 4));
        d.add(new DelayQueueEg("F", 5));

        // print the head using peek() method
        System.out.println("Head of DelayQueue: "
                + d.peek());

        // print the size using size() method
        System.out.println("Size of DelayQueue: "
                + d.size());

        // remove the head using poll() method
        System.out.println("Head of DelayQueue: "
                + d.poll());

        // print the size using size() method
        System.out.println("Size of DelayQueue: "
                + d.size());

        // clear the DelayQueue using clear() method
        d.clear();
        System.out.println("Size of DelayQueue"
                + " after clear: "
                + d.size());
    }
}

