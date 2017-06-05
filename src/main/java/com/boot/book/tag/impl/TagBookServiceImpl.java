package com.boot.book.tag.impl;

import com.boot.book.model.Book;
import com.boot.book.tag.TagBookService;
import com.boot.book.tag.model.Tag;
import com.boot.book.tag.model.TagBook;
import com.boot.book.tag.model.TagBookId;
import com.boot.book.tag.repository.TagBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class TagBookServiceImpl implements TagBookService{

    @Autowired
    private TagBookRepository tagBookRepository;

    @Override
    public Collection<Book> getBooksByTag(Tag tag, Collection<Integer> users_ids) {
        return tagBookRepository.getBooksByTag(tag, users_ids);
    }

    @Override
    public Collection<Tag> getBookTags(Book book) {
        return tagBookRepository.getBookTags(book);
    }

    @Override
    public TagBook addTagToBook(TagBook tagBook) throws IllegalArgumentException {
        try {
            return tagBookRepository.save(tagBook);
        }
        catch (DataAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void removeTagFromBook(TagBookId tagBookId) throws IllegalArgumentException {
        if (tagBookId == null)
            throw new IllegalArgumentException("Cannot delete tag from book with unspecified both of them");

        if (!tagBookRepository.exists(tagBookId))
            throw new IllegalArgumentException("Cannot delete tag from book that does not exist");

        try {
            tagBookRepository.delete(tagBookId);
        }
        catch (DataAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
