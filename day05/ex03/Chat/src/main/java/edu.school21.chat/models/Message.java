package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Message {
    private Long id;
    private User author;
    private Chatroom room;
    private String text;
    private LocalDateTime dateTime;

    public Message(Long id, User author, Chatroom room, String text, LocalDateTime dateTime) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.dateTime = dateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setAuthor(User user) {
        this.author = user;
    }

    public void setRoom(Chatroom room) {
        this.room = room;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public Chatroom getRoom() {
        return room;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Message message = (Message) obj;
        return Objects.equals(getId(), message.getId()) && Objects.equals(getAuthor(), message.getAuthor()) && Objects.equals(getRoom(), message.getRoom()) && Objects.equals(getText(), message.getText()) && Objects.equals(getDateTime(), message.getDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthor(), getRoom(), getText(), getDateTime());
    }

    @Override
    public String toString() {
        return "Message : {\nid=" + id + "\n" + author + "\n" + room + "\ntext= '" + text + "'" + "\ntime=" + dateTime.toString() + "\n}";
    }
}