package com.boot.institution.repository;

import com.boot.institution.model.Institution;
import org.springframework.data.repository.CrudRepository;

public interface InstitutionRepository extends CrudRepository<Institution, String> {
}
