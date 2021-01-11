package com.openclassrooms.safetyAlerts.serviceInterface;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface IPhoneAlertService {

    Collection<String> getPhoneList(String firestation);
}
