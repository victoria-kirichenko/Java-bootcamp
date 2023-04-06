package ex05;

public class Program {
    public static void main(String[] args) {
        Menu menu = new Menu();
        if (args.length == 0) {
            System.exit(-1);
        }
        if (args[0].equals("--profile=dev")) {
            menu.run(Modes.DEV);
        } else {
            menu.run(Modes.STANDARD);
        }
    }
}