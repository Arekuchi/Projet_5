package com.openclassrooms.safetyAlerts.serviceInterface;

import com.openclassrooms.safetyAlerts.dto.PersonInfo;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface IpersonInfoService {

    Collection<PersonInfo> getPersonInfo(String lastname, String firstname);

}
