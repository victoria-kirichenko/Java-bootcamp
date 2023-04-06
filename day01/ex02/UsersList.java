package ex02;

public interface UsersList {
    public void userAdd(User user);
    public User getUser(int id);
    public User getUserByIdx(int idx);
    public int getCountUser();
}