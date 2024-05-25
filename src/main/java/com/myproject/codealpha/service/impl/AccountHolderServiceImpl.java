package com.myproject.codealpha.service.impl;

import com.myproject.codealpha.domain.AccountHolder;
import com.myproject.codealpha.repository.AccountHolderRepository;
import com.myproject.codealpha.service.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
@Service
public class AccountHolderImpl implements AccountHolderService {

    private final AccountHolderRepository repository;
    @Autowired
    public AccountHolderImpl(AccountHolderRepository repository) { this.repository = repository; }

    @Override
    public AccountHolder create(AccountHolder accountHolder) { return repository.save(accountHolder); }
    @Override
    public AccountHolder read(Long accountHolderId) { return repository.findById(accountHolderId).orElseThrow(() -> new NoSuchElementException("No Account Holder with specified Id exist")); }
    @Override
    public AccountHolder update(AccountHolder accountHolder) { return repository.save(accountHolder); }
    @Override
    public Set<AccountHolder> getAll() { return new HashSet<>(repository.findAll()); }
}
