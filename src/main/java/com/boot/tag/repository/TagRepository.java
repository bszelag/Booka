package com.boot.tag.repository;


import com.boot.tag.model.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, String> {
}
