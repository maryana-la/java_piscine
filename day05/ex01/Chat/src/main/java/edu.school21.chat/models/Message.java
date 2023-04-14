package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Message {
    private final long id;
    private final User author;
    private final Chatroom room;
    private final String text;
    private final LocalDateTime dateTime;

    public Message(long id, User author, Chatroom room, String text, LocalDateTime dateTime) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.dateTime = dateTime;
    }

    public long getId() {
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
        return getId() == message.getId() && Objects.equals(getAuthor(), message.getAuthor()) && Objects.equals(getRoom(), message.getRoom()) && Objects.equals(getText(), message.getText()) && Objects.equals(getDateTime(), message.getDateTime());
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
