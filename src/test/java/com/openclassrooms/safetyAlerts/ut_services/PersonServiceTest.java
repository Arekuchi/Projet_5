package com.openclassrooms.safetyAlerts.ut_services;


import com.openclassrooms.safetyAlerts.dao.MedicalRecordDAO;
import com.openclassrooms.safetyAlerts.dao.PersonDAO;
import com.openclassrooms.safetyAlerts.dto.ChildAlert;
import com.openclassrooms.safetyAlerts.dto.PersonInfo;
import com.openclassrooms.safetyAlerts.exceptions.DataAlreadyExistException;
import com.openclassrooms.safetyAlerts.exceptions.DataNotFoundException;
import com.openclassrooms.safetyAlerts.exceptions.InvalidArgumentException;
import com.openclassrooms.safetyAlerts.model.Medicalrecord;
import com.openclassrooms.safetyAlerts.model.Person;
import com.openclassrooms.safetyAlerts.serviceDAO.MedicalrecordServiceImpl;
import com.openclassrooms.safetyAlerts.serviceDAO.PersoneServiceImpl;
import com.openclassrooms.safetyAlerts.serviceInterface.IChildAlertService;
import com.openclassrooms.safetyAlerts.serviceInterface.ICommunityEmail;
import com.openclassrooms.safetyAlerts.serviceInterface.IPersonInfoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
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
    ICommunityEmail communityEmailMock;

    @MockBean
    MedicalRecordDAO medicalRecordDAOMock;

    @MockBean
    IPersonInfoService personInfoServiceMock;

    @MockBean
    IChildAlertService childAlertServiceMock;

    @MockBean
    MedicalrecordServiceImpl medicalrecordServiceTest;

    @MockBean
    DataNotFoundException dataNotFoundExceptionMock;

    @MockBean
    InvalidArgumentException invalidArgumentExceptionMock;

    // Création de trois objets personnes

    Person personBerthelot = new Person("Alexandre","Berthelot","1509 Culver Street","Culver","88954","555-555-555","berthelot@mail");
    Person personBatiste = new Person("Batiste","Batiste","1790 Culver Street","Culver","88945","555-666-666","blabla@mail");
    Person personHenry = new Person("Henry","Henry","1595 Culver Street","Culver","88945","555-777-777","henrymail@mail");

    List<String> medications = new ArrayList<>(Arrays.asList("a","b","c","d"));
    List<String> allergies = new ArrayList<>(Arrays.asList("a","b","c","d"));

    Person person = new Person();
    PersonInfo personInfo = new PersonInfo();
    ChildAlert childAlert = new ChildAlert();
    Medicalrecord medicalrecordBerthelot = new Medicalrecord("Alexandre", "Berthelot", "15/03/1988", medications, allergies);

    @Test
    public void createExistingPersonTest() throws Exception {

        //given

        //when
        Mockito.when(personDAOMock.createPerson(any(Person.class))).thenReturn(true);

        //then
        try {
            Assertions.assertTrue(personeServiceTest.createPerson(personBerthelot));
            verify(personDAOMock, Mockito.times(1)).createPerson(personBerthelot);
        } catch (DataAlreadyExistException daee) {
            assert (daee.getMessage().contains("existe déjà"));
        }

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
        Mockito.when(personDAOMock.updatePerson(any(Person.class))).thenReturn(true);

        //then
        Assertions.assertTrue(personeServiceTest.updatePerson(personBerthelot));
        verify(personDAOMock, Mockito.times(1)).updatePerson(personBerthelot);

    }

    @Test
    public void updateNonExistingPersonTest() throws Exception {

        //given

        //when
        Mockito.when(personDAOMock.updatePerson(any(Person.class))).thenReturn(false);

        //then
        try {
            Assertions.assertFalse(personeServiceTest.updatePerson(personBerthelot));
            verify(personDAOMock, Mockito.times(1)).updatePerson(personBerthelot);
        } catch (DataNotFoundException dnfe) {
            assert (dnfe.getMessage().contains("n'existe pas"));
        }

    }

    @Test
    public void deleteExistingPersonTest() throws Exception {

        //given

        //when
        Mockito.when(personDAOMock.deletePerson(any(Person.class))).thenReturn(true);

        //then
        Assertions.assertTrue(personeServiceTest.deletePerson(personBerthelot));
        verify(personDAOMock, Mockito.times(1)).deletePerson(personBerthelot);

    }

    @Test
    public void deleteNonExistingPersonTest() throws Exception {

        //given

        //when
        Mockito.when(personDAOMock.deletePerson(any(Person.class))).thenReturn(false);

        //then
        try {
            Assertions.assertFalse(personeServiceTest.deletePerson(personBerthelot));
            verify(personDAOMock, Mockito.times(1)).deletePerson(personBerthelot);
        } catch (DataNotFoundException dnfe) {
            assert (dnfe.getMessage().contains("n'existe pas"));
        }

    }

    @Test
    public void getPersonTest() throws Exception {

        //given
        List<Person> personList = new ArrayList<>();

        //when
        Mockito.when(personDAOMock.getPersons()).thenReturn(personList);

        //then
        Assertions.assertTrue(personDAOMock.getPersons().add(personBerthelot));
        verify(personDAOMock, Mockito.times(1)).getPersons();

    }

    @Test
    public void getValidCommunityEmailTest() throws Exception {

        //given
        Collection<String> personListByCity = new HashSet<>();
        personListByCity.add(personBerthelot.getEmail());

        //when
        Mockito.when(communityEmailMock.getCommunityEmail(any(String.class))).thenReturn(personListByCity);
        personListByCity = communityEmailMock.getCommunityEmail(personBerthelot.getCity());

        //then
        assertThat(communityEmailMock.getCommunityEmail("Culver")).isEqualTo(personListByCity);

    }

    @Test
    public void getInvalidCommunityEmailTest() throws Exception {

        //given
        List<Person> personListByCity = new ArrayList<>();

        //when
        Mockito.when(personDAOMock.getPersonsByCity(any(String.class))).thenReturn(personListByCity);

        //then
        // assertions.assertfalse ?
        try {
            assertThat(communityEmailMock.getCommunityEmail("Versailles")).size().isEqualTo(0);
        } catch (InvalidArgumentException iae) {
            assert (iae.getMessage().contains("Invalid Argument Exception"));

        }

    }

    @Test
    public void getEmptyCityCommunityEmailTest() throws Exception {

        //given
        List<Person> personListByCity = new ArrayList<>();

        //when
        Mockito.when(personDAOMock.getPersonsByCity(any(String.class))).thenReturn(personListByCity);

        //then
        try {
            assertThat(communityEmailMock.getCommunityEmail("").size()).isEqualTo(0);
        } catch (InvalidArgumentException iae) {
            assert (iae.getMessage().contains("ne peut être vide"));
        }

    }

    @Test
    public void getValidPersonInfoTest() throws Exception {

        //given
        Collection<PersonInfo> personInfoList = new ArrayList<>();
        List<Medicalrecord> medicalrecordList = new ArrayList<>();
        personInfoList.add(personInfo);
        medicalrecordList.add(medicalrecordBerthelot);

        //when
        Mockito.when((medicalRecordDAOMock.getMedicalRecordByName(any(String.class), any(String.class)))).thenReturn(medicalrecordBerthelot);
        personInfoList = personInfoServiceMock.getPersonInfo(personBerthelot.getLastName(), personBerthelot.getFirstName());

        //then
        assertThat(personInfoServiceMock.getPersonInfo(personBerthelot.getLastName(), personBerthelot.getFirstName())).isEqualTo(personInfoList);

    }

    @Test
    public void getInvalidPersonInfoTest() throws Exception {

        //given
        Collection<PersonInfo> personInfoList = new ArrayList<>();
        List<Medicalrecord> medicalrecordList = new ArrayList<>();
        personInfoList.add(personInfo);
        medicalrecordList.add(medicalrecordBerthelot);

        //when
        Mockito.when((medicalRecordDAOMock.getMedicalRecordByName(any(String.class), any(String.class)))).thenReturn(medicalrecordBerthelot);
        personInfoList = personInfoServiceMock.getPersonInfo(personBerthelot.getLastName(), personBerthelot.getFirstName());

        //then
        // assertions.assertfalse ?
        try {
            assertThat(personInfoServiceMock.getPersonInfo(personInfo.getLastName(), personInfo.getFirstName())).size().isEqualTo(0);
            verify(personInfoServiceMock, Mockito.times(1)).getPersonInfo(personInfo.getLastName(), personInfo.getFirstName());
        } catch (InvalidArgumentException iae) {
            assert (iae.getMessage().contains("Invalid Argument Exception"));

        }

    }

    @Test
    public void getEmptyPersonInfoTest() throws Exception {

        //given
        Collection<PersonInfo> personInfoList = new ArrayList<>();
        List<Medicalrecord> medicalrecordList = new ArrayList<>();
        personInfoList.add(personInfo);
        medicalrecordList.add(medicalrecordBerthelot);

        //when
        Mockito.when((medicalRecordDAOMock.getMedicalRecordByName(any(String.class), any(String.class)))).thenReturn(medicalrecordBerthelot);
        personInfoList = personInfoServiceMock.getPersonInfo(personBerthelot.getLastName(), personBerthelot.getFirstName());

        //then
        try {
            assertThat(personInfoServiceMock.getPersonInfo("","").size()).isEqualTo(0);
        } catch (InvalidArgumentException iae) {
            assert (iae.getMessage().contains("ne peut être vide"));
        }

    }

    @Test
    public void getValidChildByAddressTest() throws Exception {

        //given
        Collection<ChildAlert> childAlertCollection = new ArrayList<>();
        List<Medicalrecord> medicalrecordList = new ArrayList<>();
        childAlertCollection.add(childAlert);
        medicalrecordList.add(medicalrecordBerthelot);

        //when
        Mockito.when(medicalRecordDAOMock.getMedicalRecordByName(any(String.class), any(String.class))).thenReturn(medicalrecordBerthelot);
        childAlertCollection = childAlertServiceMock.getChildAlert(personBerthelot.getAddress());

        //then
        assertThat(childAlertServiceMock.getChildAlert(personBerthelot.getAddress())).isEqualTo(childAlertCollection);

    }

    @Test
    public void getInvalidChildByAddressTest() throws Exception {

        //given
        Collection<ChildAlert> childAlertCollection = new ArrayList<>();
        List<Medicalrecord> medicalrecordList = new ArrayList<>();
        childAlertCollection.add(childAlert);
        medicalrecordList.add(medicalrecordBerthelot);

        //when
        Mockito.when(medicalRecordDAOMock.getMedicalRecordByName(any(String.class), any(String.class))).thenReturn(medicalrecordBerthelot);
        childAlertCollection = childAlertServiceMock.getChildAlert(personBerthelot.getAddress());

        //then
        try {
            assertThat(childAlertServiceMock.getChildAlert(personBerthelot.getAddress())).size().isEqualTo(0);
            verify(childAlertServiceMock, Mockito.times(1)).getChildAlert(personBerthelot.getAddress());
        } catch (InvalidArgumentException iae) {
            assert (iae.getMessage().contains("Invalid Argument Exception"));

        }

    }

    @Test
    public void getValidChildWithEmptyValidTest() throws Exception {

        //given
        Collection<ChildAlert> childAlertCollection = new ArrayList<>();
        List<Medicalrecord> medicalrecordList = new ArrayList<>();
        childAlertCollection.add(childAlert);
        medicalrecordList.add(medicalrecordBerthelot);

        //when
        Mockito.when(medicalRecordDAOMock.getMedicalRecordByName(any(String.class), any(String.class))).thenReturn(medicalrecordBerthelot);
        childAlertCollection = childAlertServiceMock.getChildAlert(personBerthelot.getAddress());

        //then
        try {
            assertThat(childAlertServiceMock.getChildAlert(personBerthelot.getAddress())).size().isEqualTo(0);
            verify(childAlertServiceMock, Mockito.times(1)).getChildAlert(personBerthelot.getAddress());
        } catch (InvalidArgumentException iae) {
            assert (iae.getMessage().contains("ne peut être vide"));

        }

    }

}
