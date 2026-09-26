package com.ebac.biblioteca.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/biblioteca_ebac?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private static final String USER =
            System.getenv().getOrDefault("DB_USER", "ebac");

    private static final String PASSWORD =
            System.getenv().getOrDefault("DB_PASSWORD", "");

    private static DatabaseConnection instance;

    private DatabaseConnection() {
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
