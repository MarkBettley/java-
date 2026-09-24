package com.ebac.biblioteca.service;

import com.ebac.biblioteca.exception.BookNotAvailableException;
import com.ebac.biblioteca.exception.DuplicateElementException;
import com.ebac.biblioteca.exception.UserNotFoundException;
import com.ebac.biblioteca.model.Author;
import com.ebac.biblioteca.model.Book;
import com.ebac.biblioteca.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class Library {

    private final List<Book> books;
    private final Map<String, Author> authors;
    private final Map<String, User> users;
    private final Queue<String> loanRequests;

    public Library() {
        this.books = new ArrayList<>();
        this.authors = new HashMap<>();
        this.users = new HashMap<>();
        this.loanRequests = new LinkedList<>();
    }

    // ---------- LIBROS ----------

    public void addBook(Book book) throws DuplicateElementException {
        boolean exists = books.stream()
                .anyMatch(b -> b.getIsbn().equals(book.getIsbn()));

        if (exists) {
            throw new DuplicateElementException(
                    "Ya existe un libro con ISBN: " + book.getIsbn()
            );
        }

        books.add(book);
    }

    public Book getBookByIsbn(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    public void updateBook(String isbn, Book updatedBook) {
        Book book = getBookByIsbn(isbn);

        if (book != null) {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setPublicationYear(updatedBook.getPublicationYear());
        }
    }

    public boolean removeBook(String isbn) {
        return books.removeIf(book -> book.getIsbn().equals(isbn));
    }

    // ---------- AUTORES ----------

    public void addAuthor(Author author) throws DuplicateElementException {
        String key = author.getFirstName() + " " + author.getLastName();

        if (authors.containsKey(key)) {
            throw new DuplicateElementException(
                    "El autor ya está registrado: " + key
            );
        }

        authors.put(key, author);
    }

    public Author getAuthor(String fullName) {
        return authors.get(fullName);
    }

    public void updateAuthor(String fullName, Author updatedAuthor) {
        if (authors.containsKey(fullName)) {
            authors.put(fullName, updatedAuthor);
        }
    }

    public Author removeAuthor(String fullName) {
        return authors.remove(fullName);
    }

    // ---------- USUARIOS ----------

    public void addUser(User user) throws DuplicateElementException {
        if (users.containsKey(user.getEmail())) {
            throw new DuplicateElementException(
                    "El usuario ya está registrado: " + user.getEmail()
            );
        }

        users.put(user.getEmail(), user);
    }

    public User getUser(String email) {
        return users.get(email);
    }

    public void updateUser(String email, User updatedUser) {
        if (users.containsKey(email)) {
            users.put(email, updatedUser);
        }
    }

    public User removeUser(String email) {
        return users.remove(email);
    }

    // ---------- PRÉSTAMOS ----------

    public void requestLoan(String email, String isbn)
            throws UserNotFoundException, BookNotAvailableException {

        User user = users.get(email);

        if (user == null) {
            throw new UserNotFoundException(
                    "Usuario no registrado: " + email
            );
        }

        Book book = getBookByIsbn(isbn);

        if (book == null || !book.isAvailable()) {
            throw new BookNotAvailableException(
                    "El libro no está disponible: " + isbn
            );
        }

        loanRequests.offer(email + " -> " + isbn);
        user.borrowBook(book);
        book.setAvailable(false);
    }

    public String processNextLoanRequest() {
        return loanRequests.poll();
    }

    // ---------- STREAMS Y LAMBDAS ----------

    public List<Book> searchBooksByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor()
                        .equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksPublishedAfter(int year) {
        return books.stream()
                .filter(book -> book.getPublicationYear() > year)
                .collect(Collectors.toList());
    }

    public List<String> getBookTitles() {
        return books.stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    public List<User> getUsersWithBorrowedBooks() {
        return users.values().stream()
                .filter(user -> !user.getBorrowedBooks().isEmpty())
                .collect(Collectors.toList());
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public Map<String, Author> getAuthors() {
        return new HashMap<>(authors);
    }

    public Map<String, User> getUsers() {
        return new HashMap<>(users);
    }
}
