package com.boot.contact;

import com.boot.contact.model.Contact;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface ContactService {

    public Collection<Contact> getContacts(Integer user_id);

    public Contact addContact(Contact contact);

}
