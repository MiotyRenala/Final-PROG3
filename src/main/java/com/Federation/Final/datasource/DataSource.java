package com.Federation.Final.datasource;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DataSource {
    public Connection getConnection(){
        try{
            Dotenv dotenv = Dotenv.load();
            String jdbcURL = dotenv.get("DB_URL");
            String user = dotenv.get("DB_USER");
            String password = dotenv.get("DB_PASSWORD");
            return DriverManager.getConnection(jdbcURL,user,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
