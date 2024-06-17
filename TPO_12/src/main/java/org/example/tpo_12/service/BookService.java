package org.example.tpo_12.service;

import org.example.tpo_12.model.Book;
import org.example.tpo_12.model.BookDTO;
import org.example.tpo_12.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;
    private BookDTOMapper bookDTOMapper;

    public BookService(BookRepository bookRepository, BookDTOMapper bookDTOMapper){
        this.bookRepository = bookRepository;
        this.bookDTOMapper = bookDTOMapper;
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
                throw new RuntimeException("Book is not available");
            }
        } else {
            throw new RuntimeException("Book not found");
        }
    }
}
