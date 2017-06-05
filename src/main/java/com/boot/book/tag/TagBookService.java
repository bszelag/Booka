package com.boot.book.tag;

import com.boot.book.model.Book;
import com.boot.book.tag.model.Tag;
import com.boot.book.tag.model.TagBook;
import com.boot.book.tag.model.TagBookId;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface TagBookService {

    Collection<Book> getBooksByTag(Tag tag, Collection<Integer> users_ids);

    Collection<Tag> getBookTags(Book book);

    TagBook addTagToBook(TagBook tagBook);

    void removeTagFromBook(TagBookId tagBookId);
}
