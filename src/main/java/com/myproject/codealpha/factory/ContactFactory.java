package com.myproject.codealpha.factory;

import com.myproject.codealpha.domain.Contact;
import com.myproject.codealpha.util.Helper;

public class ContactFactory {
    public static Contact buildContact(String email, String mobile){
        Long mobilePhone = Long.valueOf(mobile);
        if(!Helper.isValidEmail(email) || Helper.isNullOrEmpty(mobile) && mobile.length() != 10)
            return null;
        return new Contact.Builder().setEmail(email).setMobile(mobile).build();
    }
}
