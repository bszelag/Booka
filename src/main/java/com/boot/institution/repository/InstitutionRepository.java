package com.boot.institution.repository;

import com.boot.institution.model.Institution;
import com.boot.security.model.VerificationToken;
import org.springframework.data.repository.CrudRepository;

public interface InstitutionRepository extends CrudRepository<Institution, String> {

}
