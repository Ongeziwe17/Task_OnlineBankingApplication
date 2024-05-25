package com.myproject.codealpha.service.impl;

import com.myproject.codealpha.domain.Contact;
import com.myproject.codealpha.dto.ContactDTO;
import com.myproject.codealpha.mapper.ContactMapper;
import com.myproject.codealpha.repository.ContactRepository;
import com.myproject.codealpha.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;
    @Autowired
    public ContactServiceImpl(ContactRepository repository) { this.repository = repository; }

    @Override
    public ContactDTO create(ContactDTO contactDTO) {
        Contact contact = ContactMapper.mapToContact(contactDTO);
        Contact createdContact = repository.save(contact);
        return ContactMapper.mapToContactDTO(createdContact);
    }

    @Override
    public ContactDTO read(String email) {
        Contact contact = repository.findById(email).orElseThrow(()-> new NoSuchElementException("No contact with such email exist"));
        return ContactMapper.mapToContactDTO(contact);
    }

    @Override
    public ContactDTO update(ContactDTO contactDTO) {
        Contact findContact = ContactMapper.mapToContact(contactDTO);
        Contact updatedContact = repository.save(findContact);
        return ContactMapper.mapToContactDTO(updatedContact);
    }

    @Override
    public Set<ContactDTO> getAll() {
        Set<Contact> contacts = new HashSet<>(repository.findAll());
        Set<ContactDTO> contactDTOS = new HashSet<>();
        for(Contact contact : contacts) contactDTOS.add(ContactMapper.mapToContactDTO(contact));
        return contactDTOS;
    }
}
