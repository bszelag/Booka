package com.boot.repositories;


import com.boot.models.Contact;
import com.boot.models.ContactId;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, ContactId> {
}
