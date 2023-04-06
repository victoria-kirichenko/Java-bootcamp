package ex03;

import java.util.UUID;

public interface TransactionsList {
    public void transactionAdd(Transaction tr);
    public void transactionRemove(String id);
    public Transaction[] toArray();
}