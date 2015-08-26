package com.people2meet;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by tuhinsengupta on 25/08/15.
 */
public class RegistrationUtils {


    private static Object registrationDetails;

    private static String user_data_file = "USER_DATA";
    private static  String phonePlusImei;

    public static boolean isPhoneRegistered(Context context){

        try {
            FileInputStream fis = context.openFileInput(user_data_file);
            BufferedReader r = new BufferedReader(new InputStreamReader(fis));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            phonePlusImei = total.toString();

            if ( phonePlusImei.length() > 12 ){
                return true;
            }
            else{
                return false;
            }

        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static RegistrationDetails getRegistrationDetails() {
        String[] args = phonePlusImei.split(";");
        return new RegistrationDetails(args[0],args[1]);
    }

    public static void register(Context context,String phoneNo, String imei) {
        try {
        FileOutputStream fos = context.openFileOutput(user_data_file, Context.MODE_PRIVATE);

            fos.write((phoneNo + ";" + imei).getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
