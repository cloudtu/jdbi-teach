package com.cloudtu.demo3;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface UserDao {

    @SqlUpdate("insert into user (userId, name) values (:userId, :name)")
    int add(@Bind("userId") long userId, @Bind("name") String name);

    @SqlUpdate("update user set name = :name where userId = :userId")
    int updateName(@Bind("userId") long userId, @Bind("name") String name);

    @SqlQuery("select name from user where userId = :userId")
    String findNameById(@Bind("userId") int userId);
}
