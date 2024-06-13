package org.example.tpo_12.service;

import org.example.tpo_12.model.Book;
import org.example.tpo_12.model.BookDTO;
import org.springframework.stereotype.Service;

@Service
public class BookDTOMapper {

    public BookDTO map(Book book){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setTitle(book.getTitle());
        return bookDTO;
    }

    public Book map(BookDTO bookDTO){
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setAuthor(bookDTO.getAuthor());
        book.setPrice(bookDTO.getPrice());
        book.setTitle(bookDTO.getTitle());
        return book;
    }
}
