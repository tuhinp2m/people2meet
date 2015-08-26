package com.people2meet;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.textmagic.sms.TextMagicMessageService;
import com.textmagic.sms.exception.ServiceException;

/**
 * Created by tuhinsengupta on 26/08/15.
 */
public class SMSAsyncWebService extends AsyncTask<String, Void, Void> {

    public Exception getException() {
        return exception;
    }

    private Exception exception;


    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(final Void v) {
    }


    protected Void doInBackground(String... args) {

            TextMagicMessageService service =
                    new TextMagicMessageService(args[0], args[1]);
            try {
                service.send("People2Meet verification code " + args[3], args[2]);
            } catch (ServiceException ex) {
                exception = ex;
            }
        return null;
    }
}
