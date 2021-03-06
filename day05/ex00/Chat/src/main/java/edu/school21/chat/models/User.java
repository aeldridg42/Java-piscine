package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String password;
    List<Chatroom> rooms;
    List<Chatroom> socializedRooms;

    public User(Long id, String login, String password, List<Chatroom> rooms, List<Chatroom> socializedRooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.rooms = rooms;
        this.socializedRooms = socializedRooms;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", rooms=" + rooms +
                ", socializedRooms=" + socializedRooms +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(rooms, user.rooms) && Objects.equals(socializedRooms, user.socializedRooms);
    }

    @Override
    public int hashCode() { return Objects.hash(id, login, password, rooms, socializedRooms); }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRooms(List<Chatroom> rooms) {
        this.rooms = rooms;
    }

    public void setSocializedRooms(List<Chatroom> socializedRooms) {
        this.socializedRooms = socializedRooms;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Chatroom> getRooms() {
        return rooms;
    }

    public List<Chatroom> getSocializedRooms() {
        return socializedRooms;
    }
}
