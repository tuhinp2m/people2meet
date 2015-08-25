package com.people2meet;

/**
 * Created by tuhinsengupta on 25/08/15.
 */
public class RegistrationDetails {
    String phoneNo;
    String imeiNo;

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getImeiNo() {
        return imeiNo;
    }

    public RegistrationDetails(String phoneNo, String imeiNo) {
        this.phoneNo = phoneNo;
        this.imeiNo = imeiNo;
    }
}
