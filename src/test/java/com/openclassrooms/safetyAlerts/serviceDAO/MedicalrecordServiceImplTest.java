package com.openclassrooms.safetyAlerts.serviceDAO;

import com.openclassrooms.safetyAlerts.dao.MedicalRecordDAO;
import com.openclassrooms.safetyAlerts.model.Medicalrecord;
import com.openclassrooms.safetyAlerts.repository.DataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
class MedicalrecordServiceImplTest {

    List<String> medications = new ArrayList<String>(
            Arrays.asList("a", "b", "c", "d"));
    List<String> allergies = new ArrayList<String>(
            Arrays.asList("e", "f", "g", "h"));

    Medicalrecord medicalrecord = new Medicalrecord("Barack", "Obama",
            "03/06/1984", medications, allergies);

    @Autowired
    MedicalRecordDAO medicalRecordDAO;

    @Autowired
    DataRepository dataRepository;

    @BeforeEach
    void init() {
        dataRepository.init();
        dataRepository.setCommit(false);
    }


    @Test
    void createMedicalrecord() {
        assertThat(dataRepository.getAllMedicalRecord().contains(medicalrecord)).isFalse();
        assertThat(medicalRecordDAO.createMedicalrecord(medicalrecord)).isTrue();
        assertThat(dataRepository.getAllMedicalRecord().contains(medicalrecord)).isTrue();
    }

    @Test
    void deleteMedicalrecord() {
        assertThat(medicalRecordDAO.deleteMedicalrecord(medicalrecord)).isFalse();
        assertThat(medicalRecordDAO.createMedicalrecord(medicalrecord)).isTrue();
        assertThat(dataRepository.getAllMedicalRecord().contains(medicalrecord)).isTrue();
        assertThat(medicalRecordDAO.deleteMedicalrecord(medicalrecord)).isTrue();
        assertThat(dataRepository.getAllMedicalRecord().contains(medicalrecord)).isFalse();

    }

    @Test
    void updateMedicalrecord() {
        assertThat(medicalRecordDAO.updateMedicalrecord(medicalrecord)).isFalse();
        assertThat(medicalRecordDAO.createMedicalrecord(medicalrecord)).isTrue();
        assertThat(dataRepository.getAllMedicalRecord().contains(medicalrecord)).isTrue();
        assertThat(medicalRecordDAO.updateMedicalrecord(medicalrecord)).isTrue();

    }
}