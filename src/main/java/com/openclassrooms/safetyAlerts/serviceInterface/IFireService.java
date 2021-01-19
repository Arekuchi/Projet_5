package com.openclassrooms.safetyAlerts.serviceInterface;

import com.openclassrooms.safetyAlerts.dto.Fire;

import java.util.Collection;

public interface IFireService {

    Collection<Fire> getFire(String address);
}
