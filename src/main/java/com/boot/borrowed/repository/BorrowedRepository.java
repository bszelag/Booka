package com.boot.borrowed.repository;

import com.boot.borrowed.model.Borrowed;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface BorrowedRepository extends CrudRepository<Borrowed, Integer> {

    @Query("SELECT br" +
            " FROM Borrowed br" +
            " JOIN br.book bk" +
            " WHERE bk.id = :id")
    Borrowed findByBookId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE Borrowed br" +
            " SET br.borrower = null, br.email = :user_email, br.facebook = :user_facebook" +
            " WHERE br.borrower.login = :user_id")
    void removeBorrowedsByUserId(@Param("user_id") String user_id,@Param("user_email") String user_email,@Param("user_facebook") String user_facebook);

    @Modifying
    @Transactional
    @Query("UPDATE Borrowed br" +
            " SET br.name = :user_name" +
            " WHERE br.borrower.login = :user_id" +
            " AND br.name = null")
    void updateBorrowerName(@Param("user_id") String user_id,@Param("user_name") String user_name);

    @Query("SELECT br" +
            " FROM Borrowed br" +
            " JOIN br.book bk" +
            " JOIN bk.user u" +
            " WHERE u.login = :user_id")
    List<Borrowed> findByOwner(@Param("user_id") String user_id);

    @Query("SELECT br" +
            " FROM Borrowed br" +
            " JOIN br.borrower bo" +
            " WHERE bo.login = :user_id")
    List<Borrowed> findByBorrowerId(@Param("user_id") String user_id);
}
