package com.boot.tag;

import com.boot.tag.TagBook;
import com.boot.tag.TagBookId;
import org.springframework.data.repository.CrudRepository;

public interface TagBookRepository extends CrudRepository<TagBook, TagBookId> {
}
