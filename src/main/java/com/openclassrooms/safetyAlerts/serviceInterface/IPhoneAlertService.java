package com.openclassrooms.safetyAlerts.serviceInterface;


import java.util.Collection;

public interface IPhoneAlertService {

    Collection<String> getPhoneList(String firestation);
}
