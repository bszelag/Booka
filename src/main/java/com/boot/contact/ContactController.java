package com.boot.contact;

import com.boot.contact.model.Contact;
import com.boot.contact.model.ContactId;
import com.boot.user.UserService;
import com.boot.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/contacts")
public class ContactController {

    @Autowired
    public ContactService contactService;

    @Autowired
    public UserService userService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Collection<Contact> getById(@PathVariable Integer id){
        return contactService.getContacts(id);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact){
        if (contact.getContact().getContact1() != null && contact.getContact().getContact2() != null){
            Optional<User> user1 = userService.getById(contact.getContact().getContact1().getId());
            Optional<User> user2 =  userService.getById(contact.getContact().getContact2().getId());
            if (user1.isPresent() && user2.isPresent() && user1.get() != user2.get() ) {
                ContactId contactId = contact.getContact();
                contactId.setContact1(user1.get());
                contactId.setContact2(user2.get());
                contact.setContact(contactId);
                if (user1.get().getId() > user2.get().getId()){
                    User user = contact.getContact().getContact1();
                    contactId.setContact1(contact.getContact().getContact2());
                    contactId.setContact2(user);
                    contact.setContact(contactId);
                }
                return new ResponseEntity<>(contactService.addContact(contact), HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
