package com.boot.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Contact  implements Serializable {

    @EmbeddedId
    private ContactId contact;

    public Contact() {
    }

    public Contact(ContactId contact) {
        this.contact = contact;
    }

    public ContactId getContact() {
        return contact;
    }

    public void setContact(ContactId contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact1 = (Contact) o;

        return contact != null ? contact.equals(contact1.contact) : contact1.contact == null;
    }

    @Override
    public int hashCode() {
        return contact != null ? contact.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contact=" + contact +
                '}';
    }
}
