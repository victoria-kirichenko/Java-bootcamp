package ex04;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Vika", 1500);
        User user2 = new User("Vasya", 200);
        User user3 = new User("Julia", 1000);
        TransactionsService service = new TransactionsService();
        service.userAdd(user1);
        service.userAdd(user2);
        service.userAdd(user3);
        System.out.println(service.getBalance(user1));
        UUID tr1 = service.transact(0, 1, 500, TransferCategory.DEBITS);
        System.out.println(service.getBalance(user1) + " " + service.getBalance(user2));
        UUID tr2 = service.transact(2, 0, 300, TransferCategory.CREDITS);
        System.out.println(service.getBalance(user1) + " " + service.getBalance(user3));
        Transaction[] arr = service.GetArrayTransaction(user1);
        for (int i = 0; i < arr.length; i++) {
            arr[i].print();
        }
        Transaction[] arr1 = service.ValidTransactionsCheck();
        System.out.println(arr1.length);
        service.RemoveTransaction(0, tr1);
        Transaction[] arr2 = service.ValidTransactionsCheck();
        System.out.println(arr2.length);
    }
}