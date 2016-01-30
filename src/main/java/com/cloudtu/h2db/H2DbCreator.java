package com.cloudtu.h2db;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

public class H2DbCreator {
    private H2DbCreator() {
    }

    public static void createDemoDb(){
        DBI dbi = new DBI(H2DbConst.JDBC_URL, H2DbConst.DB_USER_NAME, H2DbConst.DB_USER_PASSWORD);

        try (Handle handle = dbi.open()){
            handle.execute(H2DbConst.USER_TABLE_SCHEMA_DDL);
            handle.execute(H2DbConst.ADDRESS_TABLE_SCHEMA_DDL);
        }
    }
}
