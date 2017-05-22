package com.boot.department.repository;

import com.boot.department.model.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {

    @Query("Select d" +
            " FROM Department d" +
            " WHERE d.code = :code")
    Department getByCode(@Param("code") String code);

}
