package com.boot.book.tag.impl;

import com.boot.book.tag.TagService;
import com.boot.book.tag.model.Tag;
import com.boot.book.tag.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class TagServiceImpl implements TagService{

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Collection<Tag> getTags() {
        return StreamSupport.stream(tagRepository.findAll().spliterator(), false).
                collect(Collectors.toList());
    }

    @Override
    public Optional<Tag> getTagByTitle(String title) {
        return Optional.ofNullable(tagRepository.findOne(title));
    }

    @Override
    public Tag addTag(Tag tag)throws IllegalArgumentException {
        try {
            return tagRepository.save(tag);
        }
        catch (DataAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void deleteTag(String title) throws IllegalArgumentException {
        if (title == null)
            throw new IllegalArgumentException("Cannot delete tag with unspecified title");

        if (!tagRepository.exists(title))
            throw new IllegalArgumentException("Cannot delete tag that does not exist");

        try {
            tagRepository.delete(title);
        }
        catch (DataAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
