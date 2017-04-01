package com.boot.repositories;

import com.boot.models.Institution;
import org.springframework.data.repository.CrudRepository;

public interface InstitutionRepository extends CrudRepository<Institution, String> {
}
