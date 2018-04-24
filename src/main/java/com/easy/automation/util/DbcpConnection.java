package com.easy.automation.util;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DbcpConnection {
    private static DataSource dataSource;
    private static DataSource dataSource2;

    private static Connection conn;

    static{
        Properties properties = new Properties();
        Properties properties2 = new Properties();

        try{
            properties.load(DbcpConnection.class.getClassLoader().getResourceAsStream("dbcp.properties"));
            properties2.load(DbcpConnection.class.getClassLoader().getResourceAsStream("dbcp2.properties"));

            dataSource= BasicDataSourceFactory.createDataSource(properties);
            dataSource2= BasicDataSourceFactory.createDataSource(properties2);

        }catch (IOException e)
        {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public DbcpConnection(){}

    public static Connection getConnection() {
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static DataSource getDataSource2() {
        return dataSource2;
    }
}
