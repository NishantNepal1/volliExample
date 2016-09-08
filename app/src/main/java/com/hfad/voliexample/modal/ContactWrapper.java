package com.hfad.voliexample.modal;

/**
 * Created by user on 9/6/2016.
 */

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
public class ContactWrapper {
    @SerializedName("contacts")
    ArrayList<Contact> contactsArrayList;

    public ArrayList<Contact> getContactsArrayList() {
        return contactsArrayList;
    }

    public void setContactsArrayList(ArrayList<Contact> contactsArrayList) {
        this.contactsArrayList = contactsArrayList;
    }
}
