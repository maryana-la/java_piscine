package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariConfig;
import java.util.Optional;
import java.util.Scanner;

public class Program {

    private static HikariDataSource dataSource;
    public static void main(String[] args) {

        setUpConnection();
        MessagesRepository msgRepo = new MessagesRepositoryJdbcImpl(dataSource);

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a message ID\n-> ");
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if ("exit".equals(input)) {
                break;
            }
            try {
                long num = Long.parseLong(input);
                Optional<Message> opt = msgRepo.findById(num);
                opt.ifPresent(System.out::println);
            }
            catch (Exception e) {
                System.out.println("Invalid number. " + e.getLocalizedMessage());
            }
            System.out.print("Enter a message ID\n-> ");
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
