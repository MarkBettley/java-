package com.ebac.biblioteca.dao;

import com.ebac.biblioteca.database.DatabaseConnection;
import com.ebac.biblioteca.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private final DatabaseConnection database;

    public BookDAO() {
        this.database = DatabaseConnection.getInstance();
    }

    // CREATE
    public Book create(Book book) throws SQLException {
        String sql =
                "INSERT INTO books " +
                "(title, author_id, library_id, publication_year, isbn, available) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, book.getTitle());
            statement.setLong(2, book.getAuthorId());
            statement.setLong(3, book.getLibraryId());
            statement.setInt(4, book.getPublicationYear());
            statement.setString(5, book.getIsbn());
            statement.setBoolean(6, book.isAvailable());

            statement.executeUpdate();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    book.setId(keys.getLong(1));
                }
            }

            return book;
        }
    }

    // READ
    public Book findById(Long id) throws SQLException {
        String sql =
                "SELECT b.*, " +
                "CONCAT(a.first_name, ' ', a.last_name) AS author_name " +
                "FROM books b " +
                "JOIN authors a ON b.author_id = a.id " +
                "WHERE b.id = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return mapBook(result);
                }
            }
        }

        return null;
    }

    public Book findByIsbn(String isbn) throws SQLException {
        String sql =
                "SELECT b.*, " +
                "CONCAT(a.first_name, ' ', a.last_name) AS author_name " +
                "FROM books b " +
                "JOIN authors a ON b.author_id = a.id " +
                "WHERE b.isbn = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, isbn);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return mapBook(result);
                }
            }
        }

        return null;
    }

    // READ ALL
    public List<Book> findAll() throws SQLException {
        String sql =
                "SELECT b.*, " +
                "CONCAT(a.first_name, ' ', a.last_name) AS author_name " +
                "FROM books b " +
                "JOIN authors a ON b.author_id = a.id " +
                "ORDER BY b.id";

        List<Book> books = new ArrayList<>();

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                books.add(mapBook(result));
            }
        }

        return books;
    }

    // UPDATE
    public boolean update(Book book) throws SQLException {
        String sql =
                "UPDATE books SET title = ?, author_id = ?, library_id = ?, " +
                "publication_year = ?, isbn = ?, available = ? WHERE id = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, book.getTitle());
            statement.setLong(2, book.getAuthorId());
            statement.setLong(3, book.getLibraryId());
            statement.setInt(4, book.getPublicationYear());
            statement.setString(5, book.getIsbn());
            statement.setBoolean(6, book.isAvailable());
            statement.setLong(7, book.getId());

            return statement.executeUpdate() > 0;
        }
    }

    // DELETE
    public boolean delete(Long id) throws SQLException {
        String sql = "DELETE FROM books WHERE id = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            return statement.executeUpdate() > 0;
        }
    }

    private Book mapBook(ResultSet result) throws SQLException {
        return new Book(
                result.getLong("id"),
                result.getString("title"),
                result.getString("author_name"),
                result.getLong("author_id"),
                result.getLong("library_id"),
                result.getInt("publication_year"),
                result.getString("isbn"),
                result.getBoolean("available")
        );
    }
}
