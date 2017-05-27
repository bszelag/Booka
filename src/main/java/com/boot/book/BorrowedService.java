package com.boot.book;


import com.boot.book.model.Borrowed;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface BorrowedService {

    Borrowed addBorrowed(Borrowed borrowed) throws IllegalArgumentException;

    Collection<Borrowed> getBorrowedByOwner(Integer user_id);

    Collection<Borrowed> getBorrowedByBorrower(Integer user_id);

    Optional<Borrowed> getBorrowedById(Integer borrowed_id);

    Optional<Borrowed> getBorrowedByBookId(Integer book_id);

    void modifyBorrowed(Borrowed borrowed) throws IllegalArgumentException;

    void deleteBorrowed(Integer borrowed_id) throws IllegalArgumentException;
}
