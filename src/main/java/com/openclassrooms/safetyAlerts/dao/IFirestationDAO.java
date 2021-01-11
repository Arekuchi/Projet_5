package com.openclassrooms.safetyAlerts.dao;

import com.openclassrooms.safetyAlerts.model.Firestation;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface IFirestationDAO {

    boolean createFirestation(Firestation firestation);
    boolean deleteFirestation(Firestation firestation);
    boolean updateFirestation(Firestation firestation);

    Firestation getFirestationByAddress(String address);
    List<Firestation> getFirestationAddressByStation(String stationNumber);
    List<Firestation> getFirestationAddressByStationList(List<String> firestationNumber);
    Collection<Firestation> getFirestations(String address);
}
