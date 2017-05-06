package com.boot.borrowed;


import com.boot.borrowed.model.Borrowed;
import org.springframework.stereotype.Service;

@Service
public interface BorrowedService {

    public Borrowed getBorrowed(int book_id);

    public boolean createBorrowed(Borrowed borrowed);

    public boolean modifyBorrowed(Borrowed borrowed);

    public boolean deleteBorrowed(int borrowed_id);
}
