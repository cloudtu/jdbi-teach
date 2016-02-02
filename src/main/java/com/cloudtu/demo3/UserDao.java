package com.cloudtu.demo3;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface UserDao {

    // 在 annotation 寫 SQL
    @SqlUpdate("insert into user (userId, name) values (:userId, :name)")
    // @Bind - 綁定特定欄位
    int add(@Bind("userId") long userId, @Bind("name") String name);

    @SqlUpdate("update user set name = :name where userId = :userId")
    int updateName(@Bind("userId") long userId, @Bind("name") String name);

    @SqlQuery("select name from user where userId = :userId")
    String findNameById(@Bind("userId") long userId);
}
