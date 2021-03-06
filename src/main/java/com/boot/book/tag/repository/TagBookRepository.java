package com.boot.book.tag.repository;

import com.boot.book.model.Book;
import com.boot.book.tag.model.Tag;
import com.boot.book.tag.model.TagBook;
import com.boot.book.tag.model.TagBookId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface TagBookRepository extends CrudRepository<TagBook, TagBookId> {

    @Query("Select tt.title" +
            " FROM TagBook t" +
            " JOIN t.tagBook.tagTittle tt" +
            " JOIN t.tagBook.book b " +
            " WHERE b = :book")
    Collection<String> getBookTags(@Param("book") Book book);
}
