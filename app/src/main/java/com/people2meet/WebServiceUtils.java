package com.people2meet;

import android.content.Context;

import com.textmagic.sms.TextMagicMessageService;
import com.textmagic.sms.exception.ServiceException;

import java.util.concurrent.ExecutionException;


/**
 * Created by tuhinsengupta on 26/08/15.
 */
public class WebServiceUtils {


    public static boolean sendRegistrationCode(Context context, String phoneNo,String username,String password) {

        //Demo code - return true now - no integrartio

        return true;

        // Uncomment for actual sms sending
//        SMSAsyncWebService sms = new SMSAsyncWebService();
//
//
//        sms.execute(username,password,phoneNo,"123456");
//
//        try {
//            sms.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            return false;
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//            return false;
//        }
//
//        if ( sms.getException() == null){
//            return true;
//        }else{
//            sms.getException().printStackTrace();
//            return false;
//        }

    }

    
}