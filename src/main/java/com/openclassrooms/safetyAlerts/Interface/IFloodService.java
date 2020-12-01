package com.openclassrooms.safetyAlerts.Interface;

import com.openclassrooms.safetyAlerts.dto.Flood;

import java.util.Collection;
import java.util.List;

public interface IFloodService {

    Collection<Flood> getFlood(List<String> stationNumber);
}
