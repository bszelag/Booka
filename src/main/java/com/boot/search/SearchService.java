package com.boot.search;

import com.boot.book.model.Book;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface SearchService {
    String searchQuery(LibraryQuery query);
    Collection<Book> searchBook(String URL);
}
