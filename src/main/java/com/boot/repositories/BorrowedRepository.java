package com.boot.repositories;

import com.boot.models.Borrowed;
import org.springframework.data.repository.CrudRepository;

public interface BorrowedRepository extends CrudRepository<Borrowed, Integer> {
}
