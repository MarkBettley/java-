package com.ebac.biblioteca.dao;

import com.ebac.biblioteca.model.Author;
import com.ebac.biblioteca.model.Book;
import com.ebac.biblioteca.model.Library;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookDAOTest {

    private final AuthorDAO authorDAO = new AuthorDAO();
    private final LibraryDAO libraryDAO = new LibraryDAO();
    private final BookDAO bookDAO = new BookDAO();

    @Test
    void shouldPerformCompleteCrud() throws Exception {

        Author author = authorDAO.create(
                new Author(
                        "Gabriel",
                        "Prueba",
                        "Autor utilizado para la prueba de BookDAO"
                )
        );

        Library library = libraryDAO.create(
                new Library(
                        "Biblioteca BookDAO",
                        "Pachuca, Hidalgo"
                )
        );

        try {
            // CREATE
            Book book = new Book(
                    null,
                    "Libro de Prueba",
                    author.getFirstName() + " " + author.getLastName(),
                    author.getId(),
                    library.getId(),
                    2026,
                    "ISBN-BOOK-DAO-001",
                    true
            );

            Book created = bookDAO.create(book);

            assertNotNull(created.getId());

            // READ
            Book found = bookDAO.findById(created.getId());

            assertNotNull(found);
            assertEquals("Libro de Prueba", found.getTitle());
            assertEquals("Gabriel Prueba", found.getAuthor());
            assertEquals(author.getId(), found.getAuthorId());
            assertEquals(library.getId(), found.getLibraryId());
            assertTrue(found.isAvailable());

            // FIND BY ISBN
            Book byIsbn = bookDAO.findByIsbn("ISBN-BOOK-DAO-001");

            assertNotNull(byIsbn);
            assertEquals(created.getId(), byIsbn.getId());

            // UPDATE
            found.setTitle("Libro Actualizado");
            found.setPublicationYear(2025);
            found.setAvailable(false);

            assertTrue(bookDAO.update(found));

            Book updated = bookDAO.findById(found.getId());

            assertNotNull(updated);
            assertEquals("Libro Actualizado", updated.getTitle());
            assertEquals(2025, updated.getPublicationYear());
            assertFalse(updated.isAvailable());

            // READ ALL
            List<Book> books = bookDAO.findAll();

            assertTrue(
                    books.stream()
                            .anyMatch(b -> b.getId().equals(created.getId()))
            );

            // DELETE
            assertTrue(bookDAO.delete(created.getId()));
            assertNull(bookDAO.findById(created.getId()));

        } finally {
            // Limpieza de las entidades relacionadas
            libraryDAO.delete(library.getId());
            authorDAO.delete(author.getId());
        }
    }
}
