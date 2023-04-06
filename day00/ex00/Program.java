package ex00;

public class Program {
    public static void main(String[] args) {
        int num = 479598;
        int sum = 0;
        while (num != 0) {
            int ost = num % 10;
            sum += ost;
            num /= 10;
        }
        System.out.print(sum);
    }
}