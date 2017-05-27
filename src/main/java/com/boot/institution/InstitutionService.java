package com.boot.institution;

import com.boot.institution.department.model.Department;
import com.boot.institution.model.Institution;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface InstitutionService {

    Collection<Institution> getAllInstitutions();

    Collection<Department> getInstitutionDepartments(Integer name);

}
