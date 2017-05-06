package com.boot.user.model;


import com.boot.address.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
@Table(name="Reader")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    private String login;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;

    @OneToOne
    private Address address;
}