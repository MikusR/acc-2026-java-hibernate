package bootcamp.hibernate_practical.service;

import bootcamp.hibernate_practical.dto.BookResponse;
import bootcamp.hibernate_practical.dto.CreateBookRequest;
import bootcamp.hibernate_practical.dto.UpdateBookRequest;
import bootcamp.hibernate_practical.entity.Book;
import bootcamp.hibernate_practical.exception.BookNotFoundException;
import bootcamp.hibernate_practical.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponse createBook(CreateBookRequest request) {
        Book book = new Book(request.getTitle(), request.getAuthor(), request.getGenre(), request.getPublicationYear(), true);
        Book savedBook = bookRepository.save(book);
        return mapToResponse(savedBook);
    }

    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::mapToResponse).toList();
    }

    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));
        return mapToResponse(book);
    }

    public BookResponse updateBook(Long id, UpdateBookRequest request) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setGenre(request.getGenre());
        book.setPublicationYear(request.getPublicationYear());
        Book savedBook = bookRepository.save(book);
        return mapToResponse(savedBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<BookResponse> findByAuthor(String author) {
        return bookRepository.findByAuthor(author).stream().map(this::mapToResponse).toList();
    }

    public List<BookResponse> findByPublicationYearGreaterThan(int publicationYear) {
        return bookRepository.findByPublicationYearGreaterThan(publicationYear).stream().map(this::mapToResponse).toList();
    }

    public List<BookResponse> findAvailableBooks() {
        return bookRepository.findByAvailableTrue().stream().map(this::mapToResponse).toList();
    }

    public List<BookResponse> findByTitleContainingIgnoreCase(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title).stream().map(this::mapToResponse).toList();
    }

    private BookResponse mapToResponse(Book book) {
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getPublicationYear(), book.isAvailable());
    }
}
