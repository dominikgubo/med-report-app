package org.acme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider {
    private final String url;
    private final String password;
    private final String username;

    public DBConnectionProvider(String url, String password, String username) {
        this.url = url;
        this.password = password;
        this.username = username;
    }

    Connection getConnection(){
        try{
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
