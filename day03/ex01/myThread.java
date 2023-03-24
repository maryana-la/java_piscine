import java.lang.Thread;

public class myThread extends Thread {
    private final String threadName;
    private final int num;
    private final int turn;
    static final Object lock = new Object();
    static int queue = 0;


    myThread(String name, int num, int turn) {
        this.threadName = name;
        this.num = num;
        this.turn = turn;
    }


    @Override
    public void run() {
        synchronized (lock) {
            try {
                for (int i = 0; i < num; i++) {
                    while (queue % 2 != turn) {
                        lock.wait();
                    }
                    System.out.println(i + 1 + " " + threadName);
                    queue++;
                    lock.notify();
                }
            } catch (InterruptedException e) {
                e.getLocalizedMessage();
            }
        }
    }
}
