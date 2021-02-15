package com.openclassrooms.safetyAlerts.ut_services;

import com.openclassrooms.safetyAlerts.dao.IMedicalrecordDAO;
import com.openclassrooms.safetyAlerts.exceptions.DataAlreadyExistException;
import com.openclassrooms.safetyAlerts.exceptions.DataNotFoundException;
import com.openclassrooms.safetyAlerts.exceptions.InvalidArgumentException;
import com.openclassrooms.safetyAlerts.model.Medicalrecord;
import com.openclassrooms.safetyAlerts.serviceDAO.MedicalrecordServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest

@AutoConfigureMockMvc
public class MedicalrecordServiceTest {

    @Autowired
    MedicalrecordServiceImpl medicalrecordServiceTest;

    @MockBean
    IMedicalrecordDAO medicalRecordDAOMock;

    @MockBean
    DataNotFoundException dataNotFoundExceptionMock;

    @MockBean
    InvalidArgumentException invalidArgumentExceptionMock;

    List<String> medications = new ArrayList<>(Arrays.asList("a","b","c","d"));
    List<String> allergies = new ArrayList<>(Arrays.asList("a","b","c","d"));

    Medicalrecord medicalrecordBerthelot = new Medicalrecord("Alexandre", "Berthelot", "15/03/1988", medications, allergies);
    Medicalrecord medicalrecordDrucker = new Medicalrecord("Michel", "Drucker", "15/03/1988", medications, allergies);
    Medicalrecord medicalrecordBern = new Medicalrecord("Stephane", "Bern", "15/03/1988", medications, allergies);


    @Test
    public void createExistingMedicalrecordTest() throws Exception {

        //given

        //when
        Mockito.when(medicalRecordDAOMock.createMedicalrecord(any(Medicalrecord.class))).thenReturn(true);

        //then
        try {
            Assertions.assertTrue(medicalrecordServiceTest.createMedicalrecord(medicalrecordBerthelot));
            verify(medicalRecordDAOMock, Mockito.times(1)).createMedicalrecord(medicalrecordBerthelot);
        } catch (DataAlreadyExistException daee) {
            assert (daee.getMessage().contains("existe déjà"));
        }


    }

    @Test
    public void createNonExistingMedicalrecordTest() throws Exception {

        //given

        //when
        Mockito.when(medicalRecordDAOMock.createMedicalrecord(any(Medicalrecord.class))).thenReturn(true);

        //then
        Assertions.assertTrue(medicalRecordDAOMock.createMedicalrecord(medicalrecordBerthelot));
        verify(medicalRecordDAOMock, Mockito.times(1)).createMedicalrecord(medicalrecordBerthelot);


    }

    @Test
    public void updateExistingMedicalrecordTest() throws Exception {

        //given

        //when
        Mockito.when(medicalRecordDAOMock.updateMedicalrecord(any(Medicalrecord.class))).thenReturn(true);

        //then
        Assertions.assertTrue(medicalRecordDAOMock.updateMedicalrecord(medicalrecordBerthelot));
        verify(medicalRecordDAOMock, Mockito.times(1)).updateMedicalrecord(medicalrecordBerthelot);


    }

    @Test
    public void updateNonExistingMedicalrecordTest() throws Exception {

        //given

        //when
        Mockito.when(medicalRecordDAOMock.updateMedicalrecord(any(Medicalrecord.class))).thenReturn(false);

        //then
        try {
            Assertions.assertFalse(medicalRecordDAOMock.updateMedicalrecord(medicalrecordBerthelot));
            verify(medicalRecordDAOMock, Mockito.times(1)).updateMedicalrecord(medicalrecordBerthelot);
        } catch (DataNotFoundException dnfe) {
            assert (dnfe.getMessage().contains("n'existe pas"));
        }


    }

    @Test
    public void deleteExistingMedicalrecordTest() throws Exception {

        //given

        //when
        Mockito.when(medicalRecordDAOMock.deleteMedicalrecord(any(Medicalrecord.class))).thenReturn(true);

        //then
        Assertions.assertTrue(medicalRecordDAOMock.deleteMedicalrecord(medicalrecordBerthelot));
        verify(medicalRecordDAOMock, Mockito.times(1)).deleteMedicalrecord(medicalrecordBerthelot);


    }

    @Test
    public void deleteNonExistingMedicalrecordTest() throws Exception {

        //given

        //when
        Mockito.when(medicalRecordDAOMock.deleteMedicalrecord(any(Medicalrecord.class))).thenReturn(false);

        //then
        try {
            Assertions.assertFalse(medicalRecordDAOMock.deleteMedicalrecord(medicalrecordBerthelot));
            verify(medicalRecordDAOMock, Mockito.times(1)).deleteMedicalrecord(medicalrecordBerthelot);
        } catch (DataNotFoundException dnfe) {
            assert (dnfe.getMessage().contains("n'existe pas"));
        }
    }

    @Test
    public void getMedicalrecordTest() throws Exception {

        //given
        List<Medicalrecord> medicalrecordList = new ArrayList<>();

        //when
        Mockito.when(medicalRecordDAOMock.getMedicalrecords(any(String.class))).thenReturn(medicalrecordList);

        //then
        Assertions.assertTrue(medicalRecordDAOMock.getMedicalrecords(medicalrecordBerthelot.getBirthdate()).add(medicalrecordBerthelot));
        verify(medicalRecordDAOMock, Mockito.times(1)).getMedicalrecords(any(String.class));

    }

    @Test
    public void getMedicalrecordByNameTest() throws Exception {

        //given
        Medicalrecord medicalrecordList = medicalrecordBerthelot;

        //when
        Mockito.when((medicalRecordDAOMock.getMedicalRecordByName(any(String.class), any(String.class)))).thenReturn(medicalrecordBerthelot);

        //then
        assertThat(medicalRecordDAOMock.getMedicalRecordByName(medicalrecordBerthelot.getLastName(), medicalrecordBerthelot.getFirstName())).isEqualTo(medicalrecordList);

    }

}
