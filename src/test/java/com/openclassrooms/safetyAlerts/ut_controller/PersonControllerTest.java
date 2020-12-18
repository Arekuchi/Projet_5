package com.openclassrooms.safetyAlerts.ut_controller;


import com.openclassrooms.safetyAlerts.service.CommunityEmail;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
