package ex02;

public class Program {

    public  static void main(String[] args) {
        if (args.length == 0 || args.length != 2) System.exit(-1);
        String str1 = args[0].substring(12);
        String str2 = args[1].substring(15);
        int arrSize = Integer.parseInt(str1);
        int threadsCount = Integer.parseInt(str2);
        int arr[] = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        System.out.println("\nSum: " + sumOfArray(arr, threadsCount));

        for (int i = 0; i < threadsCount; i++) {
            new Thread(new Calculation(arr, threadsCount)).start();
        }
    }

    public static int sumOfArray(int[] arr, int threadsCount) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

}