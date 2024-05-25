package com.myproject.codealpha.controller;

import com.myproject.codealpha.domain.AccountHolder;
import com.myproject.codealpha.dto.AccountHolderDTO;
import com.myproject.codealpha.mapper.AccountHolderMapper;
import com.myproject.codealpha.service.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/accountholders")
public class AccountHolderController {
    private final AccountHolderService accountHolderService;
    @Autowired
    public AccountHolderController(AccountHolderService accountHolderService) { this.accountHolderService = accountHolderService; }
    @PostMapping("/create")
    public ResponseEntity<AccountHolderDTO> createAccountHolder(@Validated @RequestBody AccountHolderDTO accountHolderDTO){
        AccountHolderDTO accountHolder = accountHolderService.create(accountHolderDTO);
        return new ResponseEntity<>(accountHolder, HttpStatus.CREATED);
    }
    @GetMapping("/read/{accountHolderId}")
    public ResponseEntity<AccountHolderDTO> readAccountHolder(@PathVariable long accountHolderId){
        AccountHolderDTO readAccountHolder = accountHolderService.read(accountHolderId);
        return ResponseEntity.ok(readAccountHolder);
    }
    @PutMapping("/update/{accountHolderId}")
    public ResponseEntity<AccountHolderDTO> updateAccountHolderDTO(@PathVariable long accountHolderId, @RequestBody AccountHolderDTO accountHolderDTO){
        accountHolderDTO.setAccountHolderId(accountHolderId);
        AccountHolderDTO accountHolderDTOUpdated = accountHolderService.update(accountHolderDTO);
        return ResponseEntity.ok(accountHolderDTOUpdated);
    }
    @GetMapping("/getallaccountholders")
    public ResponseEntity<Set<AccountHolderDTO>> getAllAccountHolders(){
        Set<AccountHolderDTO> accountHolderDTOS = accountHolderService.getAll();
        return new ResponseEntity<>(accountHolderDTOS, HttpStatus.OK);
    }
}
