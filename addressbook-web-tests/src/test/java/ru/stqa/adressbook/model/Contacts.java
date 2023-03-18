package ru.stqa.adressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactDetails> {

    private Set<ContactDetails> delegate;

    public Contacts(Contacts contacts) {
        this.delegate = new HashSet<ContactDetails>(contacts.delegate);
    }

    public Contacts() {
        this.delegate = new HashSet<ContactDetails>();
    }

    @Override
    protected Set<ContactDetails> delegate() {
        return delegate;
    }

    public Contacts withAdded(ContactDetails contact) {
        Contacts contacts = new Contacts(this);
        contacts.add(contact);
        return contacts;
    }

    public Contacts withOut(ContactDetails contact) {
        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;
    }
}
