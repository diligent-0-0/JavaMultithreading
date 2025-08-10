class CounterWithWaitNotify {
    private volatile int counter = 0;
    private final int limit;

    CounterWithWaitNotify(int limit) {
        this.limit = limit;
    }

    public synchronized void printOddNo() {
        while (counter <= limit) {
            while (counter % 2 == 0 && counter <= limit) { // Wait for odd turn
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (counter <= limit) {
                System.out.println(Thread.currentThread().getName() + " " + counter);
                counter++;
                notifyAll();
            }
        }
    }

    public synchronized void printEvenNo() {
        while (counter <= limit) {
            while (counter % 2 == 1 && counter <= limit) { // Wait for even turn
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (counter <= limit) {
                System.out.println(Thread.currentThread().getName() + " " + counter);
                counter++;
                notifyAll();
            }
        }
    }
}

public class OddEvenThreads {
    public static void main(String[] args) {
        CounterWithWaitNotify c = new CounterWithWaitNotify(10);

        Thread t1 = new Thread(c::printOddNo, "Odd");
        Thread t2 = new Thread(c::printEvenNo, "Even");

        t1.start();
        t2.start();
    }
}
