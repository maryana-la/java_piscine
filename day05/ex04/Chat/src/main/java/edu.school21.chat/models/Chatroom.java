package edu.school21.chat.models;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Chatroom {
    private Long id;
    private String name;
    private User owner;
    private List<Message> messageList;

    public Chatroom(Long id, String name, User owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        messageList = new ArrayList<>();
    }

    public Chatroom(Long id, String name, User owner, List<Message> messageList) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.messageList = messageList;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public void addMessage(Message newMsg) {
        messageList.add(newMsg);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Chatroom chatroom = (Chatroom) obj;
        return Objects.equals(getId(), chatroom.getId()) && Objects.equals(getName(), chatroom.getName()) && Objects.equals(getOwner(), chatroom.getOwner()) && Objects.equals(getMessageList(), chatroom.getMessageList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getOwner(), getMessageList());
    }

    @Override
    public String toString() {
        return "chat: { id=" + id + ", name=" + name + ", owner=" + owner.getLogin() + ", messages=" + getMessageListToString() + " }";
    }
    private String getMessageListToString() {
        if (messageList.size() == 0) {
            return "null";
        }
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < messageList.size(); i++) {
            if (i +1 == messageList.size()) {
                output.append(messageList.get(i).toString());
            } else {
                output.append(messageList.get(i).toString()).append("\n");
            }
        }
        return output.toString();
    }
}
