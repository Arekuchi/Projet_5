package com.openclassrooms.safetyAlerts.ut_services;


import com.openclassrooms.safetyAlerts.dao.MedicalRecordDAO;
import com.openclassrooms.safetyAlerts.dao.PersonDAO;
import com.openclassrooms.safetyAlerts.exceptions.DataNotFoundException;
import com.openclassrooms.safetyAlerts.exceptions.InvalidArgumentException;
import com.openclassrooms.safetyAlerts.model.Person;
import com.openclassrooms.safetyAlerts.serviceDAO.MedicalrecordServiceImpl;
import com.openclassrooms.safetyAlerts.serviceDAO.PersoneServiceImpl;
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

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest

@AutoConfigureMockMvc
public class PersonServiceTest {

    @Autowired
    PersoneServiceImpl personeServiceTest;

    @MockBean
    PersonDAO personDAOMock;

    @MockBean
    MedicalRecordDAO medicalRecordDAOMock;

    @MockBean
    MedicalrecordServiceImpl medicalrecordServiceTest;

    @MockBean
    DataNotFoundException dataNotFoundExceptionMock;

    @MockBean
    InvalidArgumentException invalidArgumentExceptionMock;

    // Création de trois objets personnes

    Person personBerthelot = new Person("Alexis","Berthelot","1509 Culver Street","Culver","88954","555-555-555","berthelot@mail");
    Person personBatiste = new Person("Batiste","Batiste","1790 Culver Street","Culver","88945","555-666-666","blabla@mail");
    Person personHenry = new Person("Henry","Henry","1595 Culver Street","Culver","88945","555-777-777","henrymail@mail");

    List<String> medications = new ArrayList<>(Arrays.asList("a","b","c","d"));
    List<String> allergies = new ArrayList<>(Arrays.asList("a","b","c","d"));

    @Test
    public void createExistingPersonTest() throws Exception {

        //given

        //when

        //then

    }

    @Test
    public void createNonExistingPersonTest() throws Exception {

        //given
        List<Person> listPerson= new ArrayList<>();

        //when
        Mockito.when(personDAOMock.getPersons()).thenReturn(listPerson);

        //then
        Assertions.assertTrue(personeServiceTest.createPerson(personBerthelot));
        verify(personDAOMock, Mockito.times(1)).createPerson(personBerthelot);

    }

    @Test
    public void updateExistingPersonTest() throws Exception {

        //given

        //when

        //then

    }

    @Test
    public void updateNonExistingPersonTest() throws Exception {

        //given

        //when

        //then

    }

    @Test
    public void deleteExistingPersonTest() throws Exception {

        //given

        //when

        //then

    }

    @Test
    public void deleteNonExistingPersonTest() throws Exception {

        //given

        //when

        //then

    }

    @Test
    public void getPersonTest() throws Exception {

        //given

        //when

        //then

    }

    @Test
    public void getValidCommunityEmailTest() throws Exception {

        //given

        //when

        //then

    }

    @Test
    public void getInvalidCommunityEmailTest() throws Exception {

        //given

        //when

        //then

    }

    @Test
    public void getEmptyCityCommunityEmailTest() throws Exception {

        //given

        //when

        //then

    }

    @Test
    public void getValidPersonInfoTest() throws Exception {

        //given

        //when

        //then

    }

    @Test
    public void getInvalidPersonInfoTest() throws Exception {

        //given

        //when

        //then

    }

    @Test
    public void getEmptyPersonInfoTest() throws Exception {

        //given

        //when

        //then

    }

    @Test
    public void getValidChildByAddressTest() throws Exception {

        //given

        //when

        //then

    }

    @Test
    public void getInvalidChildByAddressTest() throws Exception {

        //given

        //when

        //then

    }

    @Test
    public void getValidChildWithEmptyValidTest() throws Exception {

        //given

        //when

        //then

    }






}
