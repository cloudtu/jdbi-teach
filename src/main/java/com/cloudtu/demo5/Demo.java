package com.cloudtu.demo5;

import com.cloudtu.bean.UserWithAddress;
import com.cloudtu.h2db.H2DbConst;
import com.cloudtu.h2db.H2DbCreator;
import org.h2.jdbcx.JdbcConnectionPool;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class Demo {
    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    /**
     * demo Dao with transaction, entity with parent-child relation
     *
     * @param args
     */
    public static void main(String[] args) {
        H2DbCreator.createDemoDb();

        DataSource dataSource = JdbcConnectionPool.create(H2DbConst.JDBC_URL,
                                                          H2DbConst.DB_USER_NAME, H2DbConst.DB_USER_PASSWORD);
        DBI dbi = new DBI(dataSource);

        UserDao userDao = dbi.onDemand(UserDao.class);

        userDao.add(1, "duke", "taiwan", "america");

        UserWithAddress userWithAddress =  userDao.find(1);
        logger.info("user : {}", userWithAddress.getUser());
        userWithAddress.getAddresses().forEach(address -> logger.info("address : {}", address));
    }
}
