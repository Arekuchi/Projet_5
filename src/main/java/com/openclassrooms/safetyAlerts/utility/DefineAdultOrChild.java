package com.openclassrooms.safetyAlerts.utility;

public class DefineAdultOrChild {

    public static boolean defineAdultOrChild(int age) {

        boolean ageDefined = false;
        int ageToDefine = age;
        if (ageToDefine > 18) {
            ageDefined = true;

        }
        return ageDefined;
    }


}
