package com.myproject.codealpha.controller;

import com.myproject.codealpha.dto.AccountDTO;
import com.myproject.codealpha.dto.AccountHolderDTO;
import com.myproject.codealpha.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountServiceImpl accountService;
    @Autowired
    public AccountController(AccountServiceImpl accountService) { this.accountService = accountService; }
    @PostMapping("/create")
    public ResponseEntity<AccountDTO> createAccountDTO(@RequestBody AccountDTO accountDTO){
        AccountDTO createdAccountDTO = accountService.create(accountDTO);
        return new ResponseEntity<>(createdAccountDTO, HttpStatus.CREATED);
    }
    @GetMapping("/read/{accountNumber}")
    public ResponseEntity<AccountDTO> readAccountDTO(@PathVariable long accountNumber){
        AccountDTO readAccountDTO = accountService.read(accountNumber);
        if(readAccountDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(readAccountDTO);
    }
    @PutMapping("/update/{accountNumber}")
    public ResponseEntity<AccountDTO> updateAccountDTO(@PathVariable long accountNumber, @RequestBody AccountDTO accountDTO){
        accountDTO.setAccountNumber(accountNumber);
        AccountDTO accountDTOUpdated = accountService.update(accountDTO);
        return ResponseEntity.ok(accountDTOUpdated);
    }

    // Deposit into account using request parameter
    @PutMapping("/depositIntoAccount/{accountNumber}")
    public ResponseEntity<AccountDTO> depositAccountDTO(@PathVariable long accountNumber, @RequestParam double amount) {
        AccountDTO accountDTO = accountService.deposit(accountNumber, amount);
        return ResponseEntity.ok(accountDTO);
    }
    // Deposit into account using request body
    @PutMapping("/deposit/{accountNumber}")
    public ResponseEntity<AccountDTO> depositIntoAccountDTO(@PathVariable long accountNumber, @RequestBody Map<String, Double> request) {
        if (request == null || !request.containsKey("amount")) return ResponseEntity.badRequest().build();
        double amount = request.get("amount");
        if (amount <= 0) return ResponseEntity.badRequest().build();
        AccountDTO accountDTO = accountService.deposit(accountNumber, amount);
        return ResponseEntity.ok(accountDTO);
    }

    @DeleteMapping("/delete/{accountNumber}")
    public ResponseEntity<AccountDTO> deleteAccountDTO(@PathVariable long accountNumber){
        accountService.delete(accountNumber);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/getallaccounts")
    public ResponseEntity<Set<AccountDTO>> getAllAccounts(){
        Set<AccountDTO> accountDTOS = accountService.getAll();
        return new ResponseEntity<>(accountDTOS, HttpStatus.OK);
    }
}
