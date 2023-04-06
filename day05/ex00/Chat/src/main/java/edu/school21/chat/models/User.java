package edu.school21.chat.models;

import java.util.LinkedList;
import java.util.List;

public class User {
    private int id;
    private String login;
    private String password;
    private List<ChatRoom> createdRooms = new LinkedList<>();
    private List<ChatRoom> activeRooms = new LinkedList<>();

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public int hashCode() {
        int total = 31;
        total = total * 31 + id;
        total = total * 31 + login.hashCode();
        total = total * 31 + password.hashCode();
        total = total * 31 + createdRooms.hashCode();
        total = total * 31 + activeRooms.hashCode();
        return total;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        User tmp = (User) o;

        if (this.id == tmp.id && this.login.equals(tmp.login) && this.password.equals(tmp.password)
                && this.createdRooms.equals(tmp.createdRooms) && this.activeRooms.equals(tmp.activeRooms)) {
            result = true;
        }
        return result;
    }

    @Override
    public String toString() {
        String str = "ID: " + id + ", Login: " + login + ",\nCreated Chats:\n {" + createdRooms.toString() + "}, \nActive Chats:\n {" + activeRooms.toString() + "}";
        return str;
    }
}