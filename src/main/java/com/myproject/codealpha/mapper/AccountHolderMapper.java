package com.myproject.codealpha.mapper;

import com.myproject.codealpha.domain.AccountHolder;
import com.myproject.codealpha.dto.AccountHolderDTO;

public class AccountHolderMapper {
    // mapping the AccountHolder entity with AccountHolderDTO and vice versa
    public static AccountHolderDTO mapTOAccountHolderDTO(AccountHolder accountHolder){
        if(accountHolder == null) return null;
        return new AccountHolderDTO(accountHolder.getAccountHolderId(), accountHolder.getFirstName(), accountHolder.getLastName(), accountHolder.getContact());
    }
    public static AccountHolder mapToAccountHolder(AccountHolderDTO accountHolderDTO){
        if(accountHolderDTO == null) return null;
        return new AccountHolder(accountHolderDTO.getAccountHolderId(), accountHolderDTO.getFirstName(), accountHolderDTO.getLastName(), accountHolderDTO.getContact());
    }
}
