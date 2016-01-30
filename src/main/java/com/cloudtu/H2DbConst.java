package com.cloudtu;

public class H2DbConst {
    public static final String JDBC_URL = "jdbc:h2:mem:demodb;DB_CLOSE_DELAY=-1";

    public static final String DB_USER_NAME = "myUserName";

    public static final String DB_USER_PASSWORD = "myUserPassword";

    public static final String USER_TABLE_SCHEMA_DDL = "create table user(userId int primary key,name varchar(50))";

    public static final String ADDRESS_TABLE_SCHEMA_DDL = "create table address(userId int primary key,address varchar(500))";

    private H2DbConst() {
    }
}
