package school21.spring.service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component("usersRepositoryJdbcImpl")
public class UsersRepositoryJdbcImpl implements UsersRepository {
    private DataSource ds;

    @Autowired
    public UsersRepositoryJdbcImpl(@Qualifier("hikariDataSource") DataSource ds) {
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
                        resultSet.getString("email"),
                        resultSet.getString("password")));
            }
            return userList;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void save(User entity) {
        String query = "insert into users (id, email, password) values (?, ?, ?)";
        try (Connection conn= ds.getConnection()) {
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setLong(1, entity.getId());
            stat.setString(2, entity.getEmail());
            stat.setString(3, entity.getPassword());
            stat.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        String query = "update users set email = ?, password = ? where id = ?";

        try (Connection conn = ds.getConnection()) {
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setString(1, entity.getEmail());
            stat.setString(2, entity.getPassword());
            stat.setLong(3, entity.getId());
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