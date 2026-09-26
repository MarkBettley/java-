package com.ebac.biblioteca.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {

    @Test
    void shouldReturnSameSingletonInstance() {
        DatabaseConnection first = DatabaseConnection.getInstance();
        DatabaseConnection second = DatabaseConnection.getInstance();

        assertSame(first, second);
    }

    @Test
    void shouldConnectToDatabase() throws Exception {
        try (Connection connection =
                     DatabaseConnection.getInstance().getConnection()) {

            assertNotNull(connection);
            assertFalse(connection.isClosed());
        }
    }
}
