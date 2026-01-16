package com.example.TestsAddressbook.model;

import java.util.Objects;

public class ContactData {

    private int id;
    private String firstname;
    private final String lastname;
    private final String email;
    private final String group;

    public ContactData(int id, String firstname, String lastname, String email, String group) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.group = group;
    }

    public ContactData(String firstname, String lastname, String email, String group) {
        this.id = Integer.MAX_VALUE;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.group = group;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", group='" + group + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(firstname, that.firstname);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(firstname);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String s) {
        this.firstname = s;
    }
}
