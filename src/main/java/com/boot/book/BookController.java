package com.boot.book;

import com.boot.book.model.Book;
import com.boot.borrowed.model.Borrowed;
import com.boot.borrowed.BorrowedService;
import com.boot.security.AuthorizationService;
import com.boot.security.utility.Session;
import com.boot.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/books") //"/api/v1/" is required till we use nginx
public class BookController {

    @Autowired
    public BookService bookService;

    @Autowired
    public BorrowedService borrowedService;

    @Autowired
    public AuthorizationService authorizationService;


    @RequestMapping(method = RequestMethod.GET)
    public Collection<Book> getLoggedUserBooks(@CookieValue(Session.COOKIE_NAME) String sessionToken){
        return authorizationService.getSession(UUID.fromString(sessionToken)).
                map(u -> bookService.getUserAll(u.getUser().getLogin())).orElse(null);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Book> addLoggedUserBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.OK);
    }

    @RequestMapping(value = "{book_id}", method = RequestMethod.GET)
    public ResponseEntity<Book> getUserBook(@PathVariable int book_id){
        return bookService.getBook(book_id).map(b -> new ResponseEntity<>(b, HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "{book_id}", method = RequestMethod.PUT)
    public ResponseEntity<Book> modifyUserBook(@RequestBody Book book){
        if (bookService.modifyBook(book))
           return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "{book_id}", method = RequestMethod.DELETE)
    public ResponseEntity<Book> deleteUserBook(@PathVariable int book_id){
        if (bookService.deleteBook(book_id))
            return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
