package com.openclassrooms.safetyAlerts.dao;

import com.openclassrooms.safetyAlerts.model.Firestation;


public interface IFirestationDAO {

    boolean createFirestation(Firestation firestation);
    boolean deleteFirestation(Firestation firestation);
    boolean updateFirestation(Firestation firestation);
}
