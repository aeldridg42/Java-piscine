package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository{
    private final HikariDataSource ds;

    public MessagesRepositoryJdbcImpl(HikariDataSource ds) {
        this.ds = ds;
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        Optional<Message> opMessage;

        try ( Connection connection = ds.getConnection();
            Statement statement = connection.createStatement()) {
            String request = "SELECT * FROM chat01.messages WHERE id = " + id;
            ResultSet resultSet = statement.executeQuery(request);
            if (!resultSet.next())
                return Optional.of(new Message(0,null, null, "null", null));

            User user = new User(1, "qwerty", "qwerty", null, null);
            Chatroom chatroom = new Chatroom(1, "chatroom1", null, null);
            opMessage = Optional.of(new Message(resultSet.getInt(1), user, chatroom, resultSet.getString("message"), LocalDateTime.of(2014, 9, 19, 14, 5)));
        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return opMessage;
    }
}
