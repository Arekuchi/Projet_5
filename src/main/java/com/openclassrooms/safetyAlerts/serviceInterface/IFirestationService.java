package com.openclassrooms.safetyAlerts.serviceInterface;

import com.openclassrooms.safetyAlerts.dto.FirestationDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface IFirestationService {

    Collection<FirestationDTO> getFirestationDTO(String stationNumber);
}
