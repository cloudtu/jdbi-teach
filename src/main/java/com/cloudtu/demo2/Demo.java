package com.cloudtu.demo2;

import com.cloudtu.h2db.H2DbConst;
import com.cloudtu.h2db.H2DbCreator;
import com.cloudtu.bean.User;
import org.h2.jdbcx.JdbcConnectionPool;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.util.StringColumnMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.Map;

public class Demo {
    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    /**
     * demo connection pool, Handle fluent api
     *
     * @param args
     */
    public static void main(String[] args) {
        H2DbCreator.createDemoDb();

        DataSource dataSource = JdbcConnectionPool.create(H2DbConst.JDBC_URL,
                                                          H2DbConst.DB_USER_NAME, H2DbConst.DB_USER_PASSWORD);
        // 可經由 connection pool 取得連線
        DBI dbi = new DBI(dataSource);

        // 用 try with resource 語法簡化程式碼
        try (Handle handle = dbi.open()){
            // fluent api - insert data
            handle.createStatement("insert into user (userId, name) values (:userId, :name)")
                        .bind("userId", 1)
                        .bind("name", "duke")
                        .execute();
            handle.createStatement("insert into user (userId, name) values (:userId, :name)")
                        .bind("userId", 2)
                        .bind("name", "cloudtu")
                        .execute();

            // fluent api - query all - mapping to List<Map>
            for(Map<String, Object> user : handle.createQuery("select * from user").list()){
                logger.info(user.toString());
            }

            // fluent api - query all - mapping to  List<User>
            for (User user : handle.createQuery("select * from user")
                                   .map(User.class)
                                   .list()) {
                logger.info("user : {}", user);
            }

            // fluent api - query first record - mapping to Map<String, Object>
            logger.info("user : {}" , handle.createQuery("select * from user where userId = :userId")
                                            .bind("userId", 1)
                                            .first());

            // fluent api - query first record - mapping to String
            logger.info("name : {}" , handle.createQuery("select name from user where userId = :userId")
                                            .bind("userId", 1)
                                            .map(StringColumnMapper.INSTANCE)
                                            .first());
        }
    }
}
