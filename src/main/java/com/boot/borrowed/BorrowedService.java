package com.boot.borrowed;


import com.boot.borrowed.model.Borrowed;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface BorrowedService {

    Borrowed addBorrowed(Borrowed borrowed);

    Collection<Borrowed> getBorrowedByOwner(Integer user_id);

    Collection<Borrowed> getBorrowedByBorrower(Integer user_id);

    Optional<Borrowed> getBorrowedById(Integer borrowed_id);

    Optional<Borrowed> getBorrowedByBookId(Integer book_id);

    boolean modifyBorrowed(Borrowed borrowed);

    boolean deleteBorrowed(Integer borrowed_id);
}
