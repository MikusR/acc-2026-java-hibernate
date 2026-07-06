package bootcamp.hibernate_practical.controller;

import bootcamp.hibernate_practical.dto.BookResponse;
import bootcamp.hibernate_practical.dto.CreateBookRequest;
import bootcamp.hibernate_practical.dto.UpdateBookRequest;
import bootcamp.hibernate_practical.service.BookService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public BookResponse createBook(@Valid @RequestBody CreateBookRequest createBookRequest) {
        return bookService.createBook(createBookRequest);
    }

    @GetMapping
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/stats")
    public Map<String, Long> getBookCount() {
        return Map.of("Books in Library", bookService.getBookCount(),
                "Available for Borrowing", bookService.getAvailableBookCount());
    }

    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    public BookResponse updateBook(@PathVariable Long id, @Valid @RequestBody UpdateBookRequest updateBookRequest) {
        return bookService.updateBook(id, updateBookRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/author/{author}")
    public List<BookResponse> getBooksByAuthor(@PathVariable String author) {
        return bookService.findByAuthor(author);
    }

    @GetMapping("/year/{publicationYear}")
    public List<BookResponse> getBooksByPublicationYear(@PathVariable int publicationYear) {
        return bookService.findByPublicationYearGreaterThan(publicationYear);
    }

    @GetMapping("/title/{title}")
    public List<BookResponse> getBooksByTitle(@PathVariable String title) {
        return bookService.findByTitleContainingIgnoreCase(title);
    }

    @GetMapping("/available")
    public List<BookResponse> getAvailableBooks() {
        return bookService.findAvailableBooks();
    }

    @PostMapping("/borrow/{id}")
    public BookResponse borrowBook(@PathVariable Long id) {
        return bookService.borrowBook(id);
    }

    @PostMapping("/return/{id}")
    public BookResponse returnBook(@PathVariable Long id) {
        return bookService.returnBook(id);
    }

}
