package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import edu.school21.chat.models.*;

public class Program {
    public static void main(String[] args) throws SQLException {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        ds.setUsername("postgres");
        ds.setPassword("");

        MessagesRepository mr = new MessagesRepositoryJdbcImpl(ds);

        User author = new User(1L, "qwerty", "123456", new ArrayList(), new ArrayList());
        Chatroom room = new Chatroom(1L, "chat1", author, new ArrayList());

        Message msg = new Message(null, author, room, "asdscxzx", LocalDateTime.now());

        mr.save(msg);

        System.out.println(msg.getId());
        ds.close();
    }
}