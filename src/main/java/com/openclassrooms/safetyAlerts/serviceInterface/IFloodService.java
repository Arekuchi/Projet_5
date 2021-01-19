package com.openclassrooms.safetyAlerts.serviceInterface;

import com.openclassrooms.safetyAlerts.dto.Flood;

import java.util.Collection;
import java.util.List;

public interface IFloodService {

    Collection<Flood> getFlood(List<String> stationNumber);
}
