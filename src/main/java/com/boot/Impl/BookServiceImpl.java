package com.boot.Impl;

import com.boot.models.Book;
import com.boot.repositories.BookRepository;
import com.boot.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Collection<Book> getAll(int user_id) {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Book getBook(int book_id) {
        return bookRepository.findOne(book_id);
    }

    @Override
    public boolean addBook(Book book) {
        if(bookRepository.exists(book.getId())){
            bookRepository.save(book);
            return true;
        } else
            return false;
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
    public boolean deleteBook(int book_id) {
        if(bookRepository.exists(book_id)) {
            bookRepository.delete(book_id);
            return true;
        } else
            return false;
    }
}
