package edu.school21.chat.repositories;

import edu.school21.chat.models.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository{
    private DataSource ds;

    public UsersRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<User> findAll(int page, int size) {
        List<User> users = new LinkedList<User>();

        String query = "select * from chat.\"User\" limit ? offset ?";

        try (Connection conn = ds.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {
            st.setLong(1, size); // limit
            st.setLong(2, (long) page * size); // offset

            st.execute();

            ResultSet resultSet = st.getResultSet();

            while (resultSet.next()) {
                users.add(new User(
                                resultSet.getInt("id"),
                                resultSet.getString("login"),
                                resultSet.getString("password")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }
}