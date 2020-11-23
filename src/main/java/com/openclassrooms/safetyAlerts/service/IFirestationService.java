package com.openclassrooms.safetyAlerts.service;

import com.openclassrooms.safetyAlerts.dto.FirestationDTO;

import java.util.Collection;

public interface IFirestationService {

    Collection<FirestationDTO> getFirestationDTO(String stationNumber);
}
