package src.main.java.edu.school.chat.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Message {
    private final int id;
    private final User author;
    private final Chatroom room;
    private final String text;
    private final LocalDateTime dateTime;

    public Message(int id, User author, Chatroom room, String text, LocalDateTime dateTime) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.dateTime = dateTime;
    }

    public int getId() {
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
        if (!(obj instanceof Message message)) return false;
        return getId() == message.getId() && Objects.equals(getAuthor(), message.getAuthor()) && Objects.equals(getRoom(), message.getRoom()) && Objects.equals(getText(), message.getText()) && Objects.equals(getDateTime(), message.getDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthor(), getRoom(), getText(), getDateTime());
    }

    @Override
    public String toString() {
        return "Message: " + "id=" + id + ", author=" + author.getLogin() + ", room=" + room.getName() + ", text='" + text + '\'' + ", dateTime=" + dateTime;
    }
}
