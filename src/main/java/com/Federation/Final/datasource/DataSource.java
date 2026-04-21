package com.Federation.Final.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private String url = "jdbc:postgresql://localhost:5432/federation";
    private String user = "federation_user";
    private String password = "123456";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }
}
