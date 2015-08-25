package com.people2meet;

/**
 * Created by tuhinsengupta on 25/08/15.
 */
public class RegistrationUtils {


    private static Object registrationDetails;

    public static boolean isPhoneRegistered(){
        return false;
    }

    public static RegistrationDetails getRegistrationDetails() {
        return new RegistrationDetails("phoneno","imei");
    }
}
