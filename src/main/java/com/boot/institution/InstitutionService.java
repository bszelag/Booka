package com.boot.institution;

import com.boot.department.model.Department;
import com.boot.institution.model.Institution;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface InstitutionService {

    public Collection<Institution> getAllInstitutions();

    public Collection<Department> getInstitutionDepartments(Integer name);

}
