package com.myproject.codealpha.mapper;

import com.myproject.codealpha.domain.Contact;
import com.myproject.codealpha.dto.ContactDTO;

public class ContactMapper {
    // mapping the Contact entity with ContactDTO
    public static Contact mapToContact(ContactDTO contactDTO){
        if (contactDTO == null) return null;
        return new Contact(contactDTO.getEmail(), contactDTO.getMobile());
    }
    public static ContactDTO mapToContactDTO(Contact contact){
        if(contact == null) return null;
        return new ContactDTO(contact.getEmail(), contact.getMobile());
    }
}
