package com.boot.controller;

import com.boot.models.Book;
import com.boot.models.Borrowed;
import com.boot.services.BookService;
import com.boot.services.BorrowedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    public BookService bookService;

    @Autowired
    public BorrowedService borrowedService;


    @RequestMapping(method = RequestMethod.GET)
    public Collection<Book> getAllReaderBooks(int user_id){
        return bookService.getAll(user_id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public boolean getAllReaderBooks( @RequestBody Book book){
        return bookService.addBook(book);
    }

    @RequestMapping(value = "{book_id}", method = RequestMethod.GET)
    public Book getBook(@PathVariable int book_id){
        return bookService.getBook(book_id);
    }

    @RequestMapping(value = "{book_id}", method = RequestMethod.PUT)
    public boolean modifyBook(@PathVariable int book_id, @RequestBody Book book){
        return bookService.modifyBook(book);
    }

    @RequestMapping(value = "{book_id}", method = RequestMethod.DELETE)
    public boolean deleteBook(@PathVariable int book_id){
        return bookService.deleteBook(book_id);
    }

    @RequestMapping(value = "{book_id}/lend", method = RequestMethod.GET)
    public Borrowed getBorrowed(@PathVariable int book_id){
        return borrowedService.getBorrowed(book_id);
    }

    @RequestMapping(value = "{book_id}/lend", method = RequestMethod.POST)
    public boolean modifyBorrowed(@PathVariable int book_id, @RequestBody Borrowed borrowed){
        return borrowedService.modifyBorrowed(borrowed);
    }

    @RequestMapping(value = "{book_id}/lend", method = RequestMethod.PUT)
    public boolean createBorrowed(@PathVariable int book_id, @RequestBody Borrowed borrowed){
        return borrowedService.createBorrowed(borrowed);
    }

    @RequestMapping(value = "{book_id}/lend", method = RequestMethod.DELETE)
    public boolean deleteBorrowed(@PathVariable int book_id){
        return borrowedService.deleteBorrowed(book_id);
    }

}
