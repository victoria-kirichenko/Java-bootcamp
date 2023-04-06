package ex05;

import java.util.UUID;

public interface TransactionsList {
    public void transactionAdd(Transaction tr);
    public void transactionRemove(UUID id);
    public Transaction[] toArray();
    public int getCount();

    public Transaction getTransactionByid(UUID id);
}