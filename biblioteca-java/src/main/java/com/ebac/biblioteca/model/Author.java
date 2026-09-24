package com.ebac.biblioteca.model;

import java.util.ArrayList;
import java.util.List;

public class Author {

    private String firstName;
    private String lastName;
    private String biography;
    private List<Book> publishedBooks;

    public Author(String firstName, String lastName, String biography) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
        this.publishedBooks = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<Book> getPublishedBooks() {
        return publishedBooks;
    }

    public void setPublishedBooks(List<Book> publishedBooks) {
        this.publishedBooks = publishedBooks;
    }

    public void addBook(Book book) {
        publishedBooks.add(book);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
