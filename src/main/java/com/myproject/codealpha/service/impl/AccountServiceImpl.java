package com.myproject.codealpha.service.impl;

import com.myproject.codealpha.domain.Account;
import com.myproject.codealpha.dto.AccountDTO;
import com.myproject.codealpha.mapper.AccountMapper;
import com.myproject.codealpha.repository.AccountRepository;
import com.myproject.codealpha.service.AccountService;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;
    public AccountServiceImpl(AccountRepository repository) { this.repository = repository; }

    @Override
    public AccountDTO create(AccountDTO accountDTO) {
        Account account = AccountMapper.mapToAccount(accountDTO);
        Account createdAccount = repository.save(account);
        return AccountMapper.mapToAccountDTO(createdAccount);
    }

    @Override
    public AccountDTO read(Long accountNumber) {
        Account account = repository.findById(accountNumber).orElseThrow(()->new NoSuchElementException("No account with such id exist"));
        return AccountMapper.mapToAccountDTO(account);
    }

    @Override
    public AccountDTO update(AccountDTO accountDTO) {
        long accountNumber = accountDTO.getAccountNumber();
        Account existingAccount = repository.findById(accountNumber).orElseThrow(() -> new NoSuchElementException("No account with such id exist"));
        existingAccount.setAccountHolder(accountDTO.getAccountHolder());
        existingAccount.setBalance(accountDTO.getBalance());

        Account updatedAccount = repository.save(existingAccount);
        return AccountMapper.mapToAccountDTO(updatedAccount);
    }

    @Override
    public AccountDTO deposit(long accountNumber, double amount) {
        Account account = repository.findById(accountNumber).orElseThrow(()->new NoSuchElementException("No account with such id exist"));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account accountBalanceSaved = repository.save(account);
        return AccountMapper.mapToAccountDTO(accountBalanceSaved);
    }

    @Override
    public void delete(long accountNumber) {
        AccountDTO accountToDelete = read(accountNumber);
        Account account = AccountMapper.mapToAccount(accountToDelete);
        repository.delete(account);
    }

    @Override
    public Set<AccountDTO> getAll() {
        Set<Account> accounts = new HashSet<>(repository.findAll());
        Set<AccountDTO> accountDTOS = new HashSet<>();
        for(Account account : accounts) accountDTOS.add(AccountMapper.mapToAccountDTO(account));
        return accountDTOS;
    }
}
