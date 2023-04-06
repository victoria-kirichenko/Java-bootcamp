package ex00;

public class Printer implements Runnable {

    String str;
    int count;

    Printer(String in_str, int in_count) {
        str = in_str;
        count = in_count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println(str);
        }
    }
}