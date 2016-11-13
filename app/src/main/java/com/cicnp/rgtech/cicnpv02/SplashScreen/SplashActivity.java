package com.cicnp.rgtech.cicnpv02.SplashScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cicnp.rgtech.cicnpv02.R;
import com.cicnp.rgtech.cicnpv02.WelcomeScreen.WelcomeActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
        finish();
    }
}
