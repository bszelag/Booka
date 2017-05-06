package com.boot.tag;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class TagBook implements Serializable {

    @EmbeddedId
    private TagBookId tagBook;

    public TagBook() {
    }

    public TagBook(TagBookId tagBook) {
        this.tagBook = tagBook;
    }

    public TagBookId getTagBook() {
        return tagBook;
    }

    public void setTagBook(TagBookId tagBook) {
        this.tagBook = tagBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagBook tagBook1 = (TagBook) o;

        return tagBook.equals(tagBook1.tagBook);
    }

    @Override
    public int hashCode() {
        return tagBook.hashCode();
    }

    @Override
    public String toString() {
        return "TagBook{" +
                "tagBook=" + tagBook +
                '}';
    }
}
