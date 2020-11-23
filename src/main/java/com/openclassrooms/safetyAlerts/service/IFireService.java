package com.openclassrooms.safetyAlerts.service;

import com.openclassrooms.safetyAlerts.dto.Fire;

import java.util.Collection;

public interface IFireService {

    Collection<Fire> getFire(String address);
}
