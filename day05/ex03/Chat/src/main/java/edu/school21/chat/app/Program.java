package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import edu.school21.chat.models.*;

public class Program {
    public static void main(String[] args) throws SQLException {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        ds.setUsername("postgres");
        ds.setPassword("");

        MessagesRepository mr = new MessagesRepositoryJdbcImpl(ds);

        User user = new User(2L, "admin", "admin", null, null);
        Chatroom chatroom = new Chatroom(1L, "chatik", user, null);

        MessagesRepository repositoryJdbc = new MessagesRepositoryJdbcImpl(ds);
        Optional<Message> message = repositoryJdbc.findById(1L);

        if (message.isPresent()) {
            message.get().setAuthor(user);
            message.get().setText("new text ura!");
            repositoryJdbc.update(message.get());
        } else {
            repositoryJdbc.save(new Message(1L, user, chatroom, "auto generated message", LocalDateTime.now()));
        }
        System.out.println(repositoryJdbc.findById(1L).get());
        ds.close();
    }
}