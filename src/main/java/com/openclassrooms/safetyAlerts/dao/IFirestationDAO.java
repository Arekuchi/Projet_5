package com.openclassrooms.safetyAlerts.dao;

import com.openclassrooms.safetyAlerts.model.Firestation;

import java.util.List;


public interface IFirestationDAO {

    boolean createFirestation(Firestation firestation);
    boolean deleteFirestation(Firestation firestation);
    boolean updateFirestation(Firestation firestation);

    Firestation getFirestationByAddress(String address);
    List<Firestation> getFirestationAddressByStation(String stationNumber);
    List<Firestation> getFirestationAddressByStationList(List<String> firestationNumber);
}
