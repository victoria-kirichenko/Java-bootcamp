package ex00;

public class User {
    int id;
    String name;
    double balance;

    public User(int id_, String name_, double balance_) {
        if (balance_ < 0) throw new IllegalArgumentException("balance is negative");
        id = id_;
        name = name_;
        balance = balance_;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance_) {
        balance += balance_;
    }

    public void print() {
        System.out.println("id: " + id + " name: " + name + " balance: " + balance);
    }

}