package com.boot.book.tag.repository;


import com.boot.book.tag.model.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, String> {
}
