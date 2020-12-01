package com.openclassrooms.safetyAlerts.Interface;

import com.openclassrooms.safetyAlerts.dto.FirestationDTO;

import java.util.Collection;

public interface IFirestationService {

    Collection<FirestationDTO> getFirestationDTO(String stationNumber);
}
