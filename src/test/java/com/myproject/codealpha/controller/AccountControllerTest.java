package com.myproject.codealpha.controller;

import com.myproject.codealpha.domain.Account;
import com.myproject.codealpha.domain.AccountHolder;
import com.myproject.codealpha.domain.Contact;
import com.myproject.codealpha.dto.AccountDTO;
import com.myproject.codealpha.factory.AccountFactory;
import com.myproject.codealpha.factory.AccountHolderFactory;
import com.myproject.codealpha.factory.ContactFactory;
import com.myproject.codealpha.mapper.AccountMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private final String Base_URL = "http://localhost:8080/manage/accounts";

    private static Contact contact1, contact2, contact3;
    private static AccountHolder accountHolder1, accountHolder2, accountHolder3;
    private static Account account1, account2, account3;
    private static AccountDTO accountDTO1, accountDTO2, accountDTO3;

    @BeforeEach
    void setUp() {
        // account 1
        contact1 = ContactFactory.buildContact("john@gmail.com", "0796548765");
        accountHolder1 = AccountHolderFactory.buildAccountHolder(208176096088L, "Johnston", "Smith", contact1);
        account1 = AccountFactory.buildAccount(65483, accountHolder1, 50000.00);
        accountDTO1 = AccountMapper.mapToAccountDTO(account1);
        // account 2
        contact2 = ContactFactory.buildContact("jaz123@gmail.com", "08676545673");
        accountHolder2 = AccountHolderFactory.buildAccountHolder(11245876384L, "Jazmine", "Afonso", contact2);
        account2 = AccountFactory.buildAccount(98567, accountHolder2, 45000.00);
        accountDTO2 = AccountMapper.mapToAccountDTO(account2);
        // account 3
        contact3 = ContactFactory.buildContact("kimk@gmail.com", "0876543210");
        accountHolder3 = AccountHolderFactory.buildAccountHolder(11245876385L, "Kim", "Brown", contact3);
        account3 = AccountFactory.buildAccount(98568, accountHolder3, 50000.00);
        accountDTO3 = AccountMapper.mapToAccountDTO(account3);
    }

    @Test
    @Order(1)
    void createAccountDTO() {
        String createURL = Base_URL + "/create";
        System.out.println("URL: " + createURL);
        ResponseEntity<AccountDTO> response1 = restTemplate.postForEntity(createURL, accountDTO1, AccountDTO.class);
        ResponseEntity<AccountDTO> response2 = restTemplate.postForEntity(createURL, accountDTO2, AccountDTO.class);
        ResponseEntity<AccountDTO> response3 = restTemplate.postForEntity(createURL, accountDTO3, AccountDTO.class);
        assert response1 != null && response2 != null && response3 != null;
        System.out.println(response1.getBody());
        System.out.println(response2.getBody());
        System.out.println(response3.getBody());
    }

    @Test
    @Order(2)
    void readAccountDTO() {
        String accountNumber = String.valueOf(accountDTO2.getAccountNumber());
        String readURL = Base_URL + "/read/" + accountNumber;
        System.out.println("URL: " + readURL);
        ResponseEntity<AccountDTO> response = restTemplate.getForEntity(readURL, AccountDTO.class);
        assertNotNull(response);
        System.out.println(response.getBody());
    }

    @Test
    @Order(3)
    void updateAccountDTO() {
        String accountNumberToBeUpdated = String.valueOf(accountDTO1.getAccountNumber());
        String updateURL = Base_URL + "/update/" + accountNumberToBeUpdated;
        System.out.println("URL: " + updateURL);
        ResponseEntity<AccountDTO> existingResponse = restTemplate.getForEntity(updateURL, AccountDTO.class);
        assertNotNull(existingResponse);
        AccountHolder updatedAccountHolder1 = new AccountHolder.Builder().copy(accountHolder1).setFirstName("Kingston John").build();
        accountDTO1.setAccountHolder(updatedAccountHolder1);
        HttpEntity<AccountDTO> entity = new HttpEntity<>(accountDTO1);
        ResponseEntity<AccountDTO> response = restTemplate.exchange(updateURL, HttpMethod.PUT, entity, AccountDTO.class);
        assertNotNull(response);
        System.out.println(response.getBody());
    }

    @Test
    @Order(4)
    void depositIntoAccountDTO() {
        long depositToAccountNumber = accountDTO1.getAccountNumber();
        double initialBalance = accountDTO1.getBalance();
        double depositAmount = 15000.00;
        Map<String, Double> depositRequest = new HashMap<>();
        depositRequest.put("amount", depositAmount);
        String depositURL = Base_URL + "/" + depositToAccountNumber + "/deposit";
        System.out.println("URL: " + depositURL);
        HttpEntity<Map<String, Double>> entity = new HttpEntity<>(depositRequest);
        ResponseEntity<AccountDTO> response = restTemplate.exchange(depositURL, HttpMethod.PUT, entity, AccountDTO.class);
        assertNotNull(response);
        if(response.getBody() != null) assertEquals(initialBalance + depositAmount, response.getBody().getBalance());
        System.out.println(response.getBody());
    }

    @Test
    @Order(5)
    void withdrawFromAccountDTO() {
        long withdrawFromAccountNumber = accountDTO2.getAccountNumber();
        double initialBalance = accountDTO2.getBalance();
        double withdrawAmount = 5000.00;
        Map<String, Double> withdrawRequest = new HashMap<>();
        withdrawRequest.put("amount", withdrawAmount);
        String withdrawURL = Base_URL + "/" + withdrawFromAccountNumber + "/withdraw";
        System.out.println("URL: " + withdrawURL);
        HttpEntity<Map<String, Double>> entity = new HttpEntity<>(withdrawRequest);
        ResponseEntity<AccountDTO> response = restTemplate.exchange(withdrawURL, HttpMethod.PUT, entity, AccountDTO.class);
        assertNotNull(response);
        if(response.getBody() != null) assertEquals(initialBalance - withdrawAmount, response.getBody().getBalance());
        System.out.println(response.getBody());
    }
    @Test
    @Order(7)
    void deleteAccountDTO() {
        String accountNumberToBeDeleted = String.valueOf(accountDTO3.getAccountNumber());
        String deleteURL = Base_URL + "/delete/" + accountNumberToBeDeleted;
        System.out.println("URL: " + deleteURL);
        restTemplate.delete(deleteURL);
        System.out.println("Removed successfully");
    }

    @Test
    @Order(6)
    void getAllAccounts() {
        String getAllURL = Base_URL + "/getallaccounts";
        System.out.println("URL: " + getAllURL);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getAllURL, HttpMethod.GET, entity, String.class);
        assertNotNull(response);
        System.out.println(response);
    }
}