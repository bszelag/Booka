package com.boot.institution;

import com.boot.institution.Institution;
import org.springframework.data.repository.CrudRepository;

public interface InstitutionRepository extends CrudRepository<Institution, String> {
}
