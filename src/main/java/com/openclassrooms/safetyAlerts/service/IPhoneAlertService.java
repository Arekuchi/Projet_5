package com.openclassrooms.safetyAlerts.service;

import java.util.Collection;

public interface IPhoneAlertService {
    Collection<String> getPhoneAlert(String firestation);
}
