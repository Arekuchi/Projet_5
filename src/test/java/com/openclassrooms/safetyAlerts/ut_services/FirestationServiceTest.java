package com.openclassrooms.safetyAlerts.ut_services;

import com.openclassrooms.safetyAlerts.dao.*;
import com.openclassrooms.safetyAlerts.dto.Fire;
import com.openclassrooms.safetyAlerts.dto.FirestationDTO;
import com.openclassrooms.safetyAlerts.dto.PhoneAlert;
import com.openclassrooms.safetyAlerts.exceptions.DataAlreadyExistException;
import com.openclassrooms.safetyAlerts.exceptions.DataNotFoundException;
import com.openclassrooms.safetyAlerts.exceptions.InvalidArgumentException;
import com.openclassrooms.safetyAlerts.model.Firestation;
import com.openclassrooms.safetyAlerts.model.Medicalrecord;
import com.openclassrooms.safetyAlerts.model.Person;
import com.openclassrooms.safetyAlerts.serviceDAO.FirestationServiceImpl;
import com.openclassrooms.safetyAlerts.serviceInterface.IFireService;
import com.openclassrooms.safetyAlerts.serviceInterface.IFirestationService;
import com.openclassrooms.safetyAlerts.serviceInterface.IFloodService;
import com.openclassrooms.safetyAlerts.serviceInterface.IPhoneAlertService;
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
public class FirestationServiceTest {

    @Autowired
    FirestationServiceImpl firestationServiceTest;

    @MockBean
    IFirestationDAO firestationDAOMock;

    @MockBean
    IPersonDAO personDAOMock;

    @MockBean
    MedicalRecordDAO medicalRecordDAOMock;

    @MockBean
    IFloodService floodServiceMock;

    @MockBean
    IFireService fireServiceMock;

    @MockBean
    IPhoneAlertService phoneAlertServiceMock;

    @MockBean
    IFirestationService firestationServiceMock;

    @MockBean
    DataNotFoundException dataNotFoundExceptionMock;

    @MockBean
    InvalidArgumentException invalidArgumentExceptionMock;


    Firestation firestation1 = new Firestation("1509 Culver Street", "1");
    Firestation firestation2 = new Firestation("709 Booker Street", "2");
    Firestation firestation3 = new Firestation("788 Deutsch avenue","3");

    List<String> listStationOK = new ArrayList<>(Arrays.asList("1", "2"));
    List<String> listStationKO = new ArrayList<>(Arrays.asList("1500", "7777"));
    List<String> listStationEmpty = new ArrayList<>(Arrays.asList("" , ""));

    Person personBerthelot = new Person("Alexandre","Berthelot","1509 Culver Street","Culver","88954","555-555-555","berthelot@mail");

    List<String> medications = new ArrayList<>(Arrays.asList("a","b","c","d"));
    List<String> allergies = new ArrayList<>(Arrays.asList("a","b","c","d"));
    Medicalrecord medicalrecordBerthelot = new Medicalrecord("Alexandre", "Berthelot", "15/03/1988", medications, allergies);

    Fire fire = new Fire();
    FirestationDTO firestationDTO = new FirestationDTO();
    PhoneAlert phoneAlert = new PhoneAlert();


    @Test
    public void createExistingFirestationTest() throws Exception {

        //given

        //when
        Mockito.when(firestationDAOMock.createFirestation(any(Firestation.class))).thenReturn(true);

        //then
        try {
            Assertions.assertTrue(firestationServiceTest.createFirestation(firestation1));
            verify(firestationDAOMock, Mockito.times(1)).createFirestation(firestation1);
        } catch (DataAlreadyExistException daee) {
            assert (daee.getMessage().contains("existe déjà"));
        }

    }

    @Test
    public void createNonExistingFirestationTest() throws Exception {

        //given
        Collection<Firestation> firestationList = new ArrayList<>();

        //when
        Mockito.when(firestationDAOMock.getFirestations(any(String.class))).thenReturn(firestationList);

        //then
        Assertions.assertTrue(firestationServiceTest.createFirestation(firestation1));
        verify(firestationDAOMock, Mockito.times(1)).createFirestation(firestation1);

    }

    @Test
    public void updateExistingFirestationTest() throws Exception {

        //given

        //when
        Mockito.when(firestationDAOMock.updateFirestation(any(Firestation.class))).thenReturn(true);

        //then
        Assertions.assertTrue(firestationServiceTest.updateFirestation(firestation1));
        verify(firestationDAOMock, Mockito.times(1)).updateFirestation(firestation1);

    }

    @Test
    public void updateNonExistingFirestationTest() throws Exception {

        //given

        //when
        Mockito.when(firestationDAOMock.updateFirestation(any(Firestation.class))).thenReturn(false);

        //then
        try {
            Assertions.assertFalse(firestationServiceTest.updateFirestation(firestation1));
            verify(firestationDAOMock, Mockito.times(1)).updateFirestation(firestation1);
        } catch (DataNotFoundException dnfe) {
            assert (dnfe.getMessage().contains("n'existe pas"));
        }

    }

    @Test
    public void deleteExistingFirestationTest() throws Exception {

        //given

        //when
        Mockito.when(firestationDAOMock.deleteFirestation(any(Firestation.class))).thenReturn(true);

        //then
        Assertions.assertTrue(firestationServiceTest.deleteFirestation(firestation1));
        verify(firestationDAOMock, Mockito.times(1)).deleteFirestation(firestation1);

    }

    @Test
    public void deleteNonExistingFirestationTest() throws Exception {

        //given

        //when
        Mockito.when(firestationDAOMock.deleteFirestation(any(Firestation.class))).thenReturn(false);

        //then
        try {
            Assertions.assertFalse(firestationServiceTest.deleteFirestation(firestation1));
            verify(firestationDAOMock, Mockito.times(1)).deleteFirestation(firestation1);
        } catch (DataNotFoundException dnfe) {
            assert (dnfe.getMessage().contains("n'existe pas"));
        }
    }

    @Test
    public void getFirestationTest() throws Exception {

        //given
        List<Firestation> firestationList = new ArrayList<>();

        //when
        Mockito.when(firestationDAOMock.getFirestations(any(String.class))).thenReturn(firestationList);

        //then
        Assertions.assertTrue(firestationDAOMock.getFirestations(firestation1.getAddress()).add(firestation1));
        verify(firestationDAOMock, Mockito.times(1)).getFirestations(any(String.class));
    }


    // tests sur le controler Fire
    @Test
    public void getValidAddressFirestationListPersonTest() throws Exception {

        //given
        Collection<Fire> fireCollection = new ArrayList<>();
        List<Medicalrecord> medicalrecordList = new ArrayList<>();
        List<Firestation> firestationList = new ArrayList<>();
        fireCollection.add(fire);
        medicalrecordList.add(medicalrecordBerthelot);
        firestationList.add(firestation1);

        //when
        Mockito.when(fireServiceMock.getFire(any(String.class))).thenReturn(fireCollection);
        fireCollection = fireServiceMock.getFire(firestation1.getAddress());

        //then
        assertThat(fireServiceMock.getFire(firestation1.getAddress()).size()).isEqualTo(1);
        assertThat(fireServiceMock.getFire(firestation1.getAddress())).isEqualTo(fireCollection);

    }

    @Test
    public void getInvalidAddressFirestationListPersonTest() throws Exception {

        //given
        Collection<Fire> fireCollection = new ArrayList<>();
        List<Medicalrecord> medicalrecordList = new ArrayList<>();
        List<Firestation> firestationList = new ArrayList<>();
        fireCollection.add(fire);
        medicalrecordList.add(medicalrecordBerthelot);
        firestationList.add(firestation1);

        //when
        Mockito.when(firestationDAOMock.getFirestationByAddress(firestation1.getAddress())).thenReturn(firestation1);
        fireCollection = fireServiceMock.getFire(firestation1.getAddress());

        //then
        try {
            assertThat(fireServiceMock.getFire(firestation1.getAddress())).size().isEqualTo(0);
            verify(fireServiceMock, Mockito.times(2)).getFire(firestation1.getAddress());
        } catch (InvalidArgumentException iae) {
            assert (iae.getMessage().contains("Invalid Argument Exception"));

        }
    }

    @Test
    public void getEmptyAddressFirestationListPersonTest() throws Exception {

        //given
        Collection<Fire> fireCollection = new ArrayList<>();
        List<Medicalrecord> medicalrecordList = new ArrayList<>();
        List<Firestation> firestationList = new ArrayList<>();
        fireCollection.add(fire);
        medicalrecordList.add(medicalrecordBerthelot);
        firestationList.add(firestation1);

        //when
        Mockito.when(firestationDAOMock.getFirestationByAddress(firestation1.getAddress())).thenReturn(firestation1);
        fireCollection = fireServiceMock.getFire(firestation1.getAddress());

        //then
        try {
            assertThat(fireServiceMock.getFire("").size()).isEqualTo(0);
        } catch (InvalidArgumentException iae) {
            assert (iae.getMessage().contains("ne peut être vide"));
        }
    }

    // Tests sur le controler PhoneAlert
    @Test
    public void getValidFirestationListPhoneTest() throws Exception {

        //given
        Collection<String> phoneAlertCollection = new HashSet<>();
        List<Firestation> firestationList = new ArrayList<>();
        phoneAlertCollection.add(personBerthelot.getPhone());
        firestationList.add(firestation1);

        //when
        Mockito.when(firestationDAOMock.getFirestationAddressByStation(any(String.class))).thenReturn(firestationList);
        phoneAlertCollection = phoneAlertServiceMock.getPhoneList(personBerthelot.getPhone());

        //then
        // assertThat(phoneAlertServiceMock.getPhoneList(personBerthelot.getPhone()).size()).isEqualTo(1);
        assertThat(phoneAlertServiceMock.getPhoneList(personBerthelot.getPhone())).isEqualTo(phoneAlertCollection);


    }

    @Test
    public void getInvalidFirestationListPhoneTest() throws Exception {

        //given
        Collection<String> phoneAlertCollection = new HashSet<>();
        List<Firestation> firestationList = new ArrayList<>();
        phoneAlertCollection.add(personBerthelot.getPhone());
        firestationList.add(firestation1);

        //when
        Mockito.when(firestationDAOMock.getFirestationAddressByStation(any(String.class))).thenReturn(firestationList);
        phoneAlertCollection = phoneAlertServiceMock.getPhoneList(personBerthelot.getPhone());

        //then
        try {
            assertThat(phoneAlertServiceMock.getPhoneList(any(String.class))).size().isEqualTo(0);
            verify(phoneAlertServiceMock, Mockito.times(1)).getPhoneList(personBerthelot.getPhone());
        } catch (InvalidArgumentException iae) {
            assert (iae.getMessage().contains("Invalid Argument Exception"));

        }

    }

    @Test
    public void getEmptyFirestationListPhoneTest() throws Exception {

        //given
        Collection<String> phoneAlertCollection = new HashSet<>();
        List<Firestation> firestationList = new ArrayList<>();
        phoneAlertCollection.add(personBerthelot.getPhone());
        firestationList.add(firestation1);

        //when
        Mockito.when(firestationDAOMock.getFirestationAddressByStation(any(String.class))).thenReturn(firestationList);
        phoneAlertCollection = phoneAlertServiceMock.getPhoneList("");

        //then
        try {
            assertThat(phoneAlertServiceMock.getPhoneList(any(String.class))).size().isEqualTo(0);
            verify(phoneAlertServiceMock, Mockito.times(1)).getPhoneList("");
        } catch (InvalidArgumentException iae) {
            assert (iae.getMessage().contains("ne peut être vide"));

        }

    }

    // Tests sur le controler Firestation, qui utilise FirestationDTO
    @Test
    public void getValidFirestationListFirestationDTOTest() throws Exception {

        //given
        Collection<FirestationDTO> firestationDTOCollection = new ArrayList<>();
        List<Medicalrecord> medicalrecordList = new ArrayList<>();
        List<Firestation> firestationListAddressByStation = new ArrayList<>();
        firestationDTOCollection.add(firestationDTO);
        medicalrecordList.add(medicalrecordBerthelot);
        firestationListAddressByStation.add(firestation1);

        //when
        Mockito.when(firestationDAOMock.getFirestationAddressByStation(any(String.class))).thenReturn(firestationListAddressByStation);
        firestationDTOCollection = firestationServiceMock.getFirestationDTO(firestation1.getStation());

        //then
        assertThat(firestationServiceMock.getFirestationDTO(firestation1.getStation())).isEqualTo(firestationDTOCollection);
        // assertThat(firestationServiceMock.getFirestationDTO(firestation1.getStation()).size()).isEqualTo(1);

    }

    @Test
    public void getInvalidFirestationListFirestationDTOTest() throws Exception {

        //given
        Collection<FirestationDTO> firestationDTOCollection = new ArrayList<>();
        List<Medicalrecord> medicalrecordList = new ArrayList<>();
        List<Firestation> firestationListAddressByStation = new ArrayList<>();
        firestationListAddressByStation.add(firestation1);

        //when
        Mockito.when(firestationDAOMock.getFirestationAddressByStation(any(String.class))).thenReturn(firestationListAddressByStation);

        //then
        try {
            assertThat(firestationServiceMock.getFirestationDTO(any(String.class))).size().isEqualTo(0);

        } catch (InvalidArgumentException iae) {
            assert (iae.getMessage().contains("Invalid Argument Exception"));

        }

    }

    @Test
    public void getEmptyFirestationListFirestationDTOTest() throws Exception {

        //given
        Collection<FirestationDTO> firestationDTOCollection = new ArrayList<>();
        List<Medicalrecord> medicalrecordList = new ArrayList<>();
        List<Firestation> firestationListAddressByStation = new ArrayList<>();
        firestationListAddressByStation.add(firestation1);

        //when
        Mockito.when(firestationDAOMock.getFirestationAddressByStation(any(String.class))).thenReturn(firestationListAddressByStation);
        firestationDTOCollection = firestationServiceMock.getFirestationDTO("");

        //then
        try {
            assertThat(firestationServiceMock.getFirestationDTO(any(String.class))).size().isEqualTo(0);
            verify(firestationServiceMock, Mockito.times(1)).getFirestationDTO("");
        } catch (InvalidArgumentException iae) {
            assert (iae.getMessage().contains("ne peut être vide"));

        }
    }
}
