package com.boot.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Address implements Serializable {

    @Id
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

        if (id != null ? !id.equals(address.id) : address.id != null) return false;
        if (country != null ? !country.equals(address.country) : address.country != null) return false;
        if (province != null ? !province.equals(address.province) : address.province != null) return false;
        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        if (buildNr != null ? !buildNr.equals(address.buildNr) : address.buildNr != null) return false;
        if (apartNr != null ? !apartNr.equals(address.apartNr) : address.apartNr != null) return false;
        return code != null ? code.equals(address.code) : address.code == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (buildNr != null ? buildNr.hashCode() : 0);
        result = 31 * result + (apartNr != null ? apartNr.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
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
