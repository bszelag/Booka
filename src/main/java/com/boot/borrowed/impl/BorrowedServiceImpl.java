package com.boot.borrowed.impl;

import com.boot.borrowed.BorrowedService;
import com.boot.borrowed.model.Borrowed;
import com.boot.borrowed.repository.BorrowedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class BorrowedServiceImpl implements BorrowedService {

    @Autowired
    private BorrowedRepository borrowedRepository;

    @Override
    public Optional<Borrowed> getBorrowedById(int borrowed_id) {
        return Optional.ofNullable(borrowedRepository.findOne(borrowed_id));
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
    public Collection<Borrowed> getBorrowedByBorrower(String user_id) {
        return null;
    }

    @Override
    public boolean modifyBorrowed(Borrowed borrowed) {
        if (borrowedRepository.exists(borrowed.getId())) {
            borrowedRepository.save(borrowed);
            return true; }
        else
            return false;
    }

    @Override
    public boolean deleteBorrowed(Integer book_id) {
        if(borrowedRepository.exists(book_id)) {
            borrowedRepository.delete(book_id);
            return true;
        } else
            return false;
    }
}
