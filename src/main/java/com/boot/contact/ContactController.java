package com.boot.contact;

import com.boot.authorizedViewers.AuthorizedViewersService;
import com.boot.authorizedViewers.model.AuthorizedViewers;
import com.boot.authorizedViewers.model.AuthorizedViewersId;
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
@RequestMapping(value = "/api/v1/friends")
public class ContactController {

    @Autowired
    public ContactService contactService;

    @Autowired
    public AuthorizedViewersService authorizedViewersService;

    @Autowired
    public UserService userService;


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Collection<Contact> getContactsId(@PathVariable Integer id){
        return contactService.getContacts(id);
    }

    @RequestMapping(method = RequestMethod.POST)
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


    @RequestMapping(value = "authorized-viewers/{id}", method = RequestMethod.GET)
    public Collection<AuthorizedViewers> getAuthorizedViewersId(@PathVariable Integer id){
        return authorizedViewersService.getAuthorizedViewers(id);
    }

    @RequestMapping(value = "authorized-viewers", method = RequestMethod.POST)
    public ResponseEntity<AuthorizedViewers> addAuthorizedViewersId(@RequestBody AuthorizedViewers authorizedViewers){
        if (authorizedViewers.getAuthorizedViewer().getOwner() != null && authorizedViewers.getAuthorizedViewer().getAuthorizedViewer() != null){
            Optional<User> user1 = userService.getById(authorizedViewers.getAuthorizedViewer().getOwner().getId());
            Optional<User> user2 =  userService.getById(authorizedViewers.getAuthorizedViewer().getAuthorizedViewer().getId());
            if (user1.isPresent() && user2.isPresent() && user1.get() != user2.get() ) {
                AuthorizedViewersId authorizedViewersId = authorizedViewers.getAuthorizedViewer();
                authorizedViewersId.setOwner(user1.get());
                authorizedViewersId.setAuthorizedViewer(user2.get());
                authorizedViewers.setAuthorizedViewer(authorizedViewersId);
                return new ResponseEntity<>(authorizedViewersService.addAuthorizedViewer(authorizedViewers), HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
