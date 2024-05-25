package com.myproject.codealpha.mapper;

import com.myproject.codealpha.domain.Account;
import com.myproject.codealpha.dto.AccountDTO;

public class AccountMapper {
    // mapping Account with AccountDTO and vice versa
    public static AccountDTO mapToAccountDTO(Account account){
        if(account == null) return null;
        return new AccountDTO(account.getAccountNumber(), account.getAccountHolder(), account.getBalance());
    }
    public static Account mapToAccount(AccountDTO accountDTO){
        if(accountDTO == null) return null;
        return new Account(accountDTO.getAccountNumber(), accountDTO.getAccountHolder(), accountDTO.getBalance());
    }
}
