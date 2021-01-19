package com.openclassrooms.safetyAlerts.serviceDAO;

import com.openclassrooms.safetyAlerts.model.Firestation;
import org.springframework.stereotype.Service;


public interface IFirestationService {

    boolean createFirestation(Firestation firestation);
    boolean deleteFirestation(Firestation firestation);
    boolean updateFirestation(Firestation firestation);
}
