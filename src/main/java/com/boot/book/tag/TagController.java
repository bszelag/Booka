package com.boot.book.tag;


import com.boot.book.tag.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/v1/tags")
public class TagController {

    @Autowired
    public TagService tagService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Tag> getTags(){
        return tagService.getTags();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Tag> addTag(@RequestBody Tag tag){
        try {
            return ResponseEntity.ok(tagService.addTag(tag));
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Tag> deleteTag(@RequestBody String title) {
        Tag tag = tagService.getTagByTitle(title).orElse(null);

        if (tag == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            tagService.deleteTag(title);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
