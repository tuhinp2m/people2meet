package com.people2meet;
import java.lang.reflect.Method;

import android.content.Context;
import android.telephony.TelephonyManager;

public final class TelephonyInfo {

    private static TelephonyInfo telephonyInfo;
    private String imeiSIM;
    private String networkCountry;

    public String getImeiSIM() {
        return imeiSIM;
    }

    public String getNetworkCountry() {
        return networkCountry;
    }


    private TelephonyInfo() {
    }

    public static TelephonyInfo getInstance(Context context) {

        if (telephonyInfo == null) {

            telephonyInfo = new TelephonyInfo();

            TelephonyManager telephonyManager = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));

            telephonyInfo.imeiSIM = telephonyManager.getDeviceId();

            telephonyInfo.networkCountry = telephonyManager.getNetworkCountryIso();

        }
        return telephonyInfo;
    }
}