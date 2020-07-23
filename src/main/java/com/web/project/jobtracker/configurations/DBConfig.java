package com.web.project.jobtracker.configurations;

import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
    private static DBConfig uniqueInstance = null;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/jobtracker?createDatabaseIfNotExist=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Anudish@1994";

    public DBConfig() {
    }

    public static DBConfig instance()
    {
        if (null == uniqueInstance)
        {
            uniqueInstance = new DBConfig();
        }
        return uniqueInstance;
    }

    public Connection getDBConnection() throws SQLException
    {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
