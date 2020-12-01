package com.openclassrooms.safetyAlerts.service;

import com.openclassrooms.safetyAlerts.dto.Flood;

import java.util.Collection;
import java.util.List;

public interface IFloodService {

    Collection<Flood> getFlood(List<String> stationNumber);
}
