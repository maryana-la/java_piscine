package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;

import javax.sql.DataSource;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public Optional<Message> findById(Long id) {
        Optional<Message> tmp = Optional.empty();
        return tmp;
    }
}
