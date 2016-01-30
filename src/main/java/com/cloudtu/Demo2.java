package com.cloudtu;

import org.h2.jdbcx.JdbcConnectionPool;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.Map;

public class Demo2 {
    private static final Logger logger = LoggerFactory.getLogger(Demo2.class);

    /**
     * demo connection pool, DBI, Handle
     *
     * @param args
     */
    public static void main(String[] args) {
        H2DbCreator.createDemoDb();

        // use connection pool
        DataSource dataSource = JdbcConnectionPool.create(H2DbConst.JDBC_URL,
                                                          H2DbConst.DB_USER_NAME, H2DbConst.DB_USER_PASSWORD);
        DBI dbi = new DBI(dataSource);

        // Handle 類似 JDBC 的 Connection，用完後要呼叫 Handle.close()
        try (Handle handle = dbi.open()){
            // insert data
            handle.execute("insert into user (userId, name) values (?, ?)", 1, "duke");

            // query data
            Map<String, Object> user = handle.createQuery("select * from user where userId = :userId")
                                             .bind("userId", 1)
                                             .first();

            logger.info(user.toString());
        }
    }
}
