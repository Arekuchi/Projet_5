package com.openclassrooms.safetyAlerts.it_integration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyAlerts.model.Person;
import com.openclassrooms.safetyAlerts.repository.DataRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonIT {

    // On utilise ses objets pour envoyer des requêtes à notre serveur SpringBoot

    @Autowired
    TestRestTemplate clientREST;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    DataRepository dataRepository;

    @BeforeEach
    void initDb() throws Exception {
        dataRepository.init();
        // modification en base de donnée non autorisé, on set le commit sur FALSE
        dataRepository.setCommit(false);
    }





    @Test
    public void getCommunityMail() throws Exception {

        // etape 1 : on envoie une requete get avec en param Culver
        ResponseEntity<String> response = clientREST.getForEntity("/communityEmail?city=Culver", String.class);

        // etape 2 : on vérif le status de réponse == 200
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // etape 3 : on renvoi le json node qu'on attends
        JsonNode expectedJson = objectMapper.readTree(ClassLoader.getSystemResourceAsStream("culverCommunityEmail.json"));

        // etape 4 : on vérif le contenu du json de retour
        assertEquals(expectedJson,objectMapper.readTree(response.getBody()));

    }

    @Test
    public void getChildAlert() throws  Exception {

        // etape 1
        ResponseEntity<String> response = clientREST.getForEntity("/childAlert?address=1509 Culver St", String.class);

        // etape 2
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Etape 3
        JsonNode expectedJson = objectMapper.readTree(ClassLoader.getSystemResourceAsStream("culverChildAlert.json"));

        // etape4
        assertEquals(expectedJson,objectMapper.readTree(response.getBody()));
    }

    @Test
    public void getFire() throws Exception {

        // etape 1
        ResponseEntity<String> response = clientREST.getForEntity("/fire?address=1509 Culver St", String.class);

        // etape 2
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Etape 3
        JsonNode expectedJson = objectMapper.readTree(ClassLoader.getSystemResourceAsStream("culverFire.json"));

        // etape4
        assertEquals(expectedJson,objectMapper.readTree(response.getBody()));
    }

    @Test
    public void getFirestationDTO() throws Exception {

        // etape 1
        ResponseEntity<String> response = clientREST.getForEntity("/firestation?stationNumber=3", String.class);

        // etape 2
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Etape 3
        JsonNode expectedJson = objectMapper.readTree(ClassLoader.getSystemResourceAsStream("firestationNumber3.json"));

        // etape4
        assertEquals(expectedJson,objectMapper.readTree(response.getBody()));
    }

    @Test
    public void getFlood() throws Exception {

        // etape 1
        ResponseEntity<String> response = clientREST.getForEntity("/flood/stations?stations=3", String.class);

        // etape 2
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Etape 3
        JsonNode expectedJson = objectMapper.readTree(ClassLoader.getSystemResourceAsStream("floodNumber3.json"));

        // etape4
        assertEquals(expectedJson,objectMapper.readTree(response.getBody()));
    }

    @Test
    public void getPersonInfo() throws Exception {

        // etape 1
        ResponseEntity<String> response = clientREST.getForEntity("/personInfo?lastName=boyd&firstName=John", String.class);

        // etape 2
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Etape 3
        JsonNode expectedJson = objectMapper.readTree(ClassLoader.getSystemResourceAsStream("personInfoJohnBoyd.json"));

        // etape4
        assertEquals(expectedJson,objectMapper.readTree(response.getBody()));
    }

    @Test
    public void getPhoneAlert() throws Exception {

        // etape 1
        ResponseEntity<String> response = clientREST.getForEntity("/phoneAlertList?firestation=3", String.class);

        // etape 2
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Etape 3
        JsonNode expectedJson = objectMapper.readTree(ClassLoader.getSystemResourceAsStream("phoneAlertFirestation3.json"));

        // etape4
        assertEquals(expectedJson,objectMapper.readTree(response.getBody()));
    }

    // tests postValidPersonTest (OK) - postInvalidPersonTest (Invalid argument) - (Dataalready exist)
    @Test
    public void postValidPersonTest() {
        /* throws IOException {
            // etape 1
            String createPersonURL = "http://localhost:8080/person";
            String updatePersonURL = "http://localhost:8080/person";
            String deletePersonURL = "http://localhost:8080/person";

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject personJsonObject = new JSONObject();
            personJsonObject.put("firstName", "Barack");
            personJsonObject.put("lastName", "Obama");


            HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
            String personResultAsJsonString = restTemplate.postForObject(createPersonURL, request, String.class);
            JsonNode root = objectMapper.readTree(personResultAsJsonString);

            assertNotNull(personResultAsJsonString);
            assertNotNull(root);
            */
        // etape 2

        // etape 3

        // etape 4
    }

    @Test
    public void postInvalidPersonTest() throws Exception {

        // etape 1

        // etape 2

        // etape 3

        // etape 4
    }

    @Test
    public void postAlreadyExistingPersonTest() throws Exception {

        // etape 1

        // etape 2

        // etape 3

        // etape 4
    }

    @Test
    public void putValidPersonTest() throws Exception {

        // etape 1

        // etape 2

        // etape 3

        // etape 4
    }

    @Test
    public void putInvalidPersonTest() throws Exception {

        // etape 1

        // etape 2

        // etape 3

        // etape 4
    }

    @Test
    public void putDataNotFoundPersonTest() throws Exception {

        // etape 1

        // etape 2

        // etape 3

        // etape 4
    }

    @Test
    public void deleteValidPersonTest() throws Exception {

        // etape 1

        // etape 2

        // etape 3

        // etape 4
    }

    @Test
    public void deleteInvalidPersonTest() throws Exception {

        // etape 1

        // etape 2

        // etape 3

        // etape 4
    }

    @Test
    public void deleteInvalidBodyJsonPersonTest() throws Exception {

        // etape 1

        // etape 2

        // etape 3

        // etape 4
    }

}
