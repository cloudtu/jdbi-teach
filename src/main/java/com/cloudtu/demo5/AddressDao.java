package com.cloudtu.demo5;

import com.cloudtu.bean.Address;
import com.cloudtu.mapper.AddressMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;

import java.util.List;

@RegisterMapper(AddressMapper.class)
@UseStringTemplate3StatementLocator
public interface AddressDao {

    @SqlUpdate("insert into address (userId, address) values (:userId, :address)")
    int add(@Bind("userId") long userId, @Bind("address") String address);

    @SqlQuery("select * from address where userId = :userId")
    List<Address> find(@Bind("userId") long userId);
}
