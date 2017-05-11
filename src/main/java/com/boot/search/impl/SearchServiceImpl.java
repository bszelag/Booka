package com.boot.search.impl;

import com.boot.department.model.Department;
import com.boot.department.repository.DepartmentRepository;
import com.boot.search.LibraryQuery;
import com.boot.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchServiceImpl implements SearchService{

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public String searchQuery(LibraryQuery query) {

        String title = query.getTitle();
        String author = query.getAuthor();
        Integer department = query.getDepartment();

        String targetURL ="https://katalog.biblioteka.wroc.pl/F?func=find-c&ccl_term=";
        if (title != null && author != null)
            targetURL = targetURL +"(WTI=("+title+"?))AND(WAU="+author+"?)";
        else {
            if (title != null){
                targetURL = targetURL +"(WTI=("+title+"?))";
            }
            else{
                targetURL = targetURL +"(WAU="+author+"?)";
            }
        }
        if (department != null) {
            if (departmentRepository.exists(department)) {
                Department department1 = departmentRepository.findOne(department);
                targetURL = targetURL + "&local_base=" + department1.getCode();
            }
        }
        return targetURL;
    }
}
