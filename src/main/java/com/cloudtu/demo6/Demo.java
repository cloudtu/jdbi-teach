package com.cloudtu.demo6;

import com.cloudtu.bean.User;
import com.cloudtu.h2db.H2DbConst;
import com.cloudtu.h2db.H2DbCreator;
import org.h2.jdbcx.JdbcConnectionPool;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.logging.SLF4JLog;
import org.skife.jdbi.v2.util.StringColumnMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.Map;

public class Demo {
    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    /**
     * demo show sql log
     *
     * @param args
     */
    public static void main(String[] args) {
        H2DbCreator.createDemoDb();

        DataSource dataSource = JdbcConnectionPool.create(H2DbConst.JDBC_URL,
                                                          H2DbConst.DB_USER_NAME, H2DbConst.DB_USER_PASSWORD);
        DBI dbi = new DBI(dataSource);

        // DBI use slf4j log
        dbi.setSQLLog(new SLF4JLog());

        try (Handle handle = dbi.open()){
            handle.createStatement("insert into user (userId, name) values (:userId, :name)")
                        .bind("userId", 1)
                        .bind("name", "duke")
                        .execute();
        }
    }
}
