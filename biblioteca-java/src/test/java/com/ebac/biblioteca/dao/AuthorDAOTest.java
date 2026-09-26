package com.ebac.biblioteca.dao;

import com.ebac.biblioteca.model.Author;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorDAOTest {

    private final AuthorDAO authorDAO = new AuthorDAO();

    @Test
    void shouldPerformCompleteCrud() throws Exception {

        // CREATE
        Author author = new Author(
                "Autor",
                "Prueba",
                "Biografía inicial"
        );

        Author created = authorDAO.create(author);

        assertNotNull(created.getId());

        // READ
        Author found = authorDAO.findById(created.getId());

        assertNotNull(found);
        assertEquals("Autor", found.getFirstName());
        assertEquals("Prueba", found.getLastName());

        // UPDATE
        found.setBiography("Biografía actualizada");

        boolean updated = authorDAO.update(found);

        assertTrue(updated);

        Author updatedAuthor = authorDAO.findById(found.getId());

        assertNotNull(updatedAuthor);
        assertEquals(
                "Biografía actualizada",
                updatedAuthor.getBiography()
        );

        // READ ALL
        List<Author> authors = authorDAO.findAll();

        assertFalse(authors.isEmpty());
        assertTrue(
                authors.stream()
                        .anyMatch(a -> a.getId().equals(created.getId()))
        );

        // DELETE
        boolean deleted = authorDAO.delete(created.getId());

        assertTrue(deleted);
        assertNull(authorDAO.findById(created.getId()));
    }
}
