package edu.school21.repositories;

import edu.school21.models.Product;
import edu.school21.exceptions.AlreadyAuthenticatedException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    private DataSource ds;

    public ProductsRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }
    @Override
    public Optional<Product> findById(int id) throws SQLException {
        String str = "select * from product where id = " + id + ";";
        Connection conn = ds.getConnection();
        Statement stat = conn.createStatement();
        ResultSet result = stat.executeQuery(str);
        result.next();
        int idU = result.getInt("id");
        String name = result.getString("name");
        int price = result.getInt("price");
        return Optional.of(new Product(idU, name, price));
    }

    @Override
    public void save(Product product) {
        try (Connection conn = ds.getConnection();
             Statement stat = conn.createStatement()) {
            stat.executeUpdate("insert into product (id, name, price) values (" + product.getId() + ",'" + product.getName() + "', "
                    + product.getPrice() + ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Product product) throws SQLException {
        Optional<Product> productOrig = findById(product.getId());
        String query = "update product set name = ?, price = ? where id = ?";
        try (Connection conn = ds.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {
            if (product.getName() == null) {
                st.setString(1, productOrig.get().getName());
            } else {
                st.setString(1, product.getName());
            }
            if (product.getPrice() <= 0) {
                st.setInt(2, productOrig.get().getPrice());
            } else {
                st.setInt(2, product.getPrice());
            }
            st.setInt(3, product.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String query = "delete from product where id = ?";
        try (Connection conn = ds.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {
            st.setLong(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll(int page, int size) {
        List<Product> products = new LinkedList<Product>();
        String query = "select * from product limit ? offset ?";
        if (page < 0 || size < 0) {
            throw new AlreadyAuthenticatedException();
        }
        try (Connection conn = ds.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {
            st.setLong(1, size);
            st.setLong(2, (long) page * size);
            st.execute();
            ResultSet resultSet = st.getResultSet();
            while (resultSet.next()) {
                products.add(new Product(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("price")
                        )
                );
            }
        } catch (SQLException e) {
            throw new AlreadyAuthenticatedException();
        }
        return products;
    }
}