package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.ChatRoom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
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
            User creator = new User(1, "vika", "123");
            User creator2 = new User(3, "julia", "789");
            ChatRoom room = new ChatRoom(2, "study", creator2);
            Message message = new Message(10, room, creator, "ska java sql 05 day sucks", "2023-03-21 17:42:00");
            o.save(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
     public static void printMessage(Optional<Message> m) {
         System.out.println("Message : {\nid=" + m.get().getId() + ",\nauthor={id=" + m.get().getAuthor().getId()
                 + ",login=\"" + m.get().getAuthor().getLogin() + "\",password=\""
                 + m.get().getAuthor().getPassword() + "\",createdRooms=null,rooms=null},\nroom={id="
                 + m.get().getChatName().getId() + ",name=\"" + m.get().getChatName().getName()
                 + "\",creator=null,messages=null},\ntext=\"" + m.get().getMessageText() + "\",\ndateTime="
                 + m.get().getTimeDate() + "\n}");
     }
}
