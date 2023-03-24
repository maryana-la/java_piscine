import java.lang.Thread;

class myThread extends Thread{
    private final int num;
    private final String threadName;
    myThread(String name, int num) {
        this.threadName = name;
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 0; i < num; i++) {
            System.out.println(i + 1 + " " + threadName);
        }
    }
}
