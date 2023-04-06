package ex04;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("UserNotFoundException");
    }
}