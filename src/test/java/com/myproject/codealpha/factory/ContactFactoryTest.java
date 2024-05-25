package com.myproject.codealpha.factory;

import com.myproject.codealpha.domain.Contact;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactFactoryTest {
    @Test // pass
    @Order(1)
    void buildContactWithValidEmailAndValidMobile() {
        Contact contact = ContactFactory.buildContact("jen123@gmail.com", "0795109776");
        assertNotNull(contact);
        System.out.println(contact);
    }
    @Test // fail
    @Order(2)
    void buildContactWithInvalidEmailAndInvalidMobile() {
        Contact contact = ContactFactory.buildContact("..123$gmail.me", "09776");
        assertNotNull(contact);
        System.out.println(contact);
    }
    @Test // fail
    @Order(3)
    void buildContactWithValidEmailAndInvalidMobile() {
        Contact contact = ContactFactory.buildContact("john@gmail.co.uk", "873776");
        assertNotNull(contact);
        System.out.println(contact);
    }
    @Test // fail
    @Order(4)
    void buildContactWithInvalidEmailAndValidMobile() {
        Contact contact = ContactFactory.buildContact("..123$gmail.me", "0795109776");
        assertNotNull(contact);
        System.out.println(contact);
    }
}