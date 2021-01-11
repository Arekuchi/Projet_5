package com.openclassrooms.safetyAlerts.serviceInterface;

import com.openclassrooms.safetyAlerts.dto.Flood;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface IFloodService {

    Collection<Flood> getFlood(List<String> stationNumber);
}
