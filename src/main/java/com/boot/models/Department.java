package com.boot.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Department implements Serializable{

    @Id
    private String code;
    private String localization;

    @ManyToOne
    private Institution institution;

    public Department() {
    }

    public Department(String code, String localization, Institution institution) {
        this.code = code;
        this.localization = localization;
        this.institution = institution;
    }

    public String getId() {
        return code;
    }

    public void setId(String code) {
        this.code = code;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        Department department = (Department) o;

        return code.equals(department.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public String toString() {
        return "Department{" +
                "code='" + code + '\'' +
                "localization='" + localization + '\'' +
                ",institution='" + institution +
                '}';
    }
}
