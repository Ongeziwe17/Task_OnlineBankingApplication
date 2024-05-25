package com.myproject.codealpha.service;

import com.myproject.codealpha.domain.Contact;
import com.myproject.codealpha.dto.ContactDTO;

import java.util.Set;

public interface ContactService extends IService<ContactDTO, String> {
    Set<ContactDTO> getAll();
}
