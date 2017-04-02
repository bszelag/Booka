package com.boot.repositories;

import com.boot.models.TagBook;
import com.boot.models.TagBookId;
import org.springframework.data.repository.CrudRepository;

public interface TagBookRepository extends CrudRepository<TagBook, TagBookId> {
}
