package org.example.tpo_12.service;

import jakarta.transaction.Transactional;
import org.example.tpo_12.model.Book;
import org.example.tpo_12.model.BookDTO;
import org.example.tpo_12.model.Rating;
import org.example.tpo_12.repository.BookRepository;
import org.example.tpo_12.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookService {
    private BookRepository bookRepository;
    private BookDTOMapper bookDTOMapper;
    private RatingRepository ratingRepository;

    public BookService(BookRepository bookRepository, BookDTOMapper bookDTOMapper, RatingRepository ratingRepository){
        this.bookRepository = bookRepository;
        this.bookDTOMapper = bookDTOMapper;
        this.ratingRepository = ratingRepository;
    }

    public List<BookDTO> getAllBooks(){
        Iterable<Book> books = bookRepository.findAll();
        List<BookDTO> booksDTO = new ArrayList<>();
        for(Book b : books){
            bookDTOMapper.map(b);
            booksDTO.add(bookDTOMapper.map(b));
        }
        return booksDTO;
    }

    public Optional<Book> findBookById(Integer id) {
        return bookRepository.findById(id);
    }

    public void addBook(BookDTO bookDTO){
        bookRepository.save(bookDTOMapper.map(bookDTO));
    }

    public void borrowBook(Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            Book b = book.get();
            if (b.isAvailable()) {
                b.setAvailable(false);
                bookRepository.save(b);
            } else {
                throw new RuntimeException("Book not available");
            }
        } else {
            throw new RuntimeException("Book was not found");
        }
    }

    public void changeAvailability(Integer id, Boolean available) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            Book b = book.get();
            b.setAvailable(available);
            bookRepository.save(b);
        } else {
            throw new RuntimeException("Book not found");
        }
    }

    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    public void updateBook(BookDTO bookDTO) {
        bookRepository.save(bookDTOMapper.map(bookDTO));
    }

    @Transactional
    public void saveRating(Rating rating) {
        ratingRepository.save(rating);
    }

    public int calculateAvgRating(Integer id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            return book.getAverageRating();
        } else{
            return 0;
        }
    }
}
