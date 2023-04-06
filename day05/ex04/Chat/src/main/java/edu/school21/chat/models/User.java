package edu.school21.chat.models;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String login;
    private String password;
    private List<ChatRoom> Rooms = new LinkedList<>();

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public List<ChatRoom> getRooms() {
        return Rooms;
    }

    public String getPassword() {
        return password;
    }

    public void setRooms(List<ChatRoom> rooms) {
        Rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(Rooms, user.Rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, Rooms);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", Rooms=" + Rooms +
                '}';
    }
}