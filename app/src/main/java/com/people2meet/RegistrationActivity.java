package com.people2meet;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class RegistrationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private String selected_country_isd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Spinner spinner = (Spinner) findViewById(R.id.country_spinner);

        String[] countries = getResources().getStringArray(R.array.country_array);
        CountryDetail[] countryDetails = new CountryDetail[countries.length];
        int i = 0;
        int currentCountryPosition = 0;
        String currentCountry = TelephonyInfo.getInstance(this).getNetworkCountry().toUpperCase();
        for(String country:countries){
            String[] values = country.split(",");
            String isd = values[0];
            String iso = values[1];
            Locale loc = new Locale("",iso);
            String name = loc.getDisplayCountry();

            CountryDetail countryDetail = new CountryDetail(iso,isd,name);


            if ( iso.equals(currentCountry)){
                currentCountryPosition = i;
            }

            countryDetails[i++] = countryDetail;
        }
        ArrayAdapter<CountryDetail> adapter = new ArrayAdapter<CountryDetail>(
                this,
                android.R.layout.simple_list_item_1,
                countryDetails);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);


        spinner.setSelection(currentCountryPosition);

        spinner.setOnItemSelectedListener(this);

        Button ok = (Button) findViewById(R.id.ok);

        ok.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
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

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)

        CountryDetail countrySelected = (CountryDetail) parent.getItemAtPosition(pos);

        EditText isd = (EditText)findViewById(R.id.isd);

        isd.setText("+" + countrySelected.getIsd());

        isd.setFocusable(false);

        selected_country_isd = countrySelected.getIsd();

    }

    public void onNothingSelected(AdapterView<?> parent) {
        TextView isd = (TextView)findViewById(R.id.isd);

        isd.setText("");
    }

    @Override
    public void onClick(View v) {

        EditText phone = (EditText) findViewById(R.id.phone);

        if (phone.getText() == null || phone.getText().equals("")){
            Toast.makeText(getApplicationContext(),"Please enter valid phone number", Toast.LENGTH_SHORT);
        }else {

            String phoneNo = selected_country_isd + phone.getText();

            Bundle params = new Bundle();
            params.putString("phoneNo", phoneNo);

            Intent i = new Intent(RegistrationActivity.this, RegistrationVerificationActivity.class);
            i.putExtras(params);
            startActivity(i);
            finish();

        }
    }
}
