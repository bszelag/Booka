package com.boot.authorizedViewers.repository;

import com.boot.authorizedViewers.model.AuthorizedViewers;
import com.boot.authorizedViewers.model.AuthorizedViewersId;
import org.springframework.data.repository.CrudRepository;

public interface AuthorizedViewersRepository extends CrudRepository<AuthorizedViewers, AuthorizedViewersId> {
}
