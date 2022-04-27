package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class ProductsRepositoryJdbcImplTest {
    private EmbeddedDatabase dataSource;
    private ProductsRepository productsRepository;
    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1L, "qwerty1", 123),
            new Product(2L, "qwerty2", 234),
            new Product(3L, "qwerty3", 345),
            new Product(4L, "qwerty4", 456),
            new Product(5L, "qwerty5", 567)
    );

    final Product EXPECTED_FIND_BY_ID_PRODUCT = EXPECTED_FIND_ALL_PRODUCTS.get(3);
    final Product EXPECTED_UPDATED_PRODUCT = EXPECTED_FIND_ALL_PRODUCTS.get(1);

    @BeforeEach
    void init() {
        dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql").addScript("data.sql")
                .build();
        productsRepository = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    void findAllShouldReturnCorrectList() throws SQLException {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productsRepository.findAll());
    }

    @Test
    void findByIdShouldReturnCorrectProduct() throws SQLException {
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, productsRepository.findById(4L).get());
    }

    @Test
    void updateShouldCorrectlyUpdate() throws SQLException {
        EXPECTED_UPDATED_PRODUCT.setName("NEW NAME");
        productsRepository.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, productsRepository.findById(EXPECTED_UPDATED_PRODUCT.getId()).get());
    }

    @Test
    void deleteShouldCorrectlyDelete() throws SQLException {
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, productsRepository.findById(4L).get());
        productsRepository.delete(4L);
        Assertions.assertEquals(productsRepository.findById(4L), Optional.empty());
    }

    @Test
    void saveShouldCorrectlySave() throws SQLException {
        Assertions.assertEquals(productsRepository.findById(11L), Optional.empty());
        Product save = new Product(11L, "I'am new one", 777);
        productsRepository.save(save);
        Assertions.assertEquals(productsRepository.findById(11L).get(), save);
    }

    @AfterEach
    void shutDown() {
        dataSource.shutdown();
    }

}
