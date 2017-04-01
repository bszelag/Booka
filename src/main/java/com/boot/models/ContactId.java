package com.boot.models;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class ContactId implements Serializable {

    @ManyToOne(optional = false)
    private User contact1;

    @ManyToOne(optional = false)
    private User contact2;

    public ContactId() {
    }

    public ContactId(User contact1, User contact2) {
        this.contact1 = contact1;
        this.contact2 = contact2;
    }

    public User getContact1() {
        return contact1;
    }

    public void setContact1(User contact1) {
        this.contact1 = contact1;
    }

    public User getContact2() {
        return contact2;
    }

    public void setContact2(User contact2) {
        this.contact2 = contact2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactId contactId = (ContactId) o;

        if (contact1 != null ? !contact1.equals(contactId.contact1) : contactId.contact1 != null) return false;
        return contact2 != null ? contact2.equals(contactId.contact2) : contactId.contact2 == null;
    }

    @Override
    public int hashCode() {
        int result = contact1 != null ? contact1.hashCode() : 0;
        result = 31 * result + (contact2 != null ? contact2.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactId{" +
                "contact1=" + contact1 +
                ", contact2=" + contact2 +
                '}';
    }
}
