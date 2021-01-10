package com.openclassrooms.safetyAlerts.ut_controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.openclassrooms.safetyAlerts.exceptions.DataAlreadyExistException;
import com.openclassrooms.safetyAlerts.exceptions.DataNotFoundException;
import com.openclassrooms.safetyAlerts.serviceDAO.MedicalrecordServiceImpl;
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

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest

@AutoConfigureMockMvc
public class MedicalrecordControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MedicalrecordServiceImpl medicalrecordService;

    // un medical record
    String firstNameTest = "Alexis";
    String lastNameTest = "Berthelot";
    String birthdateTest;
    List<String> medicationsTest = new ArrayList<String>();
    List<String> allergiesTest = new ArrayList<String>();

    // Test Create

    @Test
    void createMedicalrecordValid() throws Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonMedicalrecord = obm.createObjectNode();
        jsonMedicalrecord.set("firstName", TextNode.valueOf(firstNameTest));
        jsonMedicalrecord.set("lastName", TextNode.valueOf(lastNameTest));

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(jsonMedicalrecord.toString())).andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    void createMedicalrecordInvalid() throws Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonMedicalrecord = obm.createObjectNode();
        jsonMedicalrecord.set("firstName", TextNode.valueOf(firstNameTest));
        jsonMedicalrecord.set("lastName", TextNode.valueOf(""));

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(jsonMedicalrecord.toString())).andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    void createMedicalrecordWhenMedicalrecordDoesntExist() throws Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonMedicalrecord = obm.createObjectNode();
        jsonMedicalrecord.set("firstName", TextNode.valueOf(firstNameTest));
        jsonMedicalrecord.set("lastName", TextNode.valueOf(lastNameTest));

        //when
        Mockito.doThrow(DataAlreadyExistException.class).when(medicalrecordService).createMedicalrecord(Mockito.any());

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(jsonMedicalrecord.toString())).andExpect(MockMvcResultMatchers.status().isConflict());

    }

    // Test Update

    @Test
    void updateMedicalrecordValid() throws Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonMedicalrecord = obm.createObjectNode();
        jsonMedicalrecord.set("firstName", TextNode.valueOf(firstNameTest));
        jsonMedicalrecord.set("lastName", TextNode.valueOf(lastNameTest));

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(jsonMedicalrecord.toString())).andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    void updateMedicalrecordInvalid() throws Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonMedicalrecord = obm.createObjectNode();
        jsonMedicalrecord.set("firstName", TextNode.valueOf(firstNameTest));
        jsonMedicalrecord.set("lastName", TextNode.valueOf(""));

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(jsonMedicalrecord.toString())).andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    void updateMedicalrecordWhenMedicalrecordDoesntExist() throws Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonMedicalrecord = obm.createObjectNode();
        jsonMedicalrecord.set("firstName", TextNode.valueOf(firstNameTest));
        jsonMedicalrecord.set("lastName", TextNode.valueOf(lastNameTest));

        //when
        Mockito.doThrow(DataNotFoundException.class).when(medicalrecordService).updateMedicalrecord(Mockito.any());

        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(jsonMedicalrecord.toString())).andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    // Test Delete

    @Test
    void deleteMedicalrecordValid() throws Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonMedicalrecord = obm.createObjectNode();
        jsonMedicalrecord.set("firstName", TextNode.valueOf(firstNameTest));
        jsonMedicalrecord.set("lastName", TextNode.valueOf(lastNameTest));

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(jsonMedicalrecord.toString())).andExpect(MockMvcResultMatchers.status().isResetContent());

    }

    @Test
    void deleteMedicalrecordInvalid() throws Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonMedicalrecord = obm.createObjectNode();
        jsonMedicalrecord.set("firstName", TextNode.valueOf(firstNameTest));
        jsonMedicalrecord.set("lastName", TextNode.valueOf(""));

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(jsonMedicalrecord.toString())).andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    void deleteMedicalrecordWhenMedicalrecordDoesntExist() throws Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonMedicalrecord = obm.createObjectNode();
        jsonMedicalrecord.set("firstName", TextNode.valueOf(firstNameTest));
        jsonMedicalrecord.set("lastName", TextNode.valueOf(lastNameTest));

        //when
        Mockito.doThrow(DataNotFoundException.class).when(medicalrecordService).deleteMedicalrecord(Mockito.any());

        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(jsonMedicalrecord.toString())).andExpect(MockMvcResultMatchers.status().isNotFound());

    }
}
