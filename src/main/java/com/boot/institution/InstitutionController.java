package com.boot.institution;

import com.boot.department.model.Department;
import com.boot.institution.model.Institution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/v1/institutions")
public class InstitutionController {

    @Autowired
    private InstitutionService institutionService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Collection<Institution> getAllInstitutions(){
        return institutionService.getAllInstitutions();
    }

    @RequestMapping(value = "{institution_id}", method = RequestMethod.GET)
    public Collection<Department> getInstitutionDepartments(@PathVariable String institution_id){
        return institutionService.getInstitutionDepartments(institution_id);
    }
}
