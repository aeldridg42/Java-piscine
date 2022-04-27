package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    private DataSource dataSource;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM products";
            List<Product> result = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                result.add(new Product(resultSet.getLong(1),
                        resultSet.getString(2), resultSet.getInt(3)));
            }
            return result;
        }
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM products WHERE id = " + id + ";";
            ResultSet resultSet = statement.executeQuery(query);
            Optional<Product> result = Optional.empty();
            if (resultSet.next())
                result = Optional.of(new Product(resultSet.getLong(1),
                        resultSet.getString(2), resultSet.getInt(3)));
            return result;
        }
    }

    @Override
    public void update(Product product) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String query = String.format("UPDATE products SET name = '%s', price = %d" +
                    " WHERE id = %d;", product.getName(), product.getPrice(), product.getId());
            statement.execute(query);
        }

    }

    @Override
    public void save(Product product) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into products VALUES (?, ?, ?)")) {
            statement.setLong(1, product.getId());
            statement.setString(2, product.getName());
            statement.setInt(3, product.getPrice());
            statement.execute();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String query = "DELETE FROM products WHERE id = " + id + ";";
            statement.execute(query);
        }
    }
}
