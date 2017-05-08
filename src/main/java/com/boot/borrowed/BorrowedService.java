package com.boot.borrowed;


import com.boot.borrowed.model.Borrowed;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface BorrowedService {

    Borrowed addBorrowed(Borrowed borrowed);

    Collection<Borrowed> getBorrowedByOwner(String user_id);

    Collection<Borrowed> getBorrowedByBorrower(String user_id);

    Optional<Borrowed> getBorrowedById(Integer borrowed_id);

    Optional<Borrowed> getBorrowedByBookId(Integer book_id);

    boolean modifyBorrowed(Borrowed borrowed);

    boolean deleteBorrowed(Integer borrowed_id);
}
