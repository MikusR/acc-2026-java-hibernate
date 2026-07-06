package bootcamp.hibernate_practical.controller;

import bootcamp.hibernate_practical.dto.BookResponse;
import bootcamp.hibernate_practical.dto.CreateBookRequest;
import bootcamp.hibernate_practical.entity.Book;
import bootcamp.hibernate_practical.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BookControllerTest {

    @Autowired
    private BookController bookController;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    void shouldCreateNewBookSuccessfully() {
        CreateBookRequest request = new CreateBookRequest();
        ReflectionTestUtils.setField(request, "title", "Clean Code");
        ReflectionTestUtils.setField(request, "author", "Robert C. Martin");
        ReflectionTestUtils.setField(request, "genre", "Software Engineering");
        ReflectionTestUtils.setField(request, "publicationYear", 2008);

        BookResponse response = bookController.createBook(request);

        // Assert response
        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals("Clean Code", response.getTitle());
        assertEquals("Robert C. Martin", response.getAuthor());
        assertEquals("Software Engineering", response.getGenre());
        assertEquals(2008, response.getPublicationYear());
        assertTrue(response.isAvailable());

        // Verify the database contains the saved book directly
        List<Book> books = bookRepository.findAll();
        assertEquals(1, books.size());
        Book savedBook = books.get(0);
        assertEquals("Clean Code", savedBook.getTitle());
        assertEquals("Robert C. Martin", savedBook.getAuthor());
        assertEquals("Software Engineering", savedBook.getGenre());
        assertEquals(2008, savedBook.getPublicationYear());
        assertTrue(savedBook.isAvailable());
    }
}
