package com.ebac.biblioteca.dao;

import com.ebac.biblioteca.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    private final UserDAO userDAO = new UserDAO();

    @Test
    void shouldPerformCompleteCrud() throws Exception {

        // CREATE
        User user = new User(
                "Usuario Prueba",
                "usuario.prueba@ebac.com",
                "1234"
        );

        User created = userDAO.create(user);

        assertNotNull(created.getId());

        // READ
        User found = userDAO.findById(created.getId());

        assertNotNull(found);
        assertEquals("Usuario Prueba", found.getName());
        assertEquals("usuario.prueba@ebac.com", found.getEmail());

        // UPDATE
        found.setName("Usuario Actualizado");
        found.setPassword("5678");

        assertTrue(userDAO.update(found));

        User updated = userDAO.findById(found.getId());

        assertNotNull(updated);
        assertEquals("Usuario Actualizado", updated.getName());
        assertEquals("5678", updated.getPassword());

        // FIND BY EMAIL
        User byEmail =
                userDAO.findByEmail("usuario.prueba@ebac.com");

        assertNotNull(byEmail);
        assertEquals(created.getId(), byEmail.getId());

        // READ ALL
        List<User> users = userDAO.findAll();

        assertTrue(
                users.stream()
                        .anyMatch(u -> u.getId().equals(created.getId()))
        );

        // DELETE
        assertTrue(userDAO.delete(created.getId()));
        assertNull(userDAO.findById(created.getId()));
    }
}
