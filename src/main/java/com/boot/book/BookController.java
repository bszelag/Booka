package com.boot.book;

import com.boot.book.model.Book;
import com.boot.borrowed.model.Borrowed;
import com.boot.borrowed.BorrowedService;
import com.boot.security.AuthorizationService;
import com.boot.security.utility.Session;
import com.boot.user.UserService;
import com.boot.user.model.User;
import com.boot.utilities.mergeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
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
    public Collection<Book> getUserBooks(@PathVariable Integer user_id){
        return bookService.getAllUserBooks(user_id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        try {
            return ResponseEntity.ok(bookService.addBook(book));
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "{book_id}", method = RequestMethod.GET)
    public ResponseEntity<Book> getUserBook(@PathVariable int book_id){
        return bookService.getBook(book_id).map(b -> new ResponseEntity<>(b, HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Book> modifyUserBook(@RequestBody Book book) {
        try {
            bookService.modifyBook(book);
            return new ResponseEntity<>(HttpStatus.OK); }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
    }


    @RequestMapping(value = "{book_id}", method = RequestMethod.DELETE)
    public ResponseEntity<Book> deleteUserBook(@PathVariable int book_id){
        try {
            bookService.deleteBook(book_id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "lend", method = RequestMethod.POST)
    public ResponseEntity<Borrowed> addBorrowed(@RequestBody Borrowed borrowed){

        if (borrowed.getBook() == null || borrowed.getBorrower() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!borrowed.getBook().getStatus()) {
            borrowed.getBook().setStatus(true);
            bookService.modifyBook(borrowed.getBook());
            return new ResponseEntity<>(borrowedService.addBorrowed(borrowed), HttpStatus.OK) ;
        }
        else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "lend/user/{user_id}", method = RequestMethod.GET)
    public Collection<Borrowed> getUsersAllLend(@PathVariable Integer user_id) {
        return borrowedService.getBorrowedByOwner(user_id);
    }

    @RequestMapping(value = "lend/{lend_id}", method = RequestMethod.GET)
    public ResponseEntity<Borrowed> getLend(@PathVariable Integer lend_id) {
        return borrowedService.getBorrowedById(lend_id).map(b -> new ResponseEntity<>(b, HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "lend", method = RequestMethod.PUT)
    public ResponseEntity<Borrowed> updateLend(@RequestBody Borrowed borrowed)
            throws InstantiationException, IllegalAccessException {
            if (borrowed.getId() == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Optional<Borrowed> originalBorrowed = borrowedService.getBorrowedById(borrowed.getId());
            if (originalBorrowed.isPresent()) {
                Borrowed finalBorrowed = mergeTool.mergeObjects(borrowed,originalBorrowed.get());
                borrowedService.modifyBorrowed(finalBorrowed);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "lend/{lend_id}", method = RequestMethod.DELETE)
    public ResponseEntity<Borrowed> deleteLend(@PathVariable Integer lend_id) {
        Borrowed borrowed = borrowedService.getBorrowedById(lend_id).orElse(null);

        if (borrowed == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        borrowed.getBook().setStatus(false);
        bookService.modifyBook(borrowed.getBook());
        borrowedService.deleteBorrowed(lend_id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(value = "borrowed/user/{user_id}", method = RequestMethod.GET)
    public Collection<Borrowed> getUsersAllBorrowed(@PathVariable Integer user_id) {
        return borrowedService.getBorrowedByBorrower(user_id);
    }

    @RequestMapping(value = "lend/book/{book_id}", method = RequestMethod.GET)
    public ResponseEntity<Borrowed> getBorrowedByBook(@PathVariable Integer book_id) {
        return borrowedService.getBorrowedByBookId(book_id).map(b -> new ResponseEntity<>(b, HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
