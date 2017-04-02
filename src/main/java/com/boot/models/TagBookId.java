package com.boot.models;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class TagBookId implements Serializable {

    @ManyToOne
    private Tag tagTittle;

    @ManyToOne
    private Book book;

    public TagBookId() {
    }

    public TagBookId(Tag tagTittle, Book book) {
        this.tagTittle = tagTittle;
        this.book = book;
    }

    public Tag getTagTittle() {
        return tagTittle;
    }

    public void setTagTittle(Tag tagTittle) {
        this.tagTittle = tagTittle;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagBookId tagBookId = (TagBookId) o;

        return (tagTittle.equals(tagBookId.tagTittle) && book.equals(tagBookId.book));
    }

    @Override
    public int hashCode() {
        int result = tagTittle.hashCode();
        result = 31 * result + book.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TagBookId{" +
                "tagTittle=" + tagTittle +
                ", book=" + book +
                '}';
    }
}
