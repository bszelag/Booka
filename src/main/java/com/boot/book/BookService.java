package com.boot.book;

import com.boot.book.model.Book;
import com.boot.book.tag.model.Tag;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface BookService {

    Optional<Book> getBook(int book_id);

    Collection<Object> getAllUserBooksWithTags(Integer user_id);

    Optional<Object> getBookWithTags(int book_id);

    Book addBook(Book book)  throws IllegalArgumentException;

    Book modifyBook(Book book) throws IllegalArgumentException;

    void deleteBook(Integer book_id) throws IllegalArgumentException;

    class JsonWrapper<T> {

        @JsonUnwrapped
        private T inner;
        private Collection<String> tags;

        public JsonWrapper(T inner, Collection<String> tags) {
            this.inner = inner;
            this.tags = tags;
        }

        public T getInner() {
            return inner;
        }

        public Collection<String> getTags(){
            return tags;
        }
    }
}
