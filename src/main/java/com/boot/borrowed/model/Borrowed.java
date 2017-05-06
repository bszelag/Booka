package com.boot.borrowed;


import com.boot.book.Book;
import com.boot.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Borrowed implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(optional = false)
    private Book book;

    @OneToOne
    private User borrower;
    private String message;
    private Date dateStart;
    private Date dateStop;

    public Borrowed() {
    }

    public Borrowed(Integer id, Book book, User borrower, String message, Date dateStart, Date dateStop) {
        this.id = id;
        this.book = book;
        this.borrower = borrower;
        this.message = message;
        this.dateStart = dateStart;
        this.dateStop = dateStop;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getBorrower() {
        return borrower;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateStop() {
        return dateStop;
    }

    public void setDateStop(Date dateStop) {
        this.dateStop = dateStop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Borrowed borrowed = (Borrowed) o;

        return id.equals(borrowed.id);
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Borrowed{" +
                "id=" + id +
                ", book=" + book +
                ", borrower=" + borrower +
                ", message='" + message + '\'' +
                ", dateStart=" + dateStart +
                ", dateStop=" + dateStop +
                '}';
    }
}
