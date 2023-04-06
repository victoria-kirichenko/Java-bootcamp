package ex01;

public class Program {

    public  static void main(String[] args) {
        if (args.length == 0) System.exit(-1);
        String str = args[0].substring(8);
        int count = Integer.parseInt(str);
        Thread t1 = new Thread(new Printer(("Egg"), count));
        Thread t2 = new Thread(new Printer(("Hen"), count));
        t1.start();
        t2.start();
    }

}