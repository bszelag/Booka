package com.boot.Impl;

import com.boot.models.Borrowed;
import com.boot.repositories.BorrowedRepository;
import com.boot.services.BorrowedService;
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
