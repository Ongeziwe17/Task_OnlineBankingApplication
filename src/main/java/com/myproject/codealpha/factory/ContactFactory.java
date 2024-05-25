package com.myproject.codealpha.factory;

import com.myproject.codealpha.domain.Contact;
import com.myproject.codealpha.util.Helper;

public class ContactFactory {
    public static Contact buildContact(String email, String mobile){
        Long mobilePhone = Long.valueOf(mobile);
        if(!Helper.isValidEmail(email) || Helper.isNullOrEmpty(mobile))
            return null;
        if(mobile.length() != 10)
            throw new IllegalArgumentException("Mobile number must be exactly 10 digits long");

        return new Contact.Builder().setEmail(email).setMobile(mobile).build();
    }
}
