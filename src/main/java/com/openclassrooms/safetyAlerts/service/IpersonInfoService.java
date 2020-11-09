package com.openclassrooms.safetyAlerts.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface IpersonInfoService {
    Collection<String> getPersonInfo(String birthdate);

}
