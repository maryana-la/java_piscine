package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariConfig;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Program {

    private static HikariDataSource dataSource;
    public static void main(String[] args) {
        setUpConnection();
        MessagesRepository msgRepo = new MessagesRepositoryJdbcImpl(dataSource);

        User creator = new User(2L, "user", "passwordsafe", new ArrayList<>(), new ArrayList<>());
        Chatroom room = new Chatroom(4L, "Picnic", creator, new ArrayList<>());
        Message message = new Message(null, creator, room, "Hello! Test", LocalDateTime.now());
        msgRepo.save(message);
        System.out.println(message.getId()); // ex. id == 11
        dataSource.close();
    }

    private static void setUpConnection() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("postgres");
        config.setPassword("postgres");
        config.addDataSourceProperty("cachePrepStmts" , "true");
        config.addDataSourceProperty("prepStmtCacheSize" , "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit" , "2048");
        dataSource = new HikariDataSource(config);
        try {
            dataSource.getConnection();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.exit(-1);
        }
    }
}

