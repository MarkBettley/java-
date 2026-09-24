package com.ebac.biblioteca;

import com.ebac.biblioteca.exception.BookNotAvailableException;
import com.ebac.biblioteca.exception.DuplicateElementException;
import com.ebac.biblioteca.exception.UserNotFoundException;
import com.ebac.biblioteca.model.Author;
import com.ebac.biblioteca.model.Book;
import com.ebac.biblioteca.model.User;
import com.ebac.biblioteca.service.Library;

public class Main {

    public static void main(String[] args) {

        Library library = new Library();

        try {
            // Crear autores
            Author garciaMarquez = new Author(
                    "Gabriel",
                    "García Márquez",
                    "Escritor colombiano y ganador del Premio Nobel de Literatura."
            );

            Author orwell = new Author(
                    "George",
                    "Orwell",
                    "Escritor británico conocido por sus novelas distópicas."
            );

            library.addAuthor(garciaMarquez);
            library.addAuthor(orwell);

            // Crear libros
            Book book1 = new Book(
                    "Cien años de soledad",
                    "Gabriel García Márquez",
                    1967,
                    "978-0307474728"
            );

            Book book2 = new Book(
                    "1984",
                    "George Orwell",
                    1949,
                    "978-0451524935"
            );

            Book book3 = new Book(
                    "El amor en los tiempos del cólera",
                    "Gabriel García Márquez",
                    1985,
                    "978-0307389732"
            );

            library.addBook(book1);
            library.addBook(book2);
            library.addBook(book3);

            garciaMarquez.addBook(book1);
            garciaMarquez.addBook(book3);
            orwell.addBook(book2);

            // Crear usuarios
            User user1 = new User(
                    "Marco",
                    "marco@email.com",
                    "1234"
            );

            User user2 = new User(
                    "Ana",
                    "ana@email.com",
                    "5678"
            );

            library.addUser(user1);
            library.addUser(user2);

            // Mostrar libros
            System.out.println("=== LIBROS DE LA BIBLIOTECA ===");
            library.getBooks().forEach(System.out::println);

            // Streams y Lambdas: búsqueda por autor
            System.out.println("\n=== LIBROS DE GABRIEL GARCÍA MÁRQUEZ ===");
            library.searchBooksByAuthor("Gabriel García Márquez")
                    .forEach(System.out::println);

            // Streams: filtrado por año
            System.out.println("\n=== LIBROS PUBLICADOS DESPUÉS DE 1950 ===");
            library.getBooksPublishedAfter(1950)
                    .forEach(System.out::println);

            // Streams: transformación a títulos
            System.out.println("\n=== TÍTULOS ===");
            library.getBookTitles()
                    .forEach(System.out::println);

            // Préstamo
            System.out.println("\n=== PRÉSTAMO ===");
            library.requestLoan(
                    "marco@email.com",
                    "978-0307474728"
            );

            System.out.println(
                    "Solicitud procesada: "
                            + library.processNextLoanRequest()
            );

            // Usuarios con libros prestados
            System.out.println("\n=== USUARIOS CON PRÉSTAMOS ===");
            library.getUsersWithBorrowedBooks()
                    .forEach(System.out::println);

            // Demostrar excepción de libro no disponible
            System.out.println("\n=== MANEJO DE EXCEPCIONES ===");

            try {
                library.requestLoan(
                        "ana@email.com",
                        "978-0307474728"
                );
            } catch (BookNotAvailableException e) {
                System.out.println("Error controlado: " + e.getMessage());
            }

            // Demostrar excepción de usuario no registrado
            try {
                library.requestLoan(
                        "usuario@inexistente.com",
                        "978-0451524935"
                );
            } catch (UserNotFoundException e) {
                System.out.println("Error controlado: " + e.getMessage());
            }

            // Demostrar excepción de duplicado
            try {
                library.addBook(book1);
            } catch (DuplicateElementException e) {
                System.out.println("Error controlado: " + e.getMessage());
            }

        } catch (DuplicateElementException
                 | UserNotFoundException
                 | BookNotAvailableException e) {

            System.out.println(
                    "Error en la aplicación: " + e.getMessage()
            );
        }
    }
}
