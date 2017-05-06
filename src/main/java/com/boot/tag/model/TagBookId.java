package com.boot.tag.model;

import com.boot.book.model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagBookId implements Serializable {

    @ManyToOne
    private Tag tagTittle;

    @ManyToOne
    private Book book;
}
