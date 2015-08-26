package com.people2meet;

import android.os.AsyncTask;
import android.widget.Toast;

import com.textmagic.sms.TextMagicMessageService;
import com.textmagic.sms.exception.ServiceException;

/**
 * Created by tuhinsengupta on 26/08/15.
 */
public class AsyncWaitOnSMS extends AsyncTask<String, Void, String> {

    public Exception getException() {
        return exception;
    }

    private Exception exception;


    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(final String v) {
    }


    protected String doInBackground(String... args) {

        try {
               Thread.sleep(3000);
           } catch (InterruptedException e) {
                e.printStackTrace();
            }

        return "12345";

//        while(true){
//            int wait_time = 0;
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            wait_time++;
//
//            if (wait_time == 300){
//                break;
//            }
//
//            if (SmsListener.receivedCode != null ){
//                break;
//            }
//        }
//
//
//        return SmsListener.receivedCode;

    }
}
