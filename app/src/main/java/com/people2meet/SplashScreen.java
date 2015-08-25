package com.people2meet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;

/**
 * Created by tuhinsengupta on 25/08/15.
 */
public class SplashScreen extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        if (RegistrationUtils.isPhoneRegistered()){
            UserDetails userDetails = AuthenticationUtils.login(RegistrationUtils.getRegistrationDetails());

            Intent i = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(i);

        }else{
            Intent i = new Intent(SplashScreen.this, AcceptTermActivity.class);
            startActivity(i);
        }

        TelephonyInfo telephonyInfo = TelephonyInfo.getInstance(this);

        String imeiSIM = telephonyInfo.getImeiSIM();
        String country = telephonyInfo.getNetworkCountry();


        Log.i("Dual = ", " IME : " + imeiSIM + "\n" +
                        " Country : " + country + "\n");


        // close this activity
        finish();
    }
}
