package com.openclassrooms.safetyAlerts.service;

import java.util.Collection;

public interface ICommunityEmail {
    Collection<String> getCommunityEmail(String city);
}
