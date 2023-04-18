package edu.school21.chat.app;

import edu.school21.chat.models.User;
import edu.school21.chat.repositories.UsersRepository;
import edu.school21.chat.repositories.UsersRepositoryJdbcImpl;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariConfig;
import java.util.List;;

public class Program {

    private static HikariDataSource dataSource;
    public static void main(String[] args) {
        setUpConnection();
        UsersRepository userRepo = new UsersRepositoryJdbcImpl(dataSource);
        List<User> users = userRepo.findAll(2, 2);
        for (User x : users) {
            System.out.println(x);
        }
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

