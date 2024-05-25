package com.myproject.codealpha.factory;

import com.myproject.codealpha.domain.Account;
import com.myproject.codealpha.domain.AccountHolder;
import com.myproject.codealpha.domain.Contact;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountFactoryTest {

    @Test
    void buildAccount() {
        Contact contact = ContactFactory.buildContact("john@gmail.com", "0784908331");
        AccountHolder accountHolder = AccountHolderFactory.buildAccountHolder(8236542311L, "Johnston", "Kingsman", contact);
        Account account = AccountFactory.buildAccount(8717653, accountHolder, 60000.00);

        assertNotNull(account);
        System.out.println(account);
    }
}