package com.boot.contact;


import com.boot.contact.Contact;
import com.boot.contact.ContactId;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, ContactId> {
}
