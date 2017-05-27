package com.boot.book.tag.repository;

import com.boot.book.tag.model.TagBook;
import com.boot.book.tag.model.TagBookId;
import org.springframework.data.repository.CrudRepository;

public interface TagBookRepository extends CrudRepository<TagBook, TagBookId> {
}
