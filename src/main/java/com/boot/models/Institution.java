package com.boot.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Institution implements Serializable {

    @Id
    private String name;
    private String url;
    private String contact;
    @Column(nullable = false)
    private Character type;

    public Institution() {
    }

    public Institution(String name, String url, String contact, Character type, Address address) {
        this.name = name;
        this.url = url;
        this.contact = contact;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Institution that = (Institution) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Institution{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", contact='" + contact + '\'' +
                ", type=" + type +
                '}';
    }
}
