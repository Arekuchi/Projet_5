package com.openclassrooms.safetyAlerts.serviceInterface;

import com.openclassrooms.safetyAlerts.dto.ChildAlert;

import java.util.Collection;

public interface IChildAlertService {

    Collection<ChildAlert> getChildAlert(String address);
}
