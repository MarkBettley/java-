package com.ebac.biblioteca;

import com.ebac.biblioteca.dao.AuthorDAO;
import com.ebac.biblioteca.dao.BookDAO;
import com.ebac.biblioteca.dao.LibraryDAO;
import com.ebac.biblioteca.dao.UserDAO;
import com.ebac.biblioteca.model.Author;
import com.ebac.biblioteca.model.Book;
import com.ebac.biblioteca.model.Library;
import com.ebac.biblioteca.model.User;

public class Main {

    public static void main(String[] args) {

        AuthorDAO authorDAO = new AuthorDAO();
        LibraryDAO libraryDAO = new LibraryDAO();
        UserDAO userDAO = new UserDAO();
        BookDAO bookDAO = new BookDAO();

        Author author = null;
        Library library = null;
        User user = null;
        Book book = null;

        try {
            System.out.println("=== BIBLIOTECA EBAC - MYSQL/JDBC ===");

            author = authorDAO.create(
                    new Author(
                            "Gabriel",
                            "García Márquez",
                            "Escritor colombiano y Premio Nobel de Literatura."
                    )
            );

            library = libraryDAO.create(
                    new Library(
                            "Biblioteca Central EBAC",
                            "Pachuca de Soto, Hidalgo"
                    )
            );

            user = userDAO.create(
                    new User(
                            "Marco Hernández",
                            "marco@biblioteca.com",
                            "1234"
                    )
            );

            book = new Book(
                    null,
                    "Cien años de soledad",
                    author.getFirstName() + " " + author.getLastName(),
                    author.getId(),
                    library.getId(),
                    1967,
                    "978-0307474728",
                    true
            );

            bookDAO.create(book);

            System.out.println("\n--- CREATE ---");
            System.out.println("Autor: " + author);
            System.out.println("Biblioteca: " + library);
            System.out.println("Usuario: " + user);
            System.out.println("Libro: " + book);

            System.out.println("\n--- READ ---");
            bookDAO.findAll().forEach(System.out::println);

            book.setAvailable(false);
            bookDAO.update(book);

            Book updatedBook = bookDAO.findById(book.getId());

            System.out.println("\n--- UPDATE ---");
            System.out.println(
                    updatedBook.getTitle()
                            + " | Disponible: "
                            + updatedBook.isAvailable()
            );

            System.out.println("\n--- DATOS EN MYSQL ---");
            System.out.println("Autores: " + authorDAO.findAll().size());
            System.out.println("Bibliotecas: " + libraryDAO.findAll().size());
            System.out.println("Usuarios: " + userDAO.findAll().size());
            System.out.println("Libros: " + bookDAO.findAll().size());

            System.out.println(
                    "\nDatos almacenados correctamente en biblioteca_ebac."
            );

        } catch (Exception e) {
            System.err.println(
                    "Error en la aplicación: " + e.getMessage()
            );
            e.printStackTrace();

        } finally {
            try {
                if (book != null && book.getId() != null) {
                    bookDAO.delete(book.getId());
                }

                if (user != null && user.getId() != null) {
                    userDAO.delete(user.getId());
                }

                if (library != null && library.getId() != null) {
                    libraryDAO.delete(library.getId());
                }

                if (author != null && author.getId() != null) {
                    authorDAO.delete(author.getId());
                }

                System.out.println(
                        "\nDatos de demostración eliminados correctamente."
                );

            } catch (Exception e) {
                System.err.println(
                        "Error al limpiar los datos: " + e.getMessage()
                );
            }
        }
    }
}
