package ex02;

import java.util.Arrays;

public class UsersArrayList implements UsersList {
    User[] list = new User[10];
    int count = 0;

    @Override
    public void userAdd(User user) {
        if (count == list.length) {
            list = Arrays.copyOf(list, list.length + list.length / 2);
        }
        list[count] = user;
        count++;
    }

    @Override
    public User getUser(int id) {
        if (id < 0 || id >= count) throw new UserNotFoundException();
        return list[id];
    }

    @Override
    public User getUserByIdx(int idx) {
        if (idx < 0 || idx >= count) throw new UserNotFoundException();
        return list[idx];
    }

    @Override
    public int getCountUser() {
        return count;
    }
}