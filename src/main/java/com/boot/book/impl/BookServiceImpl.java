package com.boot.book.impl;

import com.boot.book.BookService;
import com.boot.book.model.Book;
import com.boot.book.repository.BookRepository;
import com.boot.book.tag.model.Tag;
import com.boot.book.tag.repository.TagBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TagBookRepository tagBookRepository;

    @Override
    public Optional<Book> getBook(int book_id) {
        return Optional.ofNullable(bookRepository.findOne(book_id));
    }

    @Override
    public Collection<Object> getAllUserBooksWithTags(Integer user_id) {
        Collection<Book> books = bookRepository.findByUserId(user_id);
        return books.stream().map(b -> new JsonWrapper<>(b,tagBookRepository.getBookTags(b))).collect(Collectors.toList());
    }

    @Override
    public Optional<Object> getBookWithTags(int book_id) {
        Book book = bookRepository.findOne(book_id);
        Collection<Tag> tags = tagBookRepository.getBookTags(book);
        return Optional.ofNullable(new JsonWrapper<>(book,tags));
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
    public Book modifyBook(Book book) throws IllegalArgumentException {
        if (bookRepository.exists(book.getId())) {
            try {
                return bookRepository.save(book);
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



