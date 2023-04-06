package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.ChatRoom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws FileNotFoundException {
        String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
        String user = "postgres";
        String password = "123";
        String script1 = "";
        String script2 = "";
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);
        DataSource ds = new HikariDataSource(config);
//        Scanner sc = new Scanner(new File("src/main/resources/schema.sql"));
//        while (sc.hasNext()) {
//            script1 += sc.nextLine() + "\r\n";
//        }
//        sc.close();
//        sc = new Scanner(new File("src/main/resources/data.sql"));
//        while (sc.hasNext()) {
//            script2 += sc.nextLine() + "\r\n";
//        }
//        sc.close();
        try {
            System.out.print("Enter a message ID\n-> ");
            Scanner sc = new Scanner(System.in);
            MessagesRepositoryJdbcImpl o = new MessagesRepositoryJdbcImpl(ds);
            printMessage(o.findById(sc.nextInt()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public static List<User> getUserFromBD() throws SQLException {
//        List<User> users = new LinkedList<>();
//        ResultSet result = stat.executeQuery("select * from chat.\"User\";");
//        while (result.next()) {
//            int id = result.getInt("id");
//            String login = result.getString("login");
//            String password = result.getString("password");
//            users.add(new User(id, login, password));
//        }
//        return users;
//    }
//
//    public static List<ChatRoom> getChatRoomFromBD() throws SQLException {
//        List<ChatRoom> chatRooms = new LinkedList<>();
//        ResultSet result = stat.executeQuery("select * from chat.\"ChatRoom\";");
//        while (result.next()) {
//            int id = result.getInt("id");
//            String name = result.getString("name");
//            int owner = result.getInt("owner");
//            User user = findUser(owner);
//            chatRooms.add(new ChatRoom(id, name, user));
//        }
//        return chatRooms;
//    }
//
//    public static User findUser(int owner) {
//        for (int i = 0; i < users.size(); i++) {
//            if (users.get(i).getId() == owner) {
//                return users.get(i);
//            }
//        }
//        return users.get(0);
//    }
//
//    public static ChatRoom findChatRoom(int chatId) {
//        for (int i = 0; i < chatRooms.size(); i++) {
//            if (chatRooms.get(i).getId() == chatId) {
//                return chatRooms.get(i);
//            }
//        }
//        return chatRooms.get(0);
//    }
//    public static List<Message> getMessageListFromBD() throws SQLException {
//        List<Message> messages = new LinkedList<>();
//        ResultSet result = stat.executeQuery("select * from chat.\"Message\";");
//        while (result.next()) {
//            int id = result.getInt("id");
//            int chatId = result.getInt("chat_id");
//            int author = result.getInt("author");
//            String message_text = result.getString("message_text");
//            String time_date = result.getString("time_date");
//            User user = findUser(author);
//            ChatRoom room = findChatRoom(chatId);
//            messages.add(new Message(id, room, user, message_text, time_date));
//        }
//        return messages;
//    }
//    public static void fillList() {
//        for (int i = 0; i < messages.size(); i++) {
//            for (int j = 0; j < chatRooms.size(); j++) {
//                if (messages.get(i).getChatName().getName() == chatRooms.get(j).getName()) {
//                    chatRooms.get(j).getMessageList().add(messages.get(i));
//                }
//            }
//
//        }
//        for (int i = 0; i < users.size(); i++) {
//            for (int j = 0; j < chatRooms.size(); j++) {
//                for (int k = 0; k < chatRooms.get(j).getMessageList().size(); k++) {
//                    if (users.get(i).getId() == chatRooms.get(j).getMessageList().get(k).getAuthor().getId()) {
//                        users.get(i).getRooms().add(chatRooms.get(j));
//                    }
//                }
//
//            }
//        }
//    }
     public static void printMessage(Optional<Message> m) {
         System.out.println("Message : {\nid=" + m.get().getId() + ",\nauthor={id=" + m.get().getAuthor().getId()
                 + ",login=\"" + m.get().getAuthor().getLogin() + "\",password=\""
                 + m.get().getAuthor().getPassword() + "\",createdRooms=null,rooms=null},\nroom={id="
                 + m.get().getChatName().getId() + ",name=\"" + m.get().getChatName().getName()
                 + "\",creator=null,messages=null},\ntext=\"" + m.get().getMessageText() + "\",\ndateTime="
                 + m.get().getTimeDate() + "\n}");
     }
}
