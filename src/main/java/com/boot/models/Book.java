package com.boot.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;

@Entity
public class Book implements Serializable {

    @Id
    @SequenceGenerator(name="book_idbook_seq",
            sequenceName="book_idbook_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="book_idbook_seq")
    private Integer id;

    @Column(nullable = false)
    private String title;
    private String author;

    @Column(nullable = false)
    private Character format;
    private String path;
    private String status;
    private Character ownerType;

    @ManyToOne
    private User user;

    @ManyToOne
    private Institution institution;

    public Book() {
    }

    public Book(Integer id, String title, String author, Character format, String path, String status,
                Character ownerType, User user, Institution institution) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.format = format;
        this.path = path;
        this.status = status;
        this.ownerType = ownerType;
        this.user = user;
        this.institution = institution;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Character getFormat() {
        return format;
    }

    public void setFormat(Character format) {
        this.format = format;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Character getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(Character ownerType) {
        this.ownerType = ownerType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", format=" + format +
                ", path='" + path + '\'' +
                ", status='" + status + '\'' +
                ", ownerType=" + ownerType +
                ", user=" + user +
                ", institution=" + institution +
                '}';
    }
}
