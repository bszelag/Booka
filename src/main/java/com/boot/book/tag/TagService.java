package com.boot.book.tag;

import com.boot.book.tag.model.Tag;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface TagService {

    Collection<Tag> getTags();

    Optional<Tag> getTagByTitle(String title);

    Tag addTag(Tag tag);

    void deleteTag(String title);

}
