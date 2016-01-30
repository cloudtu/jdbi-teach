package com.cloudtu.mapper;

import com.cloudtu.bean.Address;
import com.cloudtu.bean.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressMapper implements ResultSetMapper<Address>{
    @Override
    public Address map(int index,
                    ResultSet r,
                    StatementContext ctx) throws SQLException {
        Address address = new Address();
        address.setUserId(r.getLong("userId"));
        address.setAddress(r.getString("address"));
        return address;
    }
}
