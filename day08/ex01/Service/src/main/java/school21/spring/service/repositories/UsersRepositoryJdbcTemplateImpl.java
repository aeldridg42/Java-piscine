package school21.spring.service.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private JdbcTemplate jdbcTemplate;

    private static final class UserMapper implements RowMapper<User> {
        public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return (new User(resultSet.getLong(1),
                    resultSet.getString(2)));
        }
    }

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User findById(Long id) {
        String query = "SELECT * FROM service.users WHERE id = " + id;
        return jdbcTemplate.queryForObject(query, new UserMapper());
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM service.users;",
                new UserMapper());
    }

    @Override
    public void save(User entity) {
        String query = String.format("INSERT INTO service.users VALUES (%d , '%s');", entity.getId(), entity.getEmail());
        jdbcTemplate.update(query);
    }

    @Override
    public void update(User entity) {
        String query = String.format("UPDATE service.users SET email = '%s' WHERE id = %d;", entity.getEmail(), entity.getId());
        jdbcTemplate.update(query);
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM service.users WHERE id = " + id;
        jdbcTemplate.update(query);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String query = String.format("SELECT * FROM service.users WHERE email = '%s';", email);
        User res = jdbcTemplate.query(query, new UserMapper()).stream().findFirst().orElse(null);
        return res != null ? Optional.of((res)) : Optional.empty();
    }
}
