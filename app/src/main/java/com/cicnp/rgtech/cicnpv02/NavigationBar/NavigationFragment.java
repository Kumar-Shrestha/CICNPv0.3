package com.cicnp.rgtech.cicnpv02.NavigationBar;


import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.cicnp.rgtech.cicnpv02.AddBlacklist.AddBlacklistActivity;
import com.cicnp.rgtech.cicnpv02.OKHttp.GetDataFromNetwork;
import com.cicnp.rgtech.cicnpv02.OKHttp.NetworkTaskInterface;
import com.cicnp.rgtech.cicnpv02.OrganizationProfile.OrganizationProfile;
import com.cicnp.rgtech.cicnpv02.R;
import com.cicnp.rgtech.cicnpv02.RecyclerView.RecyclerItemClickListener;
import com.cicnp.rgtech.cicnpv02.SearchBlacklist.SearchBlackListActivity;
import com.cicnp.rgtech.cicnpv02.Watch.WatchList.WatchListRecyclerDataWrapper;
import com.cicnp.rgtech.cicnpv02.WelcomeScreen.WelcomeActivity;
import com.google.firebase.iid.FirebaseInstanceId;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends Fragment implements RecyclerItemClickListener.OnItemClickListener, View.OnClickListener {

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


        //Update Username
        userName.setText(getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), MODE_PRIVATE)
                .getString("OrganizationFirstName", "N/A"));


        //Update profile picture
        String photoName = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), MODE_PRIVATE)
                .getString("OrganizationPhoto", null);
        Picasso.with(getContext())
                .load(getString(R.string.url_localPhoto)+ photoName)
                .placeholder(R.drawable.dot)
                .into(profilePic);


        //Logout click listener
        logout.setOnClickListener(this);

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

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, this));

        return view;
    }

    @Override
    public void onItemClick(View view, int position) {

        switch(position)
        {
            case 0:
                startActivity( new Intent(getContext(), OrganizationProfile.class));
                break;

            case 1:
                startActivity(new Intent(getContext(), AddBlacklistActivity.class));
                break;

            case 2:
                startActivity(new Intent(getContext(), SearchBlackListActivity.class));
                break;
        }

    }

    @Override
    public void onLongItemClick(View view, int position) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.nav_button_logout:

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("LoggedIn", false).apply();

                //Remove token organization link
                //Get data from network
                String removeTokenURL = getString(R.string.url_removeToken);
                RequestBody removeTokenFormBody = new FormBody.Builder()
                        .add("token", FirebaseInstanceId.getInstance().getToken())
                        .build();
                GetDataFromNetwork getDataFromNetwork = new GetDataFromNetwork(removeTokenURL, removeTokenFormBody, getActivity());
                getDataFromNetwork.setSucessOrFailListener(new NetworkTaskInterface() {
                    @Override
                    public void CallbackMethodForNetworkTask(String message) {
                        if(message.equals("success"))
                        {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getContext(), "Token link removed.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else
                        {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getContext(), "Token link removal unsuccessful.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });

                getDataFromNetwork.getData();


                startActivity(new Intent(getContext(), WelcomeActivity.class));

                break;
        }
    }
}
