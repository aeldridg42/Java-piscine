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
        User user = null;
        Chatroom room = null;
        String text = null;
        LocalDateTime time = null;
        long userId = 0L;
        long roomId = 0L;

        try ( Connection connection = ds.getConnection();
            Statement statement = connection.createStatement()) {
            String request = "SELECT * FROM chat.messages WHERE id = " + id;
            ResultSet resultSet = statement.executeQuery(request);
            if (!resultSet.next())
                return Optional.of(new Message(null, null, null, "do not exists", null));
            userId = resultSet.getLong(3);
            roomId = resultSet.getLong(2);
            time = resultSet.getTimestamp(5).toLocalDateTime();
            text = resultSet.getString(4);

            request = "SELECT * FROM chat.users WHERE id = " + userId;
            resultSet = statement.executeQuery(request);
            resultSet.next();
            user = new User(userId, resultSet.getString(2), resultSet.getString(3), null, null);

            request = "SELECT * FROM chat.room WHERE id = " + roomId;
            resultSet = statement.executeQuery(request);
            resultSet.next();

            room = new Chatroom(roomId, resultSet.getString(3), null, null);

            opMessage = Optional.of(new Message(id, user, room, text, time));
        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return opMessage;
    }
}
