package com.boot.book;

import com.boot.book.model.Book;
import com.boot.book.model.Borrowed;
import com.boot.book.tag.TagBookService;
import com.boot.book.tag.model.Tag;
import com.boot.book.tag.model.TagBook;
import com.boot.security.AuthorizationService;
import com.boot.security.utility.Session;
import com.boot.user.UserService;
import com.boot.utilities.mergeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    public AuthorizationService authorizationService;

    @Autowired
    public TagBookService tagBookService;


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
    public ResponseEntity<Book> modifyUserBook(@RequestBody Book book)
            throws InstantiationException, IllegalAccessException {

        Optional<Book> originalBook = bookService.getBook(book.getId());
        if (!originalBook.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Book finalBook = mergeTool.mergeObjects(book, originalBook.get());
        try {
            return new ResponseEntity<>(bookService.modifyBook(finalBook), HttpStatus.OK); }
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

        if (borrowed.getBook() == null || borrowed.getBook().getId()==null || borrowed.getBorrower() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Book> originalBook = bookService.getBook(borrowed.getBook().getId());
        if (!originalBook.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        borrowed.setBook(originalBook.get());
        if (!borrowed.getBook().getStatus()) {
            borrowed.getBook().setStatus(true);
            try {
                bookService.modifyBook(borrowed.getBook());
            }
            catch  (IllegalArgumentException e) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(borrowedService.addBorrowed(borrowed), HttpStatus.OK) ;
        }
        else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "lend/user", method = RequestMethod.GET)
    public Collection<Borrowed> getUsersAllLend(@CookieValue(Session.COOKIE_NAME) String sessionToken) {
        return borrowedService.getBorrowedByOwner(authorizationService.getSession(UUID.fromString(sessionToken)).
                map(Session::getUser).map(u -> u.getId()).orElse(0));
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
                try {
                    borrowedService.modifyBorrowed(finalBorrowed);
                }
                catch (IllegalArgumentException e) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
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
        try {
            borrowedService.deleteBorrowed(lend_id);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(value = "borrowed/user", method = RequestMethod.GET)
    public Collection<Borrowed> getUsersAllBorrowed(@CookieValue(Session.COOKIE_NAME) String sessionToken) {
        return borrowedService.getBorrowedByBorrower(authorizationService.getSession(UUID.fromString(sessionToken)).
                map(Session::getUser).map(u -> u.getId()).orElse(0));
    }

    @RequestMapping(value = "lend/book/{book_id}", method = RequestMethod.GET)
    public ResponseEntity<Borrowed> getBorrowedByBook(@PathVariable Integer book_id) {
        return borrowedService.getBorrowedByBookId(book_id).map(b -> new ResponseEntity<>(b, HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "getBooksByTag", method = RequestMethod.GET)
    public ResponseEntity<Collection<Book>> getBooksByTag(@CookieValue(Session.COOKIE_NAME) String sessionToken, @RequestBody Tag tag) {
        return ResponseEntity.ok(tagBookService.getBooksByTag(tag));
    }

    @RequestMapping(value = "getBookTags/{book_id}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Tag>> getBookTags(@PathVariable Integer book_id) {
        Optional<Book> book = bookService.getBook(book_id);
        return book.map(book1 -> ResponseEntity.ok(tagBookService.getBookTags(book1)))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "addTagToBook", method = RequestMethod.POST)
    public ResponseEntity<TagBook> addTagToBook(@RequestBody TagBook tagBook){
        try {
            return ResponseEntity.ok(tagBookService.addTagToBook(tagBook));
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "removeTagFromBook", method = RequestMethod.DELETE)
    public ResponseEntity<TagBook> removeTagFromBook(@RequestBody TagBook tagBook){
        try {
            tagBookService.removeTagFromBook(tagBook.getTagBook());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
