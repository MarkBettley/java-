package com.ebac.biblioteca.model;

public class Book {

    private Long id;
    private String title;
    private String author;
    private Long authorId;
    private Long libraryId;
    private int publicationYear;
    private String isbn;
    private boolean available;

    // Constructor original del Proyecto 1
    public Book(String title, String author, int publicationYear, String isbn) {
        this(null, title, author, null, null,
                publicationYear, isbn, true);
    }

    // Constructor para persistencia en base de datos
    public Book(
            Long id,
            String title,
            String author,
            Long authorId,
            Long libraryId,
            int publicationYear,
            String isbn,
            boolean available) {

        this.id = id;
        this.title = title;
        this.author = author;
        this.authorId = authorId;
        this.libraryId = libraryId;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return title + " - " + author + " (" + publicationYear + ")";
    }
}
