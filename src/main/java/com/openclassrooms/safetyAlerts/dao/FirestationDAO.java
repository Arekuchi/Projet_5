package com.openclassrooms.safetyAlerts.dao;

import com.openclassrooms.safetyAlerts.model.Firestation;
import com.openclassrooms.safetyAlerts.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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


}
