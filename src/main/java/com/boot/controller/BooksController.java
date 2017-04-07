package com.boot.controller;

import com.boot.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BookRepository br;

    @RequestMapping(method = RequestMethod.GET)
    public String books() {
        return "books/ShowBooks";
    }
}
