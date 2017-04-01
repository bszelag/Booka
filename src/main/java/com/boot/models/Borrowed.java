package com.boot.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Borrowed implements Serializable {

    @Id
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Borrowed borrowed = (Borrowed) o;

        if (id != null ? !id.equals(borrowed.id) : borrowed.id != null) return false;
        if (book != null ? !book.equals(borrowed.book) : borrowed.book != null) return false;
        if (borrower != null ? !borrower.equals(borrowed.borrower) : borrowed.borrower != null) return false;
        if (message != null ? !message.equals(borrowed.message) : borrowed.message != null) return false;
        if (dateStart != null ? !dateStart.equals(borrowed.dateStart) : borrowed.dateStart != null) return false;
        return dateStop != null ? dateStop.equals(borrowed.dateStop) : borrowed.dateStop == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (book != null ? book.hashCode() : 0);
        result = 31 * result + (borrower != null ? borrower.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (dateStop != null ? dateStop.hashCode() : 0);
        return result;
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
