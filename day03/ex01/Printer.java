package ex01;

public class Printer implements Runnable {

    private final static Object lock = new Object();
    private static int numOfThreads;
    private static int currentThreadId;
    private final int threadId;
    private final String str;
    private final int count;

    public Printer(String in_str, int in_count) {
        str = in_str;
        count = in_count;
        threadId = numOfThreads++;
    }

    @Override
    public void run() {
        for(int i = 0; i < count; i++) {
            synchronized (lock) {
                while (currentThreadId != threadId) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(str);
                currentThreadId = threadId == numOfThreads - 1 ? 0 : threadId + 1;
                lock.notifyAll();
            }
        }
    }
}