package ex05;

public class TransactionNotFoundException extends RuntimeException{
    public TransactionNotFoundException() {
        super("TransactionNotFoundException");
    }
}