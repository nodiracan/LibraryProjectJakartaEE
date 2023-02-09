package com.library.dao;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DAO {

    ResourceBundle settings = ResourceBundle.getBundle("settings");
    private final ThreadLocal<Connection> connectionThreadLocal = ThreadLocal.withInitial(
            () -> {
                try {
                    DriverManager.registerDriver(new Driver());
                    return DriverManager.getConnection(
                            settings.getString("datasource.url"),
                            settings.getString("datasource.username"),
                            settings.getString("datasource.password"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
    );

    protected Connection getConnection() {
        return connectionThreadLocal.get();
    }
}
