package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private final DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) {
        User result = null;
        String findByIdQuery = "SELECT * FROM service.users WHERE id = " + id;
        try (Connection connection = dataSource.getConnection();
             Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(findByIdQuery);
            if (!rs.next())
                result = new User(-1L, "do not exists");
            result = new User(rs.getLong(1), rs.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        String findAllQuery = "SELECT * FROM service.users;";
        try (Connection connection = dataSource.getConnection();
             Statement s = connection.createStatement()) {
            ResultSet rs = s.executeQuery(findAllQuery);
            while (rs.next()) {
                result.add(new User(rs.getLong(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return result;
    }

    @Override
    public void save(User entity) {
        String saveQuery = String.format("INSERT INTO service.users VALUES (%d , '%s');", entity.getId(), entity.getEmail());
        try (Connection connection = dataSource.getConnection();
             Statement ps = connection.createStatement()) {
            ps.execute(saveQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public void update(User entity) {
        String updateQuery = String.format("UPDATE service.users SET email = '%s' WHERE id = %d;", entity.getEmail(), entity.getId());
        try (Connection connection = dataSource.getConnection();
             Statement s = connection.createStatement()) {
            s.execute(updateQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }

    @Override
    public void delete(Long id) {
        String removeQuery = "DELETE FROM service.users WHERE id = " + id;
        try (Connection connection = dataSource.getConnection();
             Statement ps = connection.createStatement()) {
            ps.execute(removeQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String findByEmailQuery = String.format("SELECT * FROM service.users WHERE email = '%s';", email);
        Optional<User> result = Optional.empty();
        try (Connection connection = dataSource.getConnection();
        Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(findByEmailQuery);
            if (rs.next())
                result = Optional.of(new User(rs.getLong(1), rs.getString(2)));
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return result;
    }
}
