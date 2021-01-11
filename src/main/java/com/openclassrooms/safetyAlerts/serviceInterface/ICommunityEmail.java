package com.openclassrooms.safetyAlerts.serviceInterface;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface ICommunityEmail {
    Collection<String> getCommunityEmail(String city);
}
