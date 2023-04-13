package edu.school21.chat.app;

import javax.sql.DataSource;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariConfig;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;

public class Program {
    public static void main(String[] args) {

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        dataSource.addDataSourceProperty( "cachePrepStmts" , "true" );
        dataSource.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        dataSource.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );

        System.out.println("hello world");

        try {
            dataSource.getConnection();
            dataSource.close();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage() + "error");
        }


        System.out.print("Enter a message ID\n-> ");

        MessagesRepository msgs = new MessagesRepositoryJdbcImpl(dataSource);

    }
}
/*
jdbc:postgresql:database
        jdbc:postgresql:/
        jdbc:postgresql://host/database
        jdbc:postgresql://host/
        jdbc:postgresql://host:port/database
        jdbc:postgresql://host:port/
        The parameters have the following meanings:

        host = The host name of the server. Defaults to localhost . To specify an IPv6 address your must enclose the host parameter with square brackets, for example: jdbc:postgresql://[::1]:5740/accounting

        port = The port number the server is listening on. Defaults to the PostgreSQL® standard port number (5432).

        database = The database name. The default is to connect to a database with the same name as the user name used to connect to the server.

        */


//        HikariConfig config = new HikariConfig();
//
//        config.setJdbcUrl( "jdbc:postgresql://localhost:5432/java_piscine" );
//        config.setUsername( "rchelsea" );
//        config.setPassword( "1234" );
//        config.addDataSourceProperty( "cachePrepStmts" , "true" );
//        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
//        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );

//        try (HikariDataSource ds = new HikariDataSource(config)) {
//            System.out.println("hello world");
//        } catch (Exception e) {
//            System.out.println(e.getLocalizedMessage());
//        }
