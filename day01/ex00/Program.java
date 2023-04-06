package ex00;

public class Program {
    public static void main(String[] args) {
        User user1 = new User(1, "Vika", 1500);
        User user2 = new User(2, "Vasya", 200);
        Transaction tr1 = new Transaction(user2, user1, TransferCategory.DEBITS, 100);
        Transaction tr2 = new Transaction(user1, user2, TransferCategory.CREDITS, 600);
        user1.print();
        user2.print();
        tr1.print();
        tr2.print();
        user1.print();
        user2.print();

    }
}