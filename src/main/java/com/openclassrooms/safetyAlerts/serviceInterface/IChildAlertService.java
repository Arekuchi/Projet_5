package com.openclassrooms.safetyAlerts.serviceInterface;

import com.openclassrooms.safetyAlerts.dto.ChildAlert;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface IChildAlertService {

    Collection<ChildAlert> getChildAlert(String address);
}
