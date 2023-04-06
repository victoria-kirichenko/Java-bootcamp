package ex04;

public interface UserList {
    public void userAdd(User user);
    public User getUser(int id);
    public User getUserByIdx(int idx);

    public User getUserByUser(User user);
    public int getCountUser();
}