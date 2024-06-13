package org.example.tpo_12.controller;

import org.example.tpo_12.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String getHome(Model model){
        model.addAttribute("books", bookService.getAllBooks());
        return "index";
    }
}
