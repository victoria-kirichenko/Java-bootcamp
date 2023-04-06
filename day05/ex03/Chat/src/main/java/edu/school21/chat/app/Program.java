package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.util.*;

public class Program {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
        String user = "postgres";
        String password = "123";
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);
        DataSource ds = new HikariDataSource(config);
        try {
            MessagesRepositoryJdbcImpl o = new MessagesRepositoryJdbcImpl(ds);
            Optional<Message> messageOpt = o.findById(2);
            Message message = messageOpt.get();
            if (messageOpt.isPresent()) {
                message.setMessageText("03 task sucks");
                message.setTimeDate(null);
                o.update(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
