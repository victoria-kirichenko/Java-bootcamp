package ex03;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long res = 0;
        int i;
        String week;
        for (i = 1; i <= 18; i++) {
            week = sc.nextLine();
            if (week.equals("42")) {
                break;
            }
            if (!week.endsWith(Integer.toString(i))) {
                System.out.print("Illegal Argument");
                return;
            }
            int num;
            int min = sc.nextInt();
            for (int j = 0; j < 4; j++) {
                num = sc.nextInt();
                if (min > num) {
                    min = num;
                }
            }
            res = (res + min) * 10;
            sc.nextLine();
        }
        print(res, i);
    }

    public static void print(long num, int size) {
        String str = Long.toString(num);
        for (int i = 0; i < size - 1; i++) {
            System.out.print("Week " + (i + 1) + " ");
            for (int j = 0, len = Integer.parseInt(String.valueOf(str.charAt(i))); j < len; j++) {
                System.out.print("=");
            }
            System.out.println(">");
        }
    }
}