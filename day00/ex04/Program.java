package ex04;

import java.util.Scanner;
import java.util.Arrays;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] top = new char[10];
        int[] top_count = new int[10];
        int[] count = new int[65535];

        Arrays.fill(count, 0);
        for (int i = 0; i < str.length(); i++) {
            count[(int)str.charAt(i)]++;
        }

        for (int i = 0; i < 10; i++) {
            int max = count[0];
            int idx = 0;
            for (int j = 1; j < count.length; j++) {
                if (max < count[j]) {
                    max = count[j];
                    idx = j;
                }
            }
            top[i] = (char)idx;
            top_count[i] = count[idx];
            count[idx] = 0;
        }
        print(top, top_count);
    }

    public static void print(char[] top, int[] top_count) {
        int[] ost = new int[10];
        for (int i = 0; i < 10; i++) {
            ost[i] = top_count[i] * 10 / top_count[0];
        }
        System.out.println();
        int maxWeight = ost[0];
        int row = -1;
        while (row < maxWeight) {
            for (int i = 0; i < 10; i++) {
                if (ost[i] == maxWeight - row - 1) {
                    System.out.print(top_count[i]+"\t");
                }
                if (ost[i] >= maxWeight - row) {
                    System.out.print("#\t");
                }
            }
            System.out.println();
            row++;
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(top[i] + "\t");
        }
    }
}