package com.openclassrooms.safetyAlerts.ut_controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.openclassrooms.safetyAlerts.exceptions.DataAlreadyExistException;
import com.openclassrooms.safetyAlerts.exceptions.DataNotFoundException;
import com.openclassrooms.safetyAlerts.serviceDAO.FirestationServiceImpl;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest

@AutoConfigureMockMvc
public class FirestationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    FirestationServiceImpl firestationService;

    // Une Firestation
    String addressTest = "1509 Culver St";
    String stationTest = "3";

    // Test Create

    @Test
    void createFirestationValid() throws Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonFirestation = obm.createObjectNode();
        jsonFirestation.set("address", TextNode.valueOf(addressTest));
        jsonFirestation.set("station", TextNode.valueOf(stationTest));
        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/firestation").contentType(MediaType.APPLICATION_JSON).content(jsonFirestation.toString())).andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    void createFirestationInvalid() throws Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonFirestation = obm.createObjectNode();
        jsonFirestation.set("address", TextNode.valueOf(addressTest));
        jsonFirestation.set("station", TextNode.valueOf(""));

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/firestation").contentType(MediaType.APPLICATION_JSON).content(jsonFirestation.toString())).andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    void createFirestationWhenFirestationAlreadyExist() throws Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonFirestation = obm.createObjectNode();
        jsonFirestation.set("address", TextNode.valueOf(addressTest));
        jsonFirestation.set("station", TextNode.valueOf(stationTest));

        //when
        Mockito.doThrow(DataAlreadyExistException.class).when(firestationService).createFirestation(Mockito.any());

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/firestation").contentType(MediaType.APPLICATION_JSON).content(jsonFirestation.toString())).andExpect(MockMvcResultMatchers.status().isConflict());

    }

    // Test Update

    @Test
    void updateFirestationValid() throws Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonFirestation = obm.createObjectNode();
        jsonFirestation.set("address", TextNode.valueOf(addressTest));
        jsonFirestation.set("station", TextNode.valueOf(stationTest));

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/firestation").contentType(MediaType.APPLICATION_JSON).content(jsonFirestation.toString())).andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    void updateFirestationInvalid() throws Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonFirestation = obm.createObjectNode();
        jsonFirestation.set("address", TextNode.valueOf(addressTest));
        jsonFirestation.set("station", TextNode.valueOf(""));

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/firestation").contentType(MediaType.APPLICATION_JSON).content(jsonFirestation.toString())).andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    void updateFirestationWhenFirestationDoesntExist() throws Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonFirestation = obm.createObjectNode();
        jsonFirestation.set("address", TextNode.valueOf(addressTest));
        jsonFirestation.set("station", TextNode.valueOf(stationTest));


        //when
        Mockito.doThrow(DataNotFoundException.class).when(firestationService).updateFirestation(Mockito.any());

        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/firestation").contentType(MediaType.APPLICATION_JSON).content(jsonFirestation.toString())).andExpect(MockMvcResultMatchers.status().isNotFound());

    }
    // Test Delete

    @Test
    void deleteFirestationValid() throws Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonFirestation = obm.createObjectNode();
        jsonFirestation.set("address", TextNode.valueOf(addressTest));
        jsonFirestation.set("station", TextNode.valueOf(stationTest));

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/firestation").contentType(MediaType.APPLICATION_JSON).content(jsonFirestation.toString())).andExpect(MockMvcResultMatchers.status().isResetContent());

    }

    @Test
    void deleteFirestationInvalid() throws Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonFirestation = obm.createObjectNode();
        jsonFirestation.set("address", TextNode.valueOf(addressTest));
        jsonFirestation.set("station", TextNode.valueOf(""));

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/firestation").contentType(MediaType.APPLICATION_JSON).content(jsonFirestation.toString())).andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    void deleteFirestationWhenFirestationDoesntExist() throws Exception {

        //given
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonFirestation = obm.createObjectNode();
        jsonFirestation.set("address", TextNode.valueOf(addressTest));
        jsonFirestation.set("station", TextNode.valueOf(stationTest));

        //when
        Mockito.doThrow(DataNotFoundException.class).when(firestationService).deleteFirestation(Mockito.any());

        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/firestation").contentType(MediaType.APPLICATION_JSON).content(jsonFirestation.toString())).andExpect(MockMvcResultMatchers.status().isNotFound());

    }

}
