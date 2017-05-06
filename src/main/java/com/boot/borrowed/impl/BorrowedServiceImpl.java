package com.boot.borrowed;

import com.boot.borrowed.Borrowed;
import com.boot.borrowed.BorrowedRepository;
import com.boot.borrowed.BorrowedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BorrowedServiceImpl implements BorrowedService {

    @Autowired
    private BorrowedRepository borrowedRepository;

    @Override
    public Borrowed getBorrowed(int book_id) {
        return borrowedRepository.findByBookId(book_id);
    }

    @Override
    public boolean createBorrowed(Borrowed borrowed) {
        borrowedRepository.save(borrowed);
        return true;
    }

    @Override
    public boolean modifyBorrowed(Borrowed borrowed) {
        borrowedRepository.save(borrowed);
        return true;
    }

    @Override
    public boolean deleteBorrowed(int book_id) {
        Borrowed borrowed = borrowedRepository.findByBookId(book_id);
        if(borrowedRepository.exists(borrowed.getId())) {
            borrowedRepository.delete(borrowed.getId());
            return true;
        } else
            return false;
    }
}
