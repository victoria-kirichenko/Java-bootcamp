package ex02;

public class User {
    int id;
    String name;
    double balance;

    public User(String name_, double balance_) {
        if (balance_ < 0) throw new IllegalArgumentException("balance is negative");
        id = UserIdsGenerator.getInstance().generateId();
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