package com.myproject.codealpha.controller;

import com.myproject.codealpha.domain.Contact;
import com.myproject.codealpha.dto.ContactDTO;
import com.myproject.codealpha.mapper.ContactMapper;
import com.myproject.codealpha.service.ContactService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    private final String Base_URL = "http://localhost:8080/manage/contacts";
    private static ContactDTO contactDTO1, contactDTO2;

    @BeforeAll
    public static void setUp() {
        contactDTO1 = new ContactDTO("jay@gmail.com", "0795109767");
        contactDTO2 = new ContactDTO("cindy@gmail.com", "0768818080");
    }

    @Test
    @Order(1)
    void createContact() {
        String createURL = Base_URL + "/create";
        System.out.println("URL: " + createURL);
        ResponseEntity<ContactDTO> response1 = restTemplate.postForEntity(createURL, contactDTO1, ContactDTO.class);
        ResponseEntity<ContactDTO> response2 = restTemplate.postForEntity(createURL, contactDTO2, ContactDTO.class);
        assertNotNull(response1);
        assertNotNull(response2);
        System.out.println(response1.getBody());
        System.out.println(response2.getBody());
    }

    @Test
    @Order(2)
    void readContact() {
        String email = contactDTO1.getEmail();
        String readURL = Base_URL + "/read/" + email;
        System.out.println("URL: " + readURL);
        ResponseEntity<ContactDTO> response = restTemplate.getForEntity(readURL, ContactDTO.class);
        assertNotNull(response);
        System.out.println(response.getBody());
    }

    @Test
    @Order(3)
    void updateContact() {
        String email = contactDTO2.getEmail();
        String updateURL = Base_URL + "/update/" + email;
        System.out.println("URL: " + updateURL);
        ResponseEntity<ContactDTO> existingResponse = restTemplate.getForEntity(updateURL, ContactDTO.class);
        assertNotNull(existingResponse.getBody());
        contactDTO2.setMobile("0827765432");
        HttpEntity<ContactDTO> entity = new HttpEntity<>(contactDTO2);
        ResponseEntity<ContactDTO> response = restTemplate.exchange(updateURL, HttpMethod.PUT, entity, ContactDTO.class);
        assertNotNull(response);
        System.out.println(response.getBody());
    }

    @Test
    @Order(4)
    void getAll() {
        String getAllURL = Base_URL + "/getallcontacts";
        System.out.println("URL: " + getAllURL);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getAllURL, HttpMethod.GET, entity, String.class);
        assertNotNull(response);
        System.out.println(response.getBody());
    }
}
