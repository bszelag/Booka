package com.boot.book.repository;

import com.boot.book.model.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {

    @Query("Select b" +
            " FROM Book b" +
            " JOIN b.user u" +
            " WHERE u.login = :user_id")
    List<Book> findByUserId(@Param("user_id") String user_id);

    @Modifying
    @Transactional
    @Query("UPDATE Book b" +
            " SET b.user = null" +
            " WHERE b.user.login = :user_id" +
            " AND b in (SELECT br.book FROM Borrowed br)")
    void removeBookByUserId(@Param("user_id") String user_id);

}
