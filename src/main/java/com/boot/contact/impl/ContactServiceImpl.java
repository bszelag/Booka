package com.boot.contact.impl;

import com.boot.contact.ContactService;
import com.boot.contact.model.Contact;
import com.boot.contact.model.ContactId;
import com.boot.contact.repository.ContactRepository;
import com.boot.user.model.User;
import com.boot.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Collection<Contact> getContacts(Integer user_id) {
        return contactRepository.getContacts(user_id);
    }

    @Override
    public Contact addContact(Contact contact) {
        if(contactRepository.findOne(contact.getContact())!=null){
            return null;
        }
        return contactRepository.save(contact);
    }
}
