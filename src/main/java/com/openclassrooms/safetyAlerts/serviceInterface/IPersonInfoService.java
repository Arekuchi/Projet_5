package com.openclassrooms.safetyAlerts.serviceInterface;

import com.openclassrooms.safetyAlerts.dto.PersonInfo;

import java.util.Collection;

public interface IPersonInfoService {

    Collection<PersonInfo> getPersonInfo(String lastname, String firstname);

}
