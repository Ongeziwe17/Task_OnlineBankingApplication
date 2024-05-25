package com.myproject.codealpha.controller;

import com.myproject.codealpha.dto.ContactDTO;
import com.myproject.codealpha.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;
    @Autowired
    public ContactController(ContactService contactService) { this.contactService = contactService; }
    @PostMapping("/create")
    public ResponseEntity<ContactDTO> createContactDTO(@Validated @RequestBody ContactDTO contactDTO){
        ContactDTO createdContactDTO = contactService.create(contactDTO);
        return new ResponseEntity<>(createdContactDTO, HttpStatus.CREATED);
    }

    @GetMapping("/read/{email}")
    public ResponseEntity<ContactDTO> readContactDTO(@PathVariable String email){
        ContactDTO readContactDTO = contactService.read(email);
        return ResponseEntity.ok(readContactDTO);
    }
    @PutMapping("/update/{email}")
    public ResponseEntity<ContactDTO> updateContactDTO(@PathVariable String email, @RequestBody ContactDTO contactDTO){
        ContactDTO existingContactDTO = contactService.read(email);
        existingContactDTO.setMobile(contactDTO.getMobile());
        ContactDTO updatedContactDTO = contactService.update(existingContactDTO);
        return ResponseEntity.ok(updatedContactDTO);
    }
    @GetMapping("/getallcontacts")
    public ResponseEntity<Set<ContactDTO>> retrieveAllContacts(){
        Set<ContactDTO> contactDTOS = contactService.getAll();
        return new ResponseEntity<>(contactDTOS, HttpStatus.OK);
    }
}
