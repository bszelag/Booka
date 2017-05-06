package com.boot.institution.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Institution implements Serializable {

    @Id
    private String name;
    private String url;
    private String contact;
    @Column(nullable = false)
    private Character type;
}
