package ex00;

import java.util.UUID;

enum TransferCategory {
    DEBITS,
    CREDITS
}

public class Transaction {
    UUID id;
    User sender;
    User recipient;
    TransferCategory transfer_type;
    double amount;

    public Transaction(User sender_, User recipient_, TransferCategory transfer_type_, double amount_) {
        if (amount_ < 0) throw new IllegalArgumentException("amount is negative");
        if (sender_.getBalance() - amount_ < 0) throw new IllegalArgumentException("not enough money");
        id = UUID.randomUUID();
        transfer_type = transfer_type_;
        amount = amount_;
        sender = sender_;
        recipient = recipient_;
        sender_.setBalance(-amount_);
        recipient_.setBalance(amount_);
    }

    public void print() {
        System.out.print("id: " + id + " Sender: (");
        sender.print();
        System.out.print(") Recipient: (");
        recipient.print();
        System.out.println(") TransferCategory: " + transfer_type + " amount: " + amount);
    }

}