package com.myproject.codealpha.controller;

import com.myproject.codealpha.domain.AccountHolder;
import com.myproject.codealpha.domain.Contact;
import com.myproject.codealpha.dto.AccountHolderDTO;
import com.myproject.codealpha.dto.ContactDTO;
import com.myproject.codealpha.factory.AccountHolderFactory;
import com.myproject.codealpha.factory.ContactFactory;
import com.myproject.codealpha.mapper.AccountHolderMapper;
import com.myproject.codealpha.mapper.ContactMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountHolderControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private static String Base_URL = "http://localhost:8080/manage/accountholders";
    private static Contact contact1, contact2;
    private static ContactDTO contactDTO1, contactDTO2;
    private static AccountHolder accountHolder1, accountHolder2;
    private static AccountHolderDTO accountHolderDTO1, accountHolderDTO2;
    @BeforeEach
    void setUp() {
        // accountHolder1
        contact1 = ContactFactory.buildContact("luzuko@gmail.com", "0689655400");
        accountHolder1 = AccountHolderFactory.buildAccountHolder(56789, "Luzuko", "Mtolo", contact1);
        accountHolderDTO1 = AccountHolderMapper.mapTOAccountHolderDTO(accountHolder1);
        // accountHolder2
        contact2 = ContactFactory.buildContact("ayanda@gmail.com", "0768622622");
        accountHolder2 = AccountHolderFactory.buildAccountHolder(54321, "Ayanda", "Kenqu", contact2);
        accountHolderDTO2 = AccountHolderMapper.mapTOAccountHolderDTO(accountHolder2);
    }

    @Test
    @Order(1)
    void createAccountHolder() {
        String createURL = Base_URL + "/create";
        System.out.println("URL: " + createURL);
        ResponseEntity<AccountHolderDTO> response1 = restTemplate.postForEntity(createURL, accountHolderDTO1, AccountHolderDTO.class);
        ResponseEntity<AccountHolderDTO> response2 = restTemplate.postForEntity(createURL, accountHolderDTO2, AccountHolderDTO.class);
        assertNotNull(response1);
        System.out.println(response1.getBody());
        System.out.println(response2.getBody());
    }

    @Test
    @Order(2)
    void readAccountHolder() {
        String holderID = String.valueOf(accountHolderDTO2.getAccountHolderId());
        String readAccountHolderURL = Base_URL + "/read/" + holderID;
        System.out.println("URL: " + readAccountHolderURL);
        ResponseEntity<AccountHolderDTO> response = restTemplate.getForEntity(readAccountHolderURL, AccountHolderDTO.class);
        assertNotNull(response);
        System.out.println(response.getBody());
    }

    @Test
    @Order(3)
    void updateAccountHolder() {
        String holderId = String.valueOf(accountHolderDTO1.getAccountHolderId());
        String updateHolderURL = Base_URL + "/update/" + holderId;
        System.out.println("URL: " + updateHolderURL);
        ResponseEntity<AccountHolderDTO> existingResponse = restTemplate.getForEntity(updateHolderURL, AccountHolderDTO.class);
        assertNotNull(existingResponse);
        accountHolderDTO1.setFirstName("Aphelele");
        HttpEntity<AccountHolderDTO> entity = new HttpEntity<>(accountHolderDTO1);
        ResponseEntity<AccountHolderDTO> response = restTemplate.exchange(updateHolderURL, HttpMethod.PUT, entity, AccountHolderDTO.class);
        assertNotNull(response);
        System.out.println(response.getBody());
    }

    @Test
    @Order(4)
    void getAllAccountHolders() {
        String getAllURL = Base_URL + "/getallaccountholders";
        System.out.println("URL: " + getAllURL);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getAllURL, HttpMethod.GET, entity, String.class);
        assertNotNull(response);
        System.out.println(response.getBody());
    }
}