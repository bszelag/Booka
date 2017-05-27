package com.boot.borrowed.impl;

import com.boot.borrowed.BorrowedService;
import com.boot.borrowed.model.Borrowed;
import com.boot.borrowed.repository.BorrowedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class BorrowedServiceImpl implements BorrowedService {

    @Autowired
    private BorrowedRepository borrowedRepository;

    @Override
    public Optional<Borrowed> getBorrowedById(Integer borrowed_id) {
        return Optional.ofNullable(borrowedRepository.findOne(borrowed_id));
    }

    @Override
    public Optional<Borrowed> getBorrowedByBookId(Integer book_id) {
        return Optional.ofNullable(borrowedRepository.findByBookId(book_id));
    }

    @Override
    public Borrowed addBorrowed(Borrowed borrowed) {
        try {
            return borrowedRepository.save(borrowed);
        } catch (DataAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Collection<Borrowed> getBorrowedByOwner(Integer user_id) {
        return borrowedRepository.findByOwner(user_id);
    }

    @Override
    public Collection<Borrowed> getBorrowedByBorrower(Integer user_id) {
        return borrowedRepository.findByBorrowerId(user_id);
    }

    @Override
    public void modifyBorrowed(Borrowed borrowed) throws IllegalArgumentException {
        if (borrowedRepository.exists(borrowed.getId()))
            try {
                borrowedRepository.save(borrowed);
            }
            catch (DataAccessException e) {
                throw new IllegalArgumentException(e); }
        else
            throw new IllegalArgumentException("Borrowed does not exist");
    }

    @Override
    public void deleteBorrowed(Integer book_id) {
        if (book_id == null)
            throw new IllegalArgumentException("Cannot delete borrowed with unspecified id");

        if (!borrowedRepository.exists(book_id))
            throw new IllegalArgumentException("Cannot delete borrowed that does not exist");

        try {
            borrowedRepository.delete(book_id);
        }
        catch (DataAccessException e) {
            throw new IllegalArgumentException(e); }
    }
}
