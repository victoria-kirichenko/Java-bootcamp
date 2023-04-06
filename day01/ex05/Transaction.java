package ex05;

import java.util.UUID;

public class Transaction {
    UUID id;
    User sender;
    User recipient;
    double amount;

    public Transaction(User sender_, User recipient_, double amount_) {
        if (amount_ < 0) throw new IllegalTransactionException();
        if (sender_.getBalance() - amount_ < 0) throw new IllegalTransactionException();
        sender = sender_;
        recipient = recipient_;
        id = UUID.randomUUID();
        amount = -amount_;
        sender_.setBalance(-amount_);
        recipient_.setBalance(amount_);
    }

    public Transaction(UUID id_, User sender_, User recipient_, double amount_) {
        id = id_;
        sender = sender_;
        recipient = recipient_;
        amount = amount_;
    }

    public void print() {
        if (amount < 0) {
            System.out.println("To " + recipient.getName() + "(id = " + (recipient.getId() + 1) + ") " + amount + " with id = " + id);
        } else {
            System.out.println("From " + sender.getName() + "(id = " + (sender.getId() + 1) + ") " + amount + " with id = " + id);
        }

    }

    public UUID getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public double getAmount() {
        return amount;
    }
}