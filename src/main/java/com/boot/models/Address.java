package com.boot.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String country;
    private String province;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String buildNr;
    private String apartNr;
    private String code;

    public Address() {
    }

    public Address(Integer id, String country, String province, String city,
                   String street, String buildNr, String apartNr, String code) {
        this.id = id;
        this.country = country;
        this.province = province;
        this.city = city;
        this.street = street;
        this.buildNr = buildNr;
        this.apartNr = apartNr;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildNr() {
        return buildNr;
    }

    public void setBuildNr(String buildNr) {
        this.buildNr = buildNr;
    }

    public String getApartNr() {
        return apartNr;
    }

    public void setApartNr(String apartNr) {
        this.apartNr = apartNr;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return id.equals(address.id);
    }

    @Override
    public int hashCode() {
        return  id;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", buildNr='" + buildNr + '\'' +
                ", apartNr='" + apartNr + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
