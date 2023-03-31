package ru.stqa.adressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.*;

public class Contacts extends ForwardingSet<ContactDetails> {

    private Set<ContactDetails> delegate;

    public Contacts(Contacts contacts) {

        this.delegate = new HashSet<ContactDetails>(contacts.delegate);
    }

    public Contacts() {
        this.delegate = new HashSet<ContactDetails>();
    }

    public Contacts(Collection<ContactDetails> contacts) {
        this.delegate = new HashSet<ContactDetails>(contacts);
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
