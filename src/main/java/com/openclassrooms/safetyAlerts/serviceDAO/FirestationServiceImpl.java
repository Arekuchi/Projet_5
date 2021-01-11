package com.openclassrooms.safetyAlerts.serviceDAO;

import com.openclassrooms.safetyAlerts.dao.FirestationDAO;
import com.openclassrooms.safetyAlerts.dao.IFirestationDAO;
import com.openclassrooms.safetyAlerts.exceptions.DataAlreadyExistException;
import com.openclassrooms.safetyAlerts.exceptions.DataNotFoundException;
import com.openclassrooms.safetyAlerts.model.Firestation;
import com.openclassrooms.safetyAlerts.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class FirestationServiceImpl implements IFirestationService {

    @Autowired
    DataRepository dataRepository;

    @Autowired
    IFirestationDAO firestationDAO;

    @Override
    public boolean createFirestation(Firestation firestation) {

        // vérif par nom + numéro ne fonctionne pas
        // peut-être un constainskey ?
        if (!dataRepository.getDatabase().getFirestations().contains(firestation)) {
            firestationDAO.createFirestation(firestation);
            return true;
        }
        else {
            throw new DataAlreadyExistException("La Firestation " + firestation.getAddress() + " numéro " + firestation.getStation() + " existe déjà. ");
        }
    }

    @Override
    public boolean deleteFirestation(Firestation firestation) {

        if (!firestationDAO.deleteFirestation(firestation)) {
            throw new DataNotFoundException("La Firestation " + firestation.getAddress() + " numéro " + firestation.getStation() + " n'existe pas. ");
        }
        return true;
    }

    @Override
    public boolean updateFirestation(Firestation firestation) {

        if (!firestationDAO.updateFirestation(firestation)) {
            throw new DataNotFoundException("La Firestation " + firestation.getAddress() + " numéro " + firestation.getStation() + " n'existe pas. ");
        }
        return true;
    }
}
