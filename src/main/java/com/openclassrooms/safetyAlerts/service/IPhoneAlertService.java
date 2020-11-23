package com.openclassrooms.safetyAlerts.service;

import com.openclassrooms.safetyAlerts.dto.PhoneAlert;

import java.util.Collection;

public interface IPhoneAlertService {
    Collection<PhoneAlert> getPhoneAlert(String firestation);

    Collection<String> getPhoneList(String firestation);
}
