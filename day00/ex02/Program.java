package ex02;

import java.util.Scanner;
import java.lang.Math;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int count = 0;
        while (num != 42) {
            boolean res = isPrime(sumOfNum(num));
            if (res == true) {
                count++;
            }
            num = sc.nextInt();
        }
        System.out.print("Count of coffee-request - " + count);
    }
    public static boolean isPrime(int num) {
        boolean isprime = true;
        if (num == 0 || num == 1) {
            isprime = false;
            return isprime;
        }
        for (int i = 2; i <= Math.round(Math.sqrt(num)); i++) {
            if (num % i == 0) {
                isprime = false;
                break;
            }
        }
        return isprime;
    }
    public static int sumOfNum(int num) {
        int sum = 0;
        while (num != 0) {
            int ost = num % 10;
            sum += ost;
            num /= 10;
        }
        return sum;
    }
}