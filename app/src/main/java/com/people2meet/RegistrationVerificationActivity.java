package com.people2meet;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RegistrationVerificationActivity extends AppCompatActivity {

    private SmsListener listener = new SmsListener();
    private String phoneNo;
    private String imei;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_verification);


        phoneNo = getIntent().getExtras().getString("phoneNo");
        imei = TelephonyInfo.getInstance(this).getImeiSIM();

        //this.registerReceiver(listener, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));


        boolean smsSendResult = WebServiceUtils.sendRegistrationCode(getApplicationContext(), phoneNo, getString(R.string.sms_gateway_username), getString(R.string.sms_gateway_password));


        if (smsSendResult) {


           waitOnSMS();


        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "SMS sent failed.", Toast.LENGTH_SHORT);
            toast.show();

            Intent i = new Intent(RegistrationVerificationActivity.this, RegistrationActivity.class);
            startActivity(i);
        }



    }

    private void waitOnSMS() {


        final AsyncWaitOnSMS waitOnSMS = new AsyncWaitOnSMS();

        waitOnSMS.execute();

        final Handler mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                String receivedCode = message.getData().getString("code");
                if (receivedCode != null) {

                    Toast.makeText(getApplicationContext(), "code = " + receivedCode, Toast.LENGTH_SHORT);

                    RegistrationUtils.register(RegistrationVerificationActivity.this, phoneNo,imei);

                    Intent i = new Intent(RegistrationVerificationActivity.this, SplashScreen.class);
                    startActivity(i);
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "No SMS detected in 5 minutes", Toast.LENGTH_SHORT);

                    Intent i = new Intent(RegistrationVerificationActivity.this, RegistrationActivity.class);
                    startActivity(i);
                    finish();

                }

            }
        };

        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... params) {
                String receivedCode = null;
                try {
                    receivedCode = waitOnSMS.get(5, TimeUnit.MINUTES);



                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }

                Message message = mHandler.obtainMessage();
                Bundle b = new Bundle();
                b.putString("code", receivedCode);
                message.setData(b);
                message.sendToTarget();


                //RegistrationVerificationActivity.this.unregisterReceiver(listener);

                return null;
            }
        }.execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration_verification, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
