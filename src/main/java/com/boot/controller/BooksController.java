package com.boot.controller;

import com.boot.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
public class BooksController {

    @Autowired
    private BookRepository br;

    @RequestMapping(value = "books", method = RequestMethod.GET)
    public String books() {
        return "ksiazki";
    }
}
