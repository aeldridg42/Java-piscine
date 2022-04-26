package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;

import java.sql.SQLException;
import java.util.Optional;

class NotSavedSubEntityException extends RuntimeException {
    @Override
    public String toString() {
        return "Couldn't save the message";
    }
}

public interface MessagesRepository {
    public Optional<Message> findById(Long id) throws SQLException;
    public void save(Message message) throws SQLException;
}