package edu.school21.repositories;

import edu.school21.models.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProductsRepository {
    public Optional<Product> findById(int id) throws SQLException;
    public void save(Product product);

    public void update(Product product) throws SQLException;

    public void delete(Long id);

    public List<Product> findAll(int page, int size);
}