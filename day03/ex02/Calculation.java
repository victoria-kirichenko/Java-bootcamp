package ex02;

public class Calculation implements Runnable {
    private final static Object lock = new Object();
    private int[] arr;
    private int threadsCount;
    private int sum = 0;
    private final int threadId;
    private static int numOfThreads = 1;
    private static int currentThreadId;
    private static int count = 0;
    private static int end;
    private static int total_sum = 0;

    Calculation(int[] in_arr, int in_threadsCount) {
        arr = in_arr;
        threadsCount = in_threadsCount;
        threadId = numOfThreads++;
    }

    @Override
    public void run() {
        int del = arr.length / threadsCount; // 4
        for(int i = 0; i < threadsCount; i++) {;
            synchronized (lock) {
                while (currentThreadId != threadId) {
                    try {
                        end = (count + del + 1 > arr.length) ? arr.length : count + del + 1;
                        for (int j = count; j < end; j++) {
                            sum += arr[j];
                        }
                        System.out.println("Thread " + threadId + ": from " + (count) + " to " + (end - 1) +
                                " sum is " + sum);
                        count += del + 1;
                        total_sum += sum;
                        if (end == arr.length) {
                            System.out.println("Sum by threads: " + total_sum);
                        }
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                currentThreadId = threadId;
                lock.notifyAll();
            }
        }
    }
}