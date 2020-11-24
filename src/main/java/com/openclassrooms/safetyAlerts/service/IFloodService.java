package com.openclassrooms.safetyAlerts.service;

import com.openclassrooms.safetyAlerts.dto.Flood;

import java.util.Collection;

public interface IFloodService {

    Collection<Flood> getFlood(String stationNumber);
}
