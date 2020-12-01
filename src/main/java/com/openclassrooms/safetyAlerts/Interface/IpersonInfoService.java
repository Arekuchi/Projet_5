package com.openclassrooms.safetyAlerts.Interface;

import com.openclassrooms.safetyAlerts.dto.PersonInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface IpersonInfoService {

    Collection<PersonInfo> getPersonInfo(String lastname, String firstname);

}
