package org.example.tpo_12.controller;

import org.example.tpo_12.model.Book;
import org.example.tpo_12.model.BookDTO;
import org.example.tpo_12.model.Rating;
import org.example.tpo_12.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        model.addAttribute("avgRating", bookService.calculateAvgRating(id));
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

    @GetMapping("changeAvailability/book")
    public String changeAvailability(@RequestParam Integer id, @RequestParam Boolean available) {
        bookService.changeAvailability(id, available);
        return "redirect:/book?id=" + id;
    }

    @PostMapping("changeAvailability/book")
    public String changeAvailabilityPost(@RequestParam Integer id, @RequestParam Boolean available) {
        bookService.changeAvailability(id, available);
        return "redirect:/book?id=" + id;
    }

    @GetMapping("deleteBook")
    public String deleteBook(@RequestParam Integer id) {
        bookService.deleteBook(id);
        return "redirect:/";
    }

    @GetMapping("editBook")
    public String updateBookForm(@RequestParam Integer id, Model model) {
        bookService.findBookById(id).ifPresent(book -> model.addAttribute("book", book));
        return "edit-book";
    }

    @PostMapping("editBook")
    public String editBook(@ModelAttribute("book") BookDTO bookDTO) {
        bookService.updateBook(bookDTO);
        return "redirect:/";
    }

    @GetMapping("rateBook")
    public String rateBookForm(@RequestParam Integer id, Model model) {
        bookService.findBookById(id).ifPresent(book -> model.addAttribute("book", book));
        model.addAttribute("rating", new Rating());
        return "rate-book";
    }

    @PostMapping("rateBook")
    public String rateBook(@ModelAttribute("rating") Rating rating) {
        //bookService.findBookById(bookId).ifPresent(rating::setBook);
        bookService.saveRating(rating);
        return "redirect:/";
    }
}
