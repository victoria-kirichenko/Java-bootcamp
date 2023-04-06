package ex05;

import java.util.UUID;
import java.util.Arrays;

public class TransactionsService {
    UserList list;
    Transaction transaction1;
    Transaction transaction2;

    public TransactionsService() {
        list = new UserArrayList();
    }

    public void userAdd(User user) {
        list.userAdd(user);
    }
    public double getBalance(User user) {
        return list.getUserByUser(user).getBalance();
    }
    public UUID transact(int id1, int id2, double amount) {
        User sender = list.getUser(id1);
        User recipient = list.getUser(id2);
        transaction1 = new Transaction(sender, recipient, amount);
        transaction2 = new Transaction(transaction1.getId(), sender, recipient, amount);
        list.getUserByUser(sender).transactions.transactionAdd(transaction1);
        list.getUserByUser(recipient).transactions.transactionAdd(transaction2);
        return transaction1.getId();
    }

    public Transaction[] GetArrayTransaction(User user) {
        return list.getUserByUser(user).getTransactions().toArray();
    }

    public void RemoveTransaction(int user_id, UUID transaction_id) {
        list.getUser(user_id).getTransactions().transactionRemove(transaction_id);
    }

    public Transaction[] ValidTransactionsCheck() {
        Transaction[] res = new Transaction[0];
        for (int i = 0, len = list.getCountUser(); i < len; i++) {
            Transaction[] transactions = list.getUser(i).getTransactions().toArray();
            for (int j = 0, len2 = list.getUser(i).getTransactions().getCount(); j < len2; j++) {
                boolean find;
                if (transactions[j].getSender() == list.getUser(i)) {
                    find = findTransaction(transactions[j], transactions[j].getRecipient());
                } else {
                    find = findTransaction(transactions[j], transactions[j].getSender());
                }
                if (find == false) {
                    res = Arrays.copyOf(res, res.length + 1);
                    res[res.length - 1] = transactions[j];
                }
            }
        }
        return res;
    }

    public boolean findTransaction(Transaction transaction, User user) {
        boolean res = false;
        Transaction[] transactions = list.getUserByUser(user).getTransactions().toArray();
        for (int i = 0; i < list.getUserByUser(user).getTransactions().getCount(); i++) {
            if (transactions[i].getId().equals(transaction.getId())) {
                res = true;
                break;
            }
        }
        return res;
    }
}