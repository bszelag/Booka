package com.boot.user;


import com.boot.address.Address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
@Table(name="Reader")
public class User implements Serializable {

    @Id
    private String login;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;

    @OneToOne
    private Address address;

    public User() {
    }

    public User(String login, String password, String email, Address address) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.address = address;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return login.equals(user.login);
    }

    @Override
    public int hashCode() {
        return login.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "login=" + login +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                '}';
    }
}
