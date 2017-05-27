package com.boot.book.impl;

import com.boot.book.BookService;
import com.boot.book.model.Book;
import com.boot.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Collection<Book> getAllUserBooks(Integer user_id) {
        return StreamSupport.stream(bookRepository.findByUserId(user_id).spliterator(), false).
                collect(Collectors.toList());
    }

    @Override
    public Optional<Book> getBook(int book_id) {
        return Optional.ofNullable(bookRepository.findOne(book_id));
    }

    @Override
    public Book addBook(Book book) throws IllegalArgumentException  {
        try {
            return bookRepository.save(book);
        }
        catch (DataAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void modifyBook(Book book) throws IllegalArgumentException {
        if (bookRepository.exists(book.getId())) {
            try {
                bookRepository.save(book);
            }
            catch (DataAccessException e) {
                throw new IllegalArgumentException(e);
            }
        } else
            throw new IllegalArgumentException("Book does not exist");
    }

    @Override
    public void deleteBook(Integer book_id) throws IllegalArgumentException {
        if (book_id == null)
            throw new IllegalArgumentException("Cannot delete book with unspecified id");

        if (!bookRepository.exists(book_id))
            throw new IllegalArgumentException("Cannot delete book that does not exist");

        try {
            bookRepository.delete(book_id);
        }
        catch (DataAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }
}



