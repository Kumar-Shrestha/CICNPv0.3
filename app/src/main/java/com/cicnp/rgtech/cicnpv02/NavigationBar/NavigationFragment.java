package com.cicnp.rgtech.cicnpv02.NavigationBar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cicnp.rgtech.cicnpv02.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends Fragment {

    //Main View
    View view;

    //UserInfo
    ImageView profilePic;
    TextView userName;

    //Logout
    Button logout;

    //Recycler View
    RecyclerView recyclerView;
    NavigationRecyclerAdapter adapter;
    List<NavigationRecyclerDataWrapper> menuList = new ArrayList();

    public NavigationFragment() {
        adapter = new NavigationRecyclerAdapter(menuList);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_navigation, container, false);

        //Getting reference of variables
        profilePic = (ImageView) view.findViewById(R.id.nav_imageView_profilePic);
        userName = (TextView) view.findViewById(R.id.nav_textView_userName);
        logout = (Button) view.findViewById(R.id.nav_button_logout);
        recyclerView = (RecyclerView) view.findViewById(R.id.nav_recyclerView);

        //Get data from string.xml
        String[] titleList = getResources().getStringArray(R.array.nav_recyclerView_title);
        String[] imageList = getResources().getStringArray(R.array.nav_recyclerView_images);
        for (int i=0; i<titleList.length; i++) {
            menuList.add(new NavigationRecyclerDataWrapper(titleList[i], imageList[i]));
        }

        //Define recyclerview
        recyclerView = (RecyclerView) view.findViewById(R.id.nav_recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapter);

        return view;
    }

}
