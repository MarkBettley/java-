package com.ebac.biblioteca.dao;

import com.ebac.biblioteca.database.DatabaseConnection;
import com.ebac.biblioteca.model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {

    private final DatabaseConnection database;

    public AuthorDAO() {
        this.database = DatabaseConnection.getInstance();
    }

    // CREATE
    public Author create(Author author) throws SQLException {
        String sql = "INSERT INTO authors " +
                "(first_name, last_name, biography) VALUES (?, ?, ?)";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, author.getFirstName());
            statement.setString(2, author.getLastName());
            statement.setString(3, author.getBiography());

            statement.executeUpdate();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    author.setId(keys.getLong(1));
                }
            }

            return author;
        }
    }

    // READ
    public Author findById(Long id) throws SQLException {
        String sql = "SELECT * FROM authors WHERE id = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return mapAuthor(result);
                }
            }
        }

        return null;
    }

    // READ ALL
    public List<Author> findAll() throws SQLException {
        String sql = "SELECT * FROM authors ORDER BY id";
        List<Author> authors = new ArrayList<>();

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                authors.add(mapAuthor(result));
            }
        }

        return authors;
    }

    // UPDATE
    public boolean update(Author author) throws SQLException {
        String sql = "UPDATE authors SET first_name = ?, " +
                "last_name = ?, biography = ? WHERE id = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, author.getFirstName());
            statement.setString(2, author.getLastName());
            statement.setString(3, author.getBiography());
            statement.setLong(4, author.getId());

            return statement.executeUpdate() > 0;
        }
    }

    // DELETE
    public boolean delete(Long id) throws SQLException {
        String sql = "DELETE FROM authors WHERE id = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            return statement.executeUpdate() > 0;
        }
    }

    private Author mapAuthor(ResultSet result) throws SQLException {
        return new Author(
                result.getLong("id"),
                result.getString("first_name"),
                result.getString("last_name"),
                result.getString("biography")
        );
    }
}
