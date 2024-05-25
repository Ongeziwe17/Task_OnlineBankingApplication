package com.myproject.codealpha.service.impl;

import com.myproject.codealpha.domain.Account;
import com.myproject.codealpha.repository.AccountRepository;
import com.myproject.codealpha.service.AccountService;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
@Service
public class AccountImpl implements AccountService {
    private final AccountRepository repository;
    public AccountImpl(AccountRepository repository) { this.repository = repository; }

    @Override
    public Account create(Account account) { return repository.save(account); }
    @Override
    public Account read(Long accountId) { return repository.findById(accountId).orElseThrow(()-> new NoSuchElementException("No such account with specified Id exist")); }
    @Override
    public Account update(Account account) { return repository.save(account); }
    @Override
    public Account delete(long accountId) {
        Account accountToBeDeleted = read(accountId);
        if(accountToBeDeleted != null) repository.delete(accountToBeDeleted);
        return accountToBeDeleted;
    }
    @Override
    public Set<Account> getAll() { return new HashSet<>(repository.findAll()); }
}
