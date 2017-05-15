package com.boot.book;

import com.boot.book.model.Book;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface BookService {

    public Collection<Book> getAllUserBooks(Integer user_id);

    public Optional<Book> getBook(int book_id);

    public Book addBook(Book book);

    public boolean modifyBook(Book book);

    public boolean deleteBook(Integer book_id);
}
