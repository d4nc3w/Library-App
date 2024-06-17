package org.example.tpo_12.controller;

import org.example.tpo_12.model.BookDTO;
import org.example.tpo_12.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping()
    public String getHome(Model model){
        model.addAttribute("books", bookService.getAllBooks());
        return "index";
    }

    @GetMapping("book")
    public String getBook(@RequestParam Integer id, Model model){
        bookService.findBookById(id).ifPresent(book -> model.addAttribute("book", book));
        return "book";
    }

    @GetMapping("addBook")
    public String addBookForm(Model model) {
        model.addAttribute("book", new BookDTO());
        return "add-book";
    }

    @PostMapping("addBook")
    public String addBook(BookDTO bookDTO) {
        bookService.addBook(bookDTO);
        return "redirect:/";
    }

    @GetMapping("borrowBook")
    public String borrowBook(@RequestParam Integer id, Model model) {
        try {
            bookService.borrowBook(id);
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "borrowerror";
        }
        return "redirect:/borrowsuccess";
    }

    @GetMapping("borrowsuccess")
    public String borrowSuccess() {
        return "borrowsuccess";
    }

    @GetMapping("borrowerror")
    public String borrowError() {
        return "borrowerror";
    }
}
