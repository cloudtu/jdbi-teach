package com.cloudtu.mapper;

import com.cloudtu.bean.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ResultSetMapper<User>{
    @Override
    public User map(int index,
                    ResultSet r,
                    StatementContext ctx) throws SQLException {
        User user = new User();
        user.setUserId(r.getLong("userId"));
        user.setName(r.getString("name"));
        return user;
    }
}
