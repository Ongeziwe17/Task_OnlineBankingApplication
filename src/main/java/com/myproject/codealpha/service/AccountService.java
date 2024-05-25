package com.myproject.codealpha.service;

import com.myproject.codealpha.dto.AccountDTO;
import com.myproject.codealpha.dto.AccountHolderDTO;

import java.util.Set;

public interface AccountService extends IService<AccountDTO, Long>{

    AccountDTO deposit(long accountNumber, double amount);
    AccountDTO withdraw(long accountNumber, double amount);
    void delete(long accountId);
    Set<AccountDTO> getAll();
}
