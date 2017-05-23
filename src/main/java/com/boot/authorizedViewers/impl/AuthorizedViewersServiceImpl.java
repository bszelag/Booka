package com.boot.authorizedViewers.impl;

import com.boot.authorizedViewers.AuthorizedViewersService;
import com.boot.authorizedViewers.model.AuthorizedViewers;
import com.boot.authorizedViewers.repository.AuthorizedViewersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AuthorizedViewersServiceImpl implements AuthorizedViewersService {

    @Autowired
    private AuthorizedViewersRepository authorizedViewersRepository;

    @Override
    public Collection<AuthorizedViewers> getAuthorizedViewers(Integer owner_id) {
        return authorizedViewersRepository.getAuthorizedViewers(owner_id);
    }

    @Override
    public AuthorizedViewers addAuthorizedViewer(AuthorizedViewers authorizedViewer) {
        if(authorizedViewersRepository.findOne(authorizedViewer.getAuthorizedViewer())!=null){
            return null;
        }
        return authorizedViewersRepository.save(authorizedViewer);
    }
}
