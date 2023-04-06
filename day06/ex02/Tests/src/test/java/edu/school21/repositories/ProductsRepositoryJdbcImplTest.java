package edu.school21.repositories;

import edu.school21.models.Product;
import edu.school21.numbers.IllegalNumberException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ProductsRepositoryJdbcImplTest {

    private DataSource ds;
    private ProductsRepositoryJdbcImpl productsRepositoryJdbc;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS_PAGE = Arrays.asList(
            new Product(1, "milk", 50),
            new Product(2, "coconut", 300));
    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1, "milk", 50),
            new Product(2, "coconut", 300),
//            new Product(3, "juice", 100),
            new Product(4, "chips", 200),
            new Product(5, "tomato", 100),
            new Product(6, "pelmeni", 400),
            new Product(7, "cookie", 200),
            new Product(8, "souse", 120));

    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(3, "juice", 100);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(3, "milkshake", 200);
    final Product EXPECTED_SAVED_PRODUCT = new Product(8, "souse", 120);

    public static void assertProducts(Product expected, Product actual) {
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getName(), actual.getName());
        Assertions.assertEquals(expected.getPrice(), actual.getPrice());
    }

    @BeforeEach
    public void init() {
        ds = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        productsRepositoryJdbc = new ProductsRepositoryJdbcImpl(ds);
    }

    @Test
    void getConnectionTest() throws SQLException {
        Assertions.assertNotNull(ds.getConnection());
    }

    @Test
    void findAllTest() {
        List<Product> actualList = productsRepositoryJdbc.findAll(0, 2);
        for (int i = 0; i < 2; i++) {
            assertProducts(EXPECTED_FIND_ALL_PRODUCTS_PAGE.get(i), actualList.get(i));
        }
        Assertions.assertThrows(IllegalNumberException.class, () ->{
           productsRepositoryJdbc.findAll(-1, 0);
        });
    }

    @Test
    void findByIdTest() throws Exception {
        assertProducts(EXPECTED_FIND_BY_ID_PRODUCT, productsRepositoryJdbc.findById(3).get());
        Assertions.assertThrows(SQLException.class, () ->{
            productsRepositoryJdbc.findById(10);
        });
    }

    @Test
    void updateTest() throws Exception {
        productsRepositoryJdbc.update(EXPECTED_UPDATED_PRODUCT);
        assertProducts(EXPECTED_UPDATED_PRODUCT, productsRepositoryJdbc.findById(3).get());
    }

    @Test
    void saveTest() throws Exception {
        productsRepositoryJdbc.save(EXPECTED_SAVED_PRODUCT);
        assertProducts(EXPECTED_SAVED_PRODUCT, productsRepositoryJdbc.findById(8).get());
    }

    @Test
    void delete() {
        LinkedList<Product> expectedList = new LinkedList<>();
        expectedList.addAll(EXPECTED_FIND_ALL_PRODUCTS);

        expectedList.removeLast();
        productsRepositoryJdbc.delete(3L);

        List<Product> actualList = productsRepositoryJdbc.findAll(0, 7);

        for (int i = 0; i < expectedList.size(); i++) {
            assertProducts(expectedList.get(i), actualList.get(i));
        }
    }

}