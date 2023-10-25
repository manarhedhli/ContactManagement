package com.example.contactmanagement;

public class Contact {
    String fname, lname, phone;
    public Contact() {

    }
    public Contact(String fname, String lname, String phone) {
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "Contact{" +
                "first name='" + fname + '\'' +
                ", last name='" + lname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
