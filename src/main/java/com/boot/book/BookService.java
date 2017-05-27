package com.boot.book;

import com.boot.book.model.Book;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface BookService {

    Collection<Book> getAllUserBooks(Integer user_id);

    Optional<Book> getBook(int book_id);

    Book addBook(Book book)  throws IllegalArgumentException;

    void modifyBook(Book book) throws IllegalArgumentException;

    void deleteBook(Integer book_id) throws IllegalArgumentException;
}
