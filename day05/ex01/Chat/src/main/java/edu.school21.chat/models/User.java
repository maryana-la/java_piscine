package edu.school21.chat.models;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class User {
    private long id;
    private String login;
    private String password;
    private List<Chatroom> createdRooms;
    private List<Chatroom> joinedRooms;

    public User(long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = new ArrayList<>();
        this.joinedRooms = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public List<Chatroom> getCreatedRooms() {
        return createdRooms;
    }

    public List<Chatroom> getJoinedRooms() {
        return joinedRooms;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreatedRooms(List<Chatroom> createdRooms) {
        this.createdRooms = createdRooms;
    }

    public void setJoinedRooms(List<Chatroom> joinedRooms) {
        this.joinedRooms = joinedRooms;
    }

    public void addCreatedRooms(Chatroom newRoom) {
        createdRooms.add(newRoom);
    }

    public void addJoinedRooms(Chatroom newRoom) {
        joinedRooms.add(newRoom);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        if (this == obj) return true;
        User user = (User) obj;
        return this.getId() == user.getId() && this.getLogin().equals(user.getLogin()) && this.getCreatedRooms().equals(user.getCreatedRooms()) && this.getJoinedRooms().equals(user.getJoinedRooms());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, createdRooms, joinedRooms);
    }

    @Override
    public String toString() {
        return "user: { id=" + id + ", login=" + login + ", password=" + password + ", created chats=" + printChatList(createdRooms) + ", joined chats=" + printChatList(joinedRooms) + " }";
    }

    private String printChatList(List<Chatroom> chats) {
        if (chats.size() == 0) {
            return "null";
        }
        StringBuilder output = new StringBuilder("{");
        for (Chatroom tmp : chats) {
            output.append(tmp.getName()).append(" ");
        }
        output.append("}");
        return output.toString();
    }
}
