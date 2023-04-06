package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.UsersRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.util.*;

public class Program {

    public static void main(String[] args) throws FileNotFoundException {
        String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
        String user = "postgres";
        String password = "123";
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);
        DataSource ds = new HikariDataSource(config);
        try {
            UsersRepositoryJdbcImpl o = new UsersRepositoryJdbcImpl(ds);
            List<User> users = o.findAll(0, 2);
            System.out.println(users);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
