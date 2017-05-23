package com.boot.contact.impl;

import com.boot.contact.ContactService;
import com.boot.contact.model.Contact;
import com.boot.contact.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository contactRepository;

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
