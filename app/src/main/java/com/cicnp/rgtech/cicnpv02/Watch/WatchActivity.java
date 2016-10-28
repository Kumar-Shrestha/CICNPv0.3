package com.cicnp.rgtech.cicnpv02.Watch;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cicnp.rgtech.cicnpv02.R;
import com.cicnp.rgtech.cicnpv02.Watch.WatchDetails.WatchDetailsFragment;
import com.cicnp.rgtech.cicnpv02.Watch.WatchList.WatchListFragment;

public class WatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch);

        WatchListFragment watchListFragment = new WatchListFragment();
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.watch_container, watchListFragment);
        ft.commit();
    }
}
