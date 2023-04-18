package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private Connection con;
    List<User> users;
    List<Chatroom> chats;
    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        try {
            con = dataSource.getConnection();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        users = new ArrayList<>();
        chats = new Vector<>();
    }

    @Override
    public List<User> findAll(int page, int size) {

        String sq = "WITH paginated_users AS ( " +
                    "select * from chat.user " +
                    "offset ? limit ? ), " +
                "user_created_rooms(us_id, login, password, created_chat_id, created_chat_name, created_chat_owner) AS ( " +
                    "select * from paginated_users " +
                    "full join chat.chatroom on paginated_users.id=chat.chatroom.owner), " +
                "user_joined_rooms as ( " +
                    "select u.id as us_id, u.login, u.password, msg.room as joined_chat, chr.name as joined_chat_name, chr.owner as joined_chat_owner " +
                    "from chat.user u " +
                    "join chat.message msg on u.id=msg.author " +
                    "join chat.chatroom chr on chr.id=msg.room), " +
                "findAll as ( " +
                    "select ucr.us_id, ucr.login, ucr.password, ucr.created_chat_id, ucr.created_chat_name, ujr.joined_chat, ujr.joined_chat_name, ujr.joined_chat_owner " +
                    "from user_joined_rooms ujr " +
                    "join user_created_rooms ucr on ujr.us_id=ucr.us_id), " +
                "removeDupes as (" +
                    "select *, ROW_NUMBER() OVER(PARTITION BY us_id, created_chat_id, joined_chat " +
                    "ORDER BY us_id) AS DuplicateCount " +
                    "from findAll) " +
                "select * from removeDupes where DuplicateCount < 2 order by us_id, created_chat_id, joined_chat";
        try {
            PreparedStatement statement = con.prepareStatement(sq);
            statement.setInt(1, size * page - size);
            statement.setInt(2, size);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()){
                throw new SQLException("Error. Cannot proceed with SQL request.");
            }
            while (rs.next()) {
                User tmp = new User(rs.getLong("us_id"), rs.getString("login"), rs.getString("password"));
                if (!users.contains(tmp)) {
                    users.add(tmp);
                }
                // deal with created chats
                if (rs.getLong("created_chat_id") != 0) {
                    int chat_id = rs.getInt("created_chat_id");
                    Chatroom created = new Chatroom((long)chat_id, rs.getString("created_chat_name"), tmp);
                    if (chats.get(chat_id) == null) {
                        chats.add(chat_id, created);
                        tmp.addCreatedRooms(created);
                    } else if (chats.get(chat_id).getOwner() == null) {
                        chats.get(chat_id).setOwner(tmp);
                        tmp.addCreatedRooms(created);
                    }
                }

                // deal with joined chats
                if (rs.getLong("joined_chat") != 0) {
                    int joined_id = rs.getInt("joined_chat");
                    Chatroom joined = new Chatroom((long) joined_id, rs.getString("joined_chat_name"), null);
                    if (chats.get(joined_id) == null) {
                        chats.add(joined_id, joined);
                        tmp.addJoinedRooms(joined);
                    }
                }

            }

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return users;
    }
}
