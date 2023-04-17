package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.models.Chatroom;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private Connection con;
    private final String SELECT_FROM_CHAT = "SELECT * FROM chat.%s WHERE id=%d";

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        try {
            con = dataSource.getConnection();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Override
    public Optional<Message> findById(Long id) {
        Optional<Message> optionalMessage;
        try  {
            PreparedStatement stmt = con.prepareStatement(String.format(SELECT_FROM_CHAT, "message", id));
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                throw new SQLException("No message with the requested id");
            }
            User user = getUser(rs.getInt("author"));
            Chatroom room = getRoom(rs.getInt("room"));
            String text = rs.getString("text");
            LocalDateTime dateTime = rs.getObject("datetime", LocalDateTime.class);
            Message msg = new Message(id, user, room, text, dateTime);
            optionalMessage = Optional.of(msg);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return Optional.empty();
        }
        return optionalMessage;
    }

    @Override
    public void save(Message message) {
        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO chat.message (room, author, text) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, message.getRoom().getId());
            stmt.setLong(2, message.getAuthor().getId());
            stmt.setString(3, message.getText());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (!rs.next()) {
                throw new SQLException("Cannot find requested line.");
            }
            long tmp = rs.getLong("id");
            message.setId(tmp);
        } catch (Exception e) {
            throw new NotSavedSubEntityException();
        }
    }

    @Override
    public void update(Message message) {
        try {
            if (message.getRoom() == null || message.getAuthor() == null) {
                throw new NotSavedSubEntityException();
            }
            PreparedStatement stmt = con.prepareStatement(
                    "UPDATE chat.message SET " +
                            "room=?, " +
                            "author=?, " +
                            "text=?, " +
                            "datetime=COALESCE(datetime, ?) " +
                            "where id=?");
            stmt.setLong(1, message.getRoom().getId());
            stmt.setLong(2,message.getAuthor().getId());
            stmt.setString(3, message.getText());
            stmt.setTimestamp(4, message.getDateTime() == null ? null : Timestamp.valueOf(message.getDateTime()));
            stmt.setLong(5, message.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private User getUser(long id) throws SQLException {
        PreparedStatement stmt = con.prepareStatement(String.format(SELECT_FROM_CHAT, "user", id));
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            throw new SQLException("Cannot find requested user");
        }
        return new User(rs.getLong("id"), rs.getString("login"), rs.getString("password"));
    }

    private Chatroom getRoom(long id) throws SQLException {
        PreparedStatement stmt = con.prepareStatement(String.format(SELECT_FROM_CHAT, "chatroom", id));
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            throw new SQLException("Cannot find requested chatroom");
        }
        return new Chatroom(rs.getLong("id"), rs.getString("name"), getUser(rs.getInt("owner")));
    }
}
