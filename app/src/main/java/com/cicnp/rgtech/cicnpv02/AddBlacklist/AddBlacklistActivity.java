package com.cicnp.rgtech.cicnpv02.AddBlacklist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cicnp.rgtech.cicnpv02.R;

public class AddBlacklistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_blacklist);

        Photo blackListName = new Photo();
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.addBlackList_container, blackListName);
        ft.commit();
    }
}
