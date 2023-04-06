package ex05;

import java.time.DayOfWeek;
import java.util.Scanner;
import java.util.Arrays;

public class Program {
    public static void main(String[] args) {
        int count_students = 0;
        String[] name = new String[10];
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals(".")) {
            name[count_students] = input;
            count_students++;
            input = sc.nextLine();
        }

        boolean[][] shedule = new boolean[7][6];
        input = sc.nextLine();
        while (!input.equals(".")) {
            String[] time_week = input.split(" ");
            int day_idx = dayOfWeek(time_week[1]);
            shedule[day_idx][Integer.parseInt(time_week[0])] = true;
            input = sc.nextLine();
        }

        short[][][] attendance = new short[10][31][6];
        input = sc.nextLine();
        while (!input.equals(".")) {
            String[] n_t_d = input.split(" ");
            int studentIdx = studentIdx(name, n_t_d[0]);
            int time = Integer.parseInt(n_t_d[1]);
            int day = Integer.parseInt(n_t_d[2]);
            attendance[studentIdx][day][time] = (n_t_d[3].equals("HERE")) ? (short)1 : (short)-1;
            input = sc.nextLine();
        }
                       // 7     1     2     3     4     5     6
        String[] week = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};

        System.out.print(" ".repeat(10));

        for (int i = 1; i <= 30; i++) {
            int dayOfWeek = i % 7;
            for (int t = 1; t < 6; t++) {
                if (shedule[dayOfWeek][t]) {
                    System.out.printf("%d:00 %s %2d|", t, week[dayOfWeek], i);
                }
            }
        }
        System.out.println();

        for (int i = 0; i < count_students; i++) {
            System.out.printf("%10s", name[i]);
            for (int d = 1; d <= 30; d++) {
                int dayOfWeek = d % 7;
                for (int t = 1; t < 6; t++) {
                    if (shedule[dayOfWeek][t]) {
                        int attend = attendance[i][d][t];
                        if (attend != 0) System.out.printf("%10d|", attendance[i][d][t]);
                        else System.out.print(" ".repeat(10) + "|");
                    }
                }
            }
            System.out.println();
        }


    }

    public static int dayOfWeek(String day) {
        return switch (day) {
            case "MO" -> 0;
            case "TU" -> 1;
            case "WE" -> 2;
            case "TH" -> 3;
            case "FR" -> 4;
            case "SA" -> 5;
            case "SU" -> 6;
            default -> -1;
        };
    }

    public static int studentIdx(String[] arr, String name) {
        for (int i = 0; i < arr.length; i++) {
            if (name == arr[i]) {
                return i;
            }
        }
        return 0;
    }


}