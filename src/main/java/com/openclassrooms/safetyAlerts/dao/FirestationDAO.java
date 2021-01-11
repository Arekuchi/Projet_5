package com.openclassrooms.safetyAlerts.dao;

import com.openclassrooms.safetyAlerts.model.Database;
import com.openclassrooms.safetyAlerts.model.Firestation;
import com.openclassrooms.safetyAlerts.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class FirestationDAO implements IFirestationDAO {

    @Autowired
    DataRepository dataRepository;

    @Override
    public boolean createFirestation(Firestation firestation) {
        // ajout de la nouvelle personne en mémoire (Object JAVA)
        dataRepository.getDatabase().getFirestations().add(firestation);
        // commit pour appliquer les changements sur le json
        dataRepository.commit();

        return true;
    }

    @Override
    public boolean deleteFirestation(Firestation firestation) {

        boolean result=dataRepository.getDatabase().getFirestations().remove(firestation);

        dataRepository.commit();
        return result;
    }

    @Override
    public boolean updateFirestation(Firestation firestation) {

        if (dataRepository.getDatabase().getFirestations().remove(firestation)) {
            createFirestation(firestation);
            return true;
        }
        return false;
    }

    @Override
    public Firestation getFirestationByAddress(String address) {
        Firestation firestationAddress = new Firestation();
        Database db = dataRepository.getDatabase();
        for (Firestation firestation : db.getFirestations()) {
            if (firestation.getAddress().equalsIgnoreCase(address)) {
                firestationAddress = firestation;
            }
        }
        return firestationAddress;
    }

    @Override
    public List<Firestation> getFirestationAddressByStation(String stationNumber) {
        List<Firestation> firestationList = new ArrayList<Firestation>();
        Database db = dataRepository.getDatabase();
        for (Firestation firestation : db.getFirestations()) {
            if (firestation.getStation().equals(stationNumber)) {
                firestationList.add(firestation);
            }
        }
        return firestationList;
    }

    @Override
    public List<Firestation> getFirestationAddressByStationList(List<String> firestationNumber) {
        List<Firestation> firestationAddress = new ArrayList<Firestation>();
        Database db = dataRepository.getDatabase();
        for (Firestation firestation : db.getFirestations()) {
            if (firestation.getStation().equals(firestationNumber)) {
                firestationAddress.add(firestation);
            }
        }
        return firestationAddress;
    }

    @Override
    public Collection<Firestation> getFirestations(String address) {
        Collection<Firestation> firestationCollection = new ArrayList<>();
        Database db = dataRepository.getDatabase();
        for (Firestation firestation : db.getFirestations()) {
            if (firestation.getAddress().equalsIgnoreCase(address)) {
                firestationCollection.add(firestation);
            }
        }
        return firestationCollection;
    }


}
