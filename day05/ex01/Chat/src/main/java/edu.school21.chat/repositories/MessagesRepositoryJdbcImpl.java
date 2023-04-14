package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.models.Chatroom;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private Connection con;
    private final String databaseRequest = "SELECT * FROM chat.%s WHERE id=%d";

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
            PreparedStatement stmt = con.prepareStatement(String.format(databaseRequest, "message", id));
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

    private User getUser(long id) throws SQLException {
        PreparedStatement stmt = con.prepareStatement(String.format(databaseRequest, "user", id));
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            throw new SQLException("Cannot find requested user");
        }
        return new User(rs.getInt("id"), rs.getString("login"), rs.getString("password"));
    }

    private Chatroom getRoom(long id) throws SQLException {
        PreparedStatement stmt = con.prepareStatement(String.format(databaseRequest, "chatroom", id));
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            throw new SQLException("Cannot find requested chatroom");
        }
        return new Chatroom(rs.getInt("id"), rs.getString("name"), getUser(rs.getInt("owner")));
    }

}
