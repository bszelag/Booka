package com.boot.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Department implements Serializable{

    @Id
    @SequenceGenerator(name="department_iddepartment_seq",
            sequenceName="department_iddepartment_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="department_iddepartment_seq")
    private String id;
    private String code;

    @ManyToOne
    private Address address;

    @ManyToOne
    private Institution institution;

    public Department() {
    }

    public Department(String id, String code, Address address, Institution institution) {
        this.id = id;
        this.code = code;
        this.address = address;
        this.institution = institution;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        Department department = (Department) o;

        return code.equals(department.code);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ",code='" + code + '\'' +
                ",address='" + address + '\'' +
                ",institution='" + institution +
                '}';
    }
}
