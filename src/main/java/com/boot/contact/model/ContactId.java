package com.boot.contact.model;

import com.boot.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactId implements Serializable {

    @ManyToOne(optional = false)
    private User contact1;

    @ManyToOne(optional = false)
    private User contact2;
}
