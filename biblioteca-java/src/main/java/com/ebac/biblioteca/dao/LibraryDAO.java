package com.ebac.biblioteca.dao;

import com.ebac.biblioteca.database.DatabaseConnection;
import com.ebac.biblioteca.model.Library;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryDAO {

    private final DatabaseConnection database;

    public LibraryDAO() {
        this.database = DatabaseConnection.getInstance();
    }

    // CREATE
    public Library create(Library library) throws SQLException {
        String sql = "INSERT INTO libraries (name, address) VALUES (?, ?)";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, library.getName());
            statement.setString(2, library.getAddress());

            statement.executeUpdate();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    library.setId(keys.getLong(1));
                }
            }

            return library;
        }
    }

    // READ
    public Library findById(Long id) throws SQLException {
        String sql = "SELECT * FROM libraries WHERE id = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return mapLibrary(result);
                }
            }
        }

        return null;
    }

    // READ ALL
    public List<Library> findAll() throws SQLException {
        String sql = "SELECT * FROM libraries ORDER BY id";
        List<Library> libraries = new ArrayList<>();

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                libraries.add(mapLibrary(result));
            }
        }

        return libraries;
    }

    // UPDATE
    public boolean update(Library library) throws SQLException {
        String sql = "UPDATE libraries SET name = ?, address = ? WHERE id = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, library.getName());
            statement.setString(2, library.getAddress());
            statement.setLong(3, library.getId());

            return statement.executeUpdate() > 0;
        }
    }

    // DELETE
    public boolean delete(Long id) throws SQLException {
        String sql = "DELETE FROM libraries WHERE id = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            return statement.executeUpdate() > 0;
        }
    }

    private Library mapLibrary(ResultSet result) throws SQLException {
        return new Library(
                result.getLong("id"),
                result.getString("name"),
                result.getString("address")
        );
    }
}
