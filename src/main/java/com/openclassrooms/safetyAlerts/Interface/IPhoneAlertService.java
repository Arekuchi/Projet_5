package com.openclassrooms.safetyAlerts.Interface;

import com.openclassrooms.safetyAlerts.dto.PhoneAlert;

import java.util.Collection;

public interface IPhoneAlertService {

    Collection<String> getPhoneList(String firestation);
}
