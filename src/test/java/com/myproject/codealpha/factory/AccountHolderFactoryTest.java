package com.myproject.codealpha.factory;

import com.myproject.codealpha.domain.AccountHolder;
import com.myproject.codealpha.domain.Contact;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountHolderFactoryTest {

    @Test
    @Order(1)
    void buildAccountHolder() {
        Contact contact = ContactFactory.buildContact("john@gmail.com", "0784908331");
        AccountHolder accountHolder = AccountHolderFactory.buildAccountHolder(8236542311L, "Johnston", "Kingsman", contact);

        assertNotNull(accountHolder);
        System.out.println(accountHolder);
    }
}