package ex01;

import java.util.Scanner;
import java.lang.Math;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        boolean isprime = true;
        int iter = 0;
        if (num <= 0 || num == 1) {
            System.out.print("Illegal Argument");
            return;
        }
        for (int i = 2; i <= Math.round(Math.sqrt(num)); i++) {
            iter++;
            if (num % i == 0) {
                isprime = false;
                break;
            }
        }
        System.out.print(isprime + " " + iter);
    }
}