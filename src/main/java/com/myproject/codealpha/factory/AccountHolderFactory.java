package com.myproject.codealpha.factory;

import com.myproject.codealpha.domain.AccountHolder;
import com.myproject.codealpha.domain.Contact;
import com.myproject.codealpha.util.Helper;

public class AccountHolderFactory {
    public static AccountHolder buildAccountHolder(long accountHolderId, String firstName, String lastName, Contact contact){
        if(Helper.isNullOrEmpty(accountHolderId) || Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName) || Helper.isNullOrEmpty(contact))
            return null;
        return new AccountHolder.Builder().setAccountHolderId(accountHolderId).setFirstName(firstName).setLastName(lastName).setContact(contact).build();
    }
}
