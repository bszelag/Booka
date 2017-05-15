package com.boot.book.impl;

import com.boot.book.BookService;
import com.boot.book.model.Book;
import com.boot.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Book addBook(Book book) {
            bookRepository.save(book);
            return book;
    }

    @Override
    public boolean modifyBook(Book book) {
        if(bookRepository.exists(book.getId())){
            bookRepository.save(book);
            return true;
        } else
            return false;
    }

    @Override
    public boolean deleteBook(Integer book_id) {
        if(bookRepository.exists(book_id)) {
            bookRepository.delete(book_id);
            return true;
        } else
            return false;
    }
}
