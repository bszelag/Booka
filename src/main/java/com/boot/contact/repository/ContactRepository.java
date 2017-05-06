package com.boot.contact.repository;


import com.boot.contact.model.Contact;
import com.boot.contact.model.ContactId;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, ContactId> {
}
