package com.boot.borrowed.model;


import com.boot.book.model.Book;
import com.boot.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Borrowed implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Book book;

    @OneToOne(optional = true, fetch = FetchType.LAZY)
    private User borrower;
    private String name;
    private String email;
    private String facebook;
    private String message;
    private Date dateStart;
    private Date dateStop;
}
