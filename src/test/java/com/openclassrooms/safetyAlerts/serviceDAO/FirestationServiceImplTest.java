package com.openclassrooms.safetyAlerts.serviceDAO;

import com.openclassrooms.safetyAlerts.dao.FirestationDAO;
import com.openclassrooms.safetyAlerts.model.Firestation;
import com.openclassrooms.safetyAlerts.repository.DataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
class FirestationServiceImplTest {


    Firestation firestation = new Firestation("15 rue addresse", "9");

    @Autowired
    FirestationDAO firestationDAO;

    @Autowired
    DataRepository dataRepository;

    @BeforeEach
    void init() {
        dataRepository.init();
        dataRepository.setCommit(false);
    }

    @Test
    void createFirestation() {
        assertThat(dataRepository.getListFirestation().contains(firestation)).isFalse();
        assertThat(firestationDAO.createFirestation(firestation)).isTrue();
        assertThat(dataRepository.getListFirestation().contains(firestation)).isTrue();
    }

    @Test
    void deleteFirestation() {
        assertThat(firestationDAO.deleteFirestation(firestation)).isFalse();
        assertThat(firestationDAO.createFirestation(firestation)).isTrue();
        assertThat(dataRepository.getListFirestation().contains(firestation)).isTrue();
        assertThat(firestationDAO.deleteFirestation(firestation)).isTrue();
        assertThat(dataRepository.getListFirestation().contains(firestation)).isFalse();
    }

    @Test
    void updateFirestation() {
        assertThat(firestationDAO.updateFirestation(firestation)).isFalse();
        assertThat(firestationDAO.createFirestation(firestation)).isTrue();
        assertThat(dataRepository.getListFirestation().contains(firestation)).isTrue();
        assertThat(firestationDAO.updateFirestation(firestation)).isTrue();
    }
}