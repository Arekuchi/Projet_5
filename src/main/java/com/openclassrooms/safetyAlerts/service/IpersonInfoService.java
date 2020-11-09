package com.openclassrooms.safetyAlerts.service;

import com.openclassrooms.safetyAlerts.dto.PersonInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface IpersonInfoService {
    Collection<String> getPersonInfo(String birthdate);

    Collection<PersonInfo> getPersonInfo2(String lastname, String firstname);
}
