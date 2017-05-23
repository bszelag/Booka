package com.boot.authorizedViewers;

import com.boot.authorizedViewers.model.AuthorizedViewers;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface AuthorizedViewersService {

    public Collection<AuthorizedViewers> getAuthorizedViewers(Integer owner_id);

    public AuthorizedViewers addAuthorizedViewer(AuthorizedViewers authorizedViewer);

}
