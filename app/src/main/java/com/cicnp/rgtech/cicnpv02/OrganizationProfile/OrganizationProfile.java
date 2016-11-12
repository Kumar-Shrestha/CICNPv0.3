package com.cicnp.rgtech.cicnpv02.OrganizationProfile;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.cicnp.rgtech.cicnpv02.R;

public class OrganizationProfile extends AppCompatActivity {


    TextView name;
    TextView address;
    TextView contactNo;
    TextView active;
    TextView userType;
    TextView expiryDate;
    TextView registrationDate;
    TextView registrationNumber;
    TextView id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_profile);

        setTitle("Organization Profile");

        name = (TextView) findViewById(R.id.profile_textView_name);
        address = (TextView) findViewById(R.id.profile_textView_address);
        contactNo = (TextView) findViewById(R.id.profile_textView_contactNo);
        active = (TextView) findViewById(R.id.profile_textView_active);
        userType = (TextView) findViewById(R.id.profile_textView_userType);
        expiryDate = (TextView) findViewById(R.id.profile_textView_expiryDate);
        registrationDate = (TextView) findViewById(R.id.profile_textView_registrationDate);
        registrationNumber = (TextView) findViewById(R.id.profile_textView_registrationNumber);
        id = (TextView) findViewById(R.id.profile_textView_organizationID);

        setValues();
    }

    private void setValues() {

        SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.shared_preferences_key), MODE_PRIVATE);

        name.setText(sharedPreferences.getString("OrganizationFirstName", "N/A"));
        address.setText(sharedPreferences.getString("OrganizationAddress", "N/A"));
        contactNo.setText(sharedPreferences.getString("OrganizationContactNumber", "N/A"));
        active.setText(sharedPreferences.getString("OrganizationActive", "N/A"));
        userType.setText(sharedPreferences.getString("OrganizationUserType", "N/A"));
        expiryDate.setText(sharedPreferences.getString("OrganizationExpiredDate", "N/A"));
        registrationDate.setText(sharedPreferences.getString("OrganizationRegistrationDate", "N/A"));
        registrationNumber.setText(sharedPreferences.getString("OrganizationRegistrationNumber", "N/A"));
        id.setText(sharedPreferences.getString("OrganizationID", "N/A"));

    }




}
