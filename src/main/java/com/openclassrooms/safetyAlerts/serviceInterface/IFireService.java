package com.openclassrooms.safetyAlerts.serviceInterface;

import com.openclassrooms.safetyAlerts.dto.Fire;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface IFireService {

    Collection<Fire> getFire(String address);
}
