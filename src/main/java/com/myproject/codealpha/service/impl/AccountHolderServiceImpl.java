package com.myproject.codealpha.service.impl;

import com.myproject.codealpha.domain.AccountHolder;
import com.myproject.codealpha.dto.AccountHolderDTO;
import com.myproject.codealpha.mapper.AccountHolderMapper;
import com.myproject.codealpha.repository.AccountHolderRepository;
import com.myproject.codealpha.service.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
@Service
public class AccountHolderServiceImpl implements AccountHolderService {

    private final AccountHolderRepository repository;
    @Autowired
    public AccountHolderServiceImpl(AccountHolderRepository repository) { this.repository = repository; }

    @Override
    public AccountHolderDTO create(AccountHolderDTO accountHolderDTO) {
        AccountHolder accountHolder = AccountHolderMapper.mapToAccountHolder(accountHolderDTO);
        AccountHolder createdAccountHolder = repository.save(accountHolder);
        return AccountHolderMapper.mapTOAccountHolderDTO(createdAccountHolder);
    }

    @Override
    public AccountHolderDTO read(Long accountHolderId) {
        AccountHolder accountHolder = repository.findById(accountHolderId).orElseThrow(()-> new NoSuchElementException("No account holder with such Id exist"));
        return AccountHolderMapper.mapTOAccountHolderDTO(accountHolder);
    }

    @Override
    public AccountHolderDTO update(AccountHolderDTO accountHolderDTO) {
        long accountHolderId = accountHolderDTO.getAccountHolderId();
        AccountHolder existingAccountHolder = repository.findById(accountHolderId).orElseThrow(()->new NoSuchElementException("No account holder with such id exist"));

        existingAccountHolder.setAccountHolderId(accountHolderDTO.getAccountHolderId());
        existingAccountHolder.setFirstName(accountHolderDTO.getFirstName());
        existingAccountHolder.setLastName(accountHolderDTO.getLastName());
        existingAccountHolder.setContact(accountHolderDTO.getContact());

        AccountHolder updatedAccountHolder = repository.save(existingAccountHolder);
        return AccountHolderMapper.mapTOAccountHolderDTO(updatedAccountHolder);
    }
    @Override
    public Set<AccountHolderDTO> getAll() {
        Set<AccountHolder> accountHolders = new HashSet<>(repository.findAll());
        Set<AccountHolderDTO> accountHolderDTOS = new HashSet<>();
        for(AccountHolder accountHolder : accountHolders) accountHolderDTOS.add(AccountHolderMapper.mapTOAccountHolderDTO(accountHolder));
        return accountHolderDTOS;
    }
}
