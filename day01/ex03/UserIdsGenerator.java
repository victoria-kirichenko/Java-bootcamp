package ex03;

public class UserIdsGenerator {
    private static final UserIdsGenerator instance = new UserIdsGenerator();

    private static int id = -1;

    private UserIdsGenerator() {
    }

    public static UserIdsGenerator getInstance() {
        return instance;
    }

    public int generateId() {
        id += 1;
        return id;
    }
}