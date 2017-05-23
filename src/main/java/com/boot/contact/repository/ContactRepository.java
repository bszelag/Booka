package com.boot.contact.repository;


import com.boot.contact.model.Contact;
import com.boot.contact.model.ContactId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface ContactRepository extends CrudRepository<Contact, ContactId> {

    @Query("Select c" +
            " FROM Contact c" +
            " JOIN c.contact ci" +
            " JOIN ci.contact1 u1" +
            " JOIN ci.contact2 u2" +
            " WHERE u1.id = :user_id" +
            " OR u2.id = :user_id")
    Collection<Contact> getContacts(@Param("user_id") Integer user_id);

}
