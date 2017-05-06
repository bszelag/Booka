package com.boot.tag.repository;

import com.boot.tag.model.TagBook;
import com.boot.tag.model.TagBookId;
import org.springframework.data.repository.CrudRepository;

public interface TagBookRepository extends CrudRepository<TagBook, TagBookId> {
}
