package ex05;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("UserNotFoundException");
    }
}