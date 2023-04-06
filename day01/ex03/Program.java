package ex03;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Vika", 1500);
        User user2 = new User("Vasya", 200);
        Transaction tr1 = new Transaction(user2, user1, TransferCategory.DEBITS, 100);
        Transaction tr2 = new Transaction(user1, user2, TransferCategory.CREDITS, 600);
        Transaction tr3 = new Transaction(user1, user2, TransferCategory.CREDITS, 500);
        user1.transactions.transactionAdd(tr1);
        user1.transactions.transactionAdd(tr2);
        user1.transactions.transactionAdd(tr3);
        String id_tr2 = tr2.getId();
        Transaction[] arr = user1.transactions.toArray();
        for (int i = 0; i < arr.length; i++) {
            arr[i].print();
        }
        user1.transactions.transactionRemove(id_tr2);
        Transaction[] arr1 = user1.transactions.toArray();
        for (int i = 0; i < arr1.length; i++) {
            arr1[i].print();
        }
    }
}