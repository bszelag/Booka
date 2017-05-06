package com.boot.services;

import com.boot.models.Book;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface BookService {

    public Collection<Book> getAll(int user_id);

    public Book getBook(int book_id);

    public boolean addBook(Book book);

    public boolean modifyBook(Book book);

    public boolean deleteBook(int book_id);
}
