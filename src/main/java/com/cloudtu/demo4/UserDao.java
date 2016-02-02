package com.cloudtu.demo4;

import com.cloudtu.bean.User;
import com.cloudtu.mapper.UserMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;
import org.skife.jdbi.v2.unstable.BindIn;

import java.util.List;

// @BindBean 要搭配 @RegisterMapper 一起使用
@RegisterMapper(UserMapper.class)
// @BindIn 要搭配 @UseStringTemplate3StatementLocator 一起使用
@UseStringTemplate3StatementLocator
public interface UserDao {

    @SqlUpdate("insert into user (userId, name) values (:userId, :name)")
    int add(@Bind("userId") long userId, @Bind("name") String name);

    @SqlUpdate("update user set name = :name where userId = :userId")
    // @BindBean - 綁定整個 bean
    int update(@BindBean User user);

    @SqlQuery("select * from user where userId = :userId")
    User find(@Bind("userId") long userId);

    @SqlQuery("select * from user where userId in (<userIdList>)")
    //@BindIn - in condition query
    List<User> find(@BindIn("userIdList") List<Long> userIdList);
}
