package edu.school21.chat.repositories;

import edu.school21.chat.app.NotSavedSubEntityException;
import edu.school21.chat.models.ChatRoom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private DataSource ds;

    public MessagesRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Optional<Message> findById(int id) throws SQLException {
        String str = "select * from chat.\"Message\" where id = " + id + ";";
        Connection conn = ds.getConnection();
        Statement stat = conn.createStatement();
        ResultSet result = stat.executeQuery(str);
        result.next();
        int idU = result.getInt("id");
        int chatId = result.getInt("chat_id");
        int author = result.getInt("author");
        String message_text = result.getString("message_text");
        String time_date = result.getString("time_date");
        User user = findUser(author);
        ChatRoom room = findChatRoom(chatId);
        return Optional.of(new Message(idU, room, user, message_text, time_date));
    }

    @Override
    public void save(Message message) throws NotSavedSubEntityException, SQLException {
        Connection conn = ds.getConnection();
        Statement stat = conn.createStatement();
        stat.executeUpdate("insert into chat.\"Message\"(chat_id, author, message_text, time_date) values (" + message.getChatName().getId() + "," + message.getAuthor().getId() + ",'"
        + message.getMessageText() + "','" + message.getTimeDate() + "');");
    }

    @Override
    public void update(Message message) throws SQLException, NullPointerException {
        Optional<Message> messageOrig = findById(message.getId());
        Connection conn = ds.getConnection();
        PreparedStatement st = conn.prepareStatement("update chat.\"Message\" set chat_id = ?, author = ?, message_text = ?, time_date = ? where id = ?");
        if (message.getChatName() == null) {
            st.setInt(1, messageOrig.get().getChatName().getId());
        } else {
            st.setInt(1, message.getChatName().getId());
        }
        if (message.getAuthor() == null) {
            st.setInt(2, messageOrig.get().getAuthor().getId());
        } else {
            st.setInt(2, message.getAuthor().getId());
        }
        if (message.getMessageText() == null) {
            st.setString(3, messageOrig.get().getMessageText());
        } else {
            st.setString(3, message.getMessageText());
        }
        if (message.getTimeDate() == null) {
            st.setTimestamp(4, Timestamp.valueOf(messageOrig.get().getTimeDate()));
        } else {
            st.setTimestamp(4, Timestamp.valueOf(message.getTimeDate()));
        }
        st.setInt(5, message.getId());
        st.executeUpdate();
    }


    private User findUser(int author) throws SQLException {
        String str = "select * from chat.\"User\" where id = " + author + ";";
        Connection conn = ds.getConnection();
        Statement stat = conn.createStatement();
        ResultSet result = stat.executeQuery(str);
        result.next();
        int id = result.getInt("id");
        String login = result.getString("login");
        String password = result.getString("password");
        User user = new User(id, login, password);
        return user;
    }

    private ChatRoom findChatRoom(int chatId) throws SQLException {
        String str = "select * from chat.\"ChatRoom\" where id = " + chatId + ";";
        Connection conn = ds.getConnection();
        Statement stat = conn.createStatement();
        ResultSet result = stat.executeQuery(str);
        result.next();
        int id = result.getInt("id");
        String name = result.getString("name");
        int owner = result.getInt("owner");
        User user = findUser(owner);
        ChatRoom room = new ChatRoom(id, name, user);
        return room;
    }
}