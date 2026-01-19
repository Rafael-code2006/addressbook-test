package com.example.TestsAddressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {

    private Set<ContactData> delegate;

    public Contacts(Set<ContactData> delegate) {
        this.delegate = new HashSet<ContactData>(delegate);
    }

    public Contacts() {
        this.delegate = new HashSet<>();
    }

    @Override
    protected Set<ContactData> delegate() {
        return delegate;
    }


    public Contacts withAdded(ContactData contactData){
        Contacts contacts = new Contacts(this);
        contacts.add(contactData);
        return contacts;
    }

    public Contacts without(ContactData contactData){
        Contacts contacts = new Contacts(this);
        contacts.remove(contactData);
        return contacts;
    }


}
