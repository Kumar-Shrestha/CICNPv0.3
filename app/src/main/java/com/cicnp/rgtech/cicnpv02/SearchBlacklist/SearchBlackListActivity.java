package com.cicnp.rgtech.cicnpv02.SearchBlacklist;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cicnp.rgtech.cicnpv02.R;

public class SearchBlackListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_black_list);

        SearchBlackListFragment searchBlackListFragment = new SearchBlackListFragment();
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.searchBlackList_container, searchBlackListFragment);
        ft.commit();

    }
}
