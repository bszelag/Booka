package com.boot.authorizedViewers.repository;

import com.boot.authorizedViewers.model.AuthorizedViewers;
import com.boot.authorizedViewers.model.AuthorizedViewersId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface AuthorizedViewersRepository extends CrudRepository<AuthorizedViewers, AuthorizedViewersId> {

    @Query("Select a" +
            " FROM AuthorizedViewers a" +
            " JOIN a.authorizedViewer av" +
            " JOIN av.owner u1" +
            " WHERE u1.id = :owner_id")
    Collection<AuthorizedViewers> getAuthorizedViewers(@Param("owner_id") Integer owner_id);
}
