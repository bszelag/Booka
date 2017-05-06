package com.boot.borrowed.repository;

import com.boot.borrowed.model.Borrowed;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BorrowedRepository extends CrudRepository<Borrowed, Integer> {

    @Query("SELECT br" +
            " FROM Borrowed br" +
            " JOIN br.book bk" +
            " WHERE bk.id = :id")
    Borrowed findByBookId(@Param("id") Integer id);

}
