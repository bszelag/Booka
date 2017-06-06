package com.boot.book.tag;

import com.boot.book.tag.model.TagBook;
import com.boot.book.tag.model.TagBookId;
import org.springframework.stereotype.Service;

@Service
public interface TagBookService {

    TagBook addTagToBook(TagBook tagBook);

    void removeTagFromBook(TagBookId tagBookId);
}
