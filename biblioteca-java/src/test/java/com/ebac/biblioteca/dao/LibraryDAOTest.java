package com.ebac.biblioteca.dao;

import com.ebac.biblioteca.model.Library;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryDAOTest {

    private final LibraryDAO libraryDAO = new LibraryDAO();

    @Test
    void shouldPerformCompleteCrud() throws Exception {

        // CREATE
        Library library = new Library(
                "Biblioteca de Prueba",
                "Pachuca, Hidalgo"
        );

        Library created = libraryDAO.create(library);

        assertNotNull(created.getId());

        // READ
        Library found = libraryDAO.findById(created.getId());

        assertNotNull(found);
        assertEquals("Biblioteca de Prueba", found.getName());
        assertEquals("Pachuca, Hidalgo", found.getAddress());

        // UPDATE
        found.setName("Biblioteca Actualizada");
        found.setAddress("Pachuca de Soto, Hidalgo");

        assertTrue(libraryDAO.update(found));

        Library updated = libraryDAO.findById(found.getId());

        assertNotNull(updated);
        assertEquals("Biblioteca Actualizada", updated.getName());
        assertEquals("Pachuca de Soto, Hidalgo", updated.getAddress());

        // READ ALL
        List<Library> libraries = libraryDAO.findAll();

        assertTrue(
                libraries.stream()
                        .anyMatch(l -> l.getId().equals(created.getId()))
        );

        // DELETE
        assertTrue(libraryDAO.delete(created.getId()));
        assertNull(libraryDAO.findById(created.getId()));
    }
}
