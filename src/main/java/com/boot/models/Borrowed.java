package com.boot.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Borrowed implements Serializable {

    @Id
    @SequenceGenerator(name="borrowed_idborrowed_seq",
            sequenceName="borrowed_idborrowed_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.IDENTITY,
            generator="borrowed_idborrowed_seq")
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
