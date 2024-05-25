package com.myproject.codealpha.dto;

import com.myproject.codealpha.domain.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountHolderDTO {
    private long accountHolderId;
    private String firstName;
    private String lastName;
    private Contact contact;
}
