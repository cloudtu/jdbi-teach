package com.cloudtu;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Demo1 {
    private static final Logger logger = LoggerFactory.getLogger(Demo1.class);

    /**
     * demo DBI, Handle
     *
     * @param args
     */
    public static void main(String[] args) {
        H2DbCreator.createDemoDb();

        DBI dbi = new DBI(H2DbConst.JDBC_URL, H2DbConst.DB_USER_NAME, H2DbConst.DB_USER_PASSWORD);

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
