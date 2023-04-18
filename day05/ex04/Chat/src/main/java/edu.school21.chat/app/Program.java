package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import edu.school21.chat.repositories.UsersRepository;
import edu.school21.chat.repositories.UsersRepositoryJdbcImpl;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariConfig;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Program {

    private static HikariDataSource dataSource;
    public static void main(String[] args) {
        setUpConnection();
        MessagesRepository msgRepo = new MessagesRepositoryJdbcImpl(dataSource);

        UsersRepository userRepo = new UsersRepositoryJdbcImpl(dataSource);
        List<User> users = userRepo.findAll(1, 10);
        System.out.println(users);

 /*       Optional<Message> messageOptional = msgRepo.findById(11L);
        System.out.println(messageOptional);
        if (messageOptional.isPresent()) {
            Message msg = messageOptional.get();
            msg.setText("Spartaaaa");
            msg.setDateTime(null);
//          msg.setAuthor(null);
            msgRepo.update(msg);
            System.out.println(msgRepo.findById(11L));
        }
  */
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

