package com.boot.borrowed;


import com.boot.borrowed.model.Borrowed;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface BorrowedService {

    Borrowed addBorrowed(Borrowed borrowed);

    Collection<Borrowed> getBorrowedByOwner(String user_id);

    Optional<Borrowed> getBorrowedById(int borrowed_id);

    boolean modifyBorrowed(Borrowed borrowed);

    boolean deleteBorrowed(int borrowed_id);
}
