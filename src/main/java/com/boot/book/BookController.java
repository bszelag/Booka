package com.boot.book;

import com.boot.book.model.Book;
import com.boot.borrowed.model.Borrowed;
import com.boot.borrowed.BorrowedService;
import com.boot.security.AuthorizationService;
import com.boot.security.utility.Session;
import com.boot.user.UserService;
import com.boot.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/books") //"/api/v1/" is required till we use nginx
public class BookController {

    @Autowired
    public BookService bookService;

    @Autowired
    public BorrowedService borrowedService;

    @Autowired
    public UserService userService;


    @RequestMapping(value = "user/{user_id}", method = RequestMethod.GET)
    public Collection<Book> getUserBooks(@PathVariable String user_id){
        return bookService.getAllUserBooks(user_id);
    }

    @RequestMapping(value = "user/{user_id}", method = RequestMethod.POST)
    public ResponseEntity<Book> addUserBook(@RequestBody Book book, @PathVariable String user_id){
        Optional<User> user = userService.getById(user_id);
        if (user.isPresent())
        {
            book.setUser(user.get());
            return new ResponseEntity<>(bookService.addBook(book), HttpStatus.OK); }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

    @RequestMapping(value = "lend/<book_id>/<user_id>", method = RequestMethod.POST)
    public ResponseEntity<Borrowed> addBorrowed(@PathVariable Integer book_id, @PathVariable String user_id,
                                  @RequestBody Borrowed borrowed){

        Optional<User> user = userService.getById(user_id);
        Optional<Book> book = bookService.getBook(book_id);
        if (user.isPresent() && book.isPresent() && !book.get().getStatus()) {
            borrowed.setBorrower(user.get());
            borrowed.setBook(book.get());
            return new ResponseEntity<>(borrowedService.addBorrowed(borrowed), HttpStatus.OK) ;

        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
