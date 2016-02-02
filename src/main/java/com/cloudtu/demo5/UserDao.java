package com.cloudtu.demo5;

import com.cloudtu.bean.Address;
import com.cloudtu.bean.User;
import com.cloudtu.bean.UserWithAddress;
import com.cloudtu.mapper.UserMapper;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;

import java.util.Arrays;
import java.util.List;

@RegisterMapper(UserMapper.class)
@UseStringTemplate3StatementLocator
public abstract class UserDao {
    // UserDao, AddressDao instances are under the same DB connection
    @CreateSqlObject
    abstract AddressDao addressDao();

    @SqlUpdate("insert into user (userId, name) values (:userId, :name)")
    abstract int add(@Bind("userId") long userId, @Bind("name") String name);

    // UserDao, AddressDao instances are under the same DB transaction
    @Transaction
    public void add(final long userId, final String name, String... addresses){
        add(userId, name);
        Arrays.stream(addresses)
              .forEach(address -> addressDao().add(userId, address));
    }

    @SqlQuery("select * from user where userId = :userId")
    abstract User findById(@Bind("userId") long userId);

    public UserWithAddress find(long userId){
        User user = findById(userId);
        List<Address> addresses = addressDao().find(userId);
        return new UserWithAddress(user, addresses);
    }
}
