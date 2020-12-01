package com.openclassrooms.safetyAlerts.Interface;

import com.openclassrooms.safetyAlerts.dto.Fire;

import java.util.Collection;

public interface IFireService {

    Collection<Fire> getFire(String address);
}
