package com.boot.borrowed.impl;

import com.boot.borrowed.BorrowedService;
import com.boot.borrowed.model.Borrowed;
import com.boot.borrowed.repository.BorrowedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class BorrowedServiceImpl implements BorrowedService {

    @Autowired
    private BorrowedRepository borrowedRepository;

    @Override
    public Borrowed getBorrowed(int book_id) {
        return borrowedRepository.findByBookId(book_id);
    }

    @Override
    public Borrowed addBorrowed(Borrowed borrowed) {
        borrowedRepository.save(borrowed);
        return borrowed;
    }

    @Override
    public Collection<Borrowed> getBorrowedByOwner(String user_id) {
        return borrowedRepository.findByOwner(user_id);
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
