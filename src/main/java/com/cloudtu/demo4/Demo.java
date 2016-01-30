package com.cloudtu.demo4;

import com.cloudtu.h2db.H2DbConst;
import com.cloudtu.h2db.H2DbCreator;
import com.cloudtu.bean.User;
import org.h2.jdbcx.JdbcConnectionPool;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.Arrays;

public class Demo {
    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    /**
     * demo Dao advance
     *
     * @param args
     */
    public static void main(String[] args) {
        H2DbCreator.createDemoDb();

        DataSource dataSource = JdbcConnectionPool.create(H2DbConst.JDBC_URL,
                                                          H2DbConst.DB_USER_NAME, H2DbConst.DB_USER_PASSWORD);
        // use connection pool
        DBI dbi = new DBI(dataSource);

        // DBI.onDemand(...) 會自動控制 connection 的 open & close
        UserDao userDao = dbi.onDemand(UserDao.class);

        logger.info("addAmount : {}", userDao.add(1, "duke"));

        User user =  userDao.find(1);
        logger.info("user : {}", user);

        user.setName("cloudtu");
        logger.info("updAmount : {}", userDao.update(user));

        for(User userBean : userDao.find(Arrays.asList(1L))){
            logger.info("user : {}", userBean);
        }
    }
}
