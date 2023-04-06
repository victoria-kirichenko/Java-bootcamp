package edu.school21.spring.service.repositories;

import edu.school21.spring.service.models.User;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private DataSource ds;

    public UsersRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public User findById(Long id) {
        String query = "select * from users where id = ?";
        try (Connection conn = ds.getConnection()) {
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setLong(1, id);
            ResultSet resultSet = stat.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        String query = "select * from users";
        List<User> userList = new ArrayList<>();
        try (Connection conn = ds.getConnection()) {
            Statement stat = conn.createStatement();
            ResultSet resultSet = stat.executeQuery(query);
            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email")));
            }
            return userList;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void save(User entity) {
        String query = "insert into users (id, email) values (?, ?)";
        try (Connection conn= ds.getConnection()) {
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setLong(1, entity.getId());
            stat.setString(2, entity.getEmail());
            stat.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        String query = "update users set email = ? where id = ?";

        try (Connection conn = ds.getConnection()) {
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setString(1, entity.getEmail());
            stat.setLong(2, entity.getId());
            stat.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String query = "delete from users where id = ?";
        try (Connection conn = ds.getConnection()) {
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setLong(1, id);
            stat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String query = "select * from users where email = ?";

        try (Connection conn = ds.getConnection()) {
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setString(1, email);
            ResultSet resultSet = stat.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email")));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return Optional.empty();
    }
}