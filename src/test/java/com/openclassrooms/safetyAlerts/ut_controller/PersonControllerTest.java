package com.openclassrooms.safetyAlerts.ut_controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.openclassrooms.safetyAlerts.exceptions.DataAlreadyExistException;
import com.openclassrooms.safetyAlerts.exceptions.DataNotFoundException;
import com.openclassrooms.safetyAlerts.service.CommunityEmail;
import com.openclassrooms.safetyAlerts.serviceDAO.PersoneServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@SpringBootTest

@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CommunityEmail communityEmail;

    @MockBean
    PersoneServiceImpl personeService;


    // une personne
    String firstNameTest = "Alexis";
    String lastNameTest = "Berthelot";
    String addressTest = "25 rue blabla";
    String cityTest = "Atlanta";
    String zipTest = "59875";
    String phoneTest = "555-555-555";
    String emailTest = "abc@mail";

    // Test Create
    @Test
    void createPersonValid() throws Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonPerson = obm.createObjectNode();
        jsonPerson.set("firstName", TextNode.valueOf(firstNameTest));
        jsonPerson.set("lastName", TextNode.valueOf(lastNameTest));

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/person").contentType(MediaType.APPLICATION_JSON).content(jsonPerson.toString())).andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    void createPersonInvalid() throws Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonPerson = obm.createObjectNode();
        jsonPerson.set("firstName", TextNode.valueOf(firstNameTest));
        jsonPerson.set("lastName", TextNode.valueOf(""));
        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/person").contentType(MediaType.APPLICATION_JSON).content(jsonPerson.toString())).andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    void createPersonWhenPersonAlreadyExist() throws Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonPerson = obm.createObjectNode();
        jsonPerson.set("firstName", TextNode.valueOf(firstNameTest));
        jsonPerson.set("lastName", TextNode.valueOf(lastNameTest));

        //when
        Mockito.doThrow(DataAlreadyExistException.class).when(personeService).createPerson(Mockito.any());

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/person").contentType(MediaType.APPLICATION_JSON).content(jsonPerson.toString())).andExpect(MockMvcResultMatchers.status().isConflict());

    }


    // test update

    @Test
    void updatePersonValid() throws Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonPerson = obm.createObjectNode();
        jsonPerson.set("firstName", TextNode.valueOf(firstNameTest));
        jsonPerson.set("lastName", TextNode.valueOf(lastNameTest));

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/person").contentType(MediaType.APPLICATION_JSON).content(jsonPerson.toString())).andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    void updatePersonInvalid() throws  Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonPerson = obm.createObjectNode();
        jsonPerson.set("firstName", TextNode.valueOf(firstNameTest));
        jsonPerson.set("lastName", TextNode.valueOf(""));
        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/person").contentType(MediaType.APPLICATION_JSON).content(jsonPerson.toString())).andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    void updatePersonWhenPersonDoesntExist() throws Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonPerson = obm.createObjectNode();
        jsonPerson.set("firstName", TextNode.valueOf(firstNameTest));
        jsonPerson.set("lastName", TextNode.valueOf(lastNameTest));

        //when
        Mockito.doThrow(DataNotFoundException.class).when(personeService).updatePerson(Mockito.any());

        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/person").contentType(MediaType.APPLICATION_JSON).content(jsonPerson.toString())).andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    // test delete

    @Test
    void deletePersonValid() throws Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonPerson = obm.createObjectNode();
        jsonPerson.set("firstName", TextNode.valueOf(firstNameTest));
        jsonPerson.set("lastName", TextNode.valueOf(lastNameTest));

        //when


        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/person").contentType(MediaType.APPLICATION_JSON).content(jsonPerson.toString())).andExpect(MockMvcResultMatchers.status().isResetContent());

    }

    @Test
    void deletePersonInvalid() throws Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonPerson = obm.createObjectNode();
        jsonPerson.set("firstName", TextNode.valueOf(firstNameTest));
        jsonPerson.set("lastName", TextNode.valueOf(""));

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/person").contentType(MediaType.APPLICATION_JSON).content(jsonPerson.toString())).andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    void deletePersonWhenPersonDoesntExist() throws Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonPerson = obm.createObjectNode();
        jsonPerson.set("firstName", TextNode.valueOf(firstNameTest));
        jsonPerson.set("lastName", TextNode.valueOf(lastNameTest));

        //when
        Mockito.doThrow(DataNotFoundException.class).when(personeService).deletePerson(Mockito.any());

        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/person").contentType(MediaType.APPLICATION_JSON).content(jsonPerson.toString())).andExpect(MockMvcResultMatchers.status().isNotFound());

    }


    @Test
    void getCommunityEmail() throws Exception {
        // on test getCommunityEmail
        // etape 1 : on mock le comportement de CommunityEmail (MockBean) pour renvoyer des valeurs d'email.
        Mockito.when(communityEmail.getCommunityEmail("Culver")).thenReturn(Arrays.asList("a@a", "b@b", "c@c"));
        // etape 2 : on envoie une requête GET avec en param la ville Culver et on vérief le Status de la réponse (voulu : 200)
        mockMvc.perform(MockMvcRequestBuilders.get("/communityEmail").param("city", "Culver")).andExpect(MockMvcResultMatchers.status().isOk());
        // etape 3 : on vérifie que le service a bien été appelé avec les bons params
        Mockito.verify(communityEmail, Mockito.times(1)).getCommunityEmail("Culver");

    }

}
