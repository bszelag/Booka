package com.boot.institution.impl;

import com.boot.department.model.Department;
import com.boot.department.repository.DepartmentRepository;
import com.boot.institution.InstitutionService;
import com.boot.institution.model.Institution;
import com.boot.institution.repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class InstitutionServiceImpl implements InstitutionService{

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Collection<Institution> getAllInstitutions() {
        return StreamSupport.stream(institutionRepository.findAll().spliterator(), false).
                collect(Collectors.toList());
    }

    @Override
    public Collection<Department> getInstitutionDepartments(Integer name) {
        return departmentRepository.getByInstitution(name);
    }
}
