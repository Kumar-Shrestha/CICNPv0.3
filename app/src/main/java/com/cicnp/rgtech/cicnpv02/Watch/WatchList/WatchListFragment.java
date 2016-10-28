package com.cicnp.rgtech.cicnpv02.Watch.WatchList;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cicnp.rgtech.cicnpv02.OKHttp.GetDataFromNetwork;
import com.cicnp.rgtech.cicnpv02.OKHttp.NetworkTask;
import com.cicnp.rgtech.cicnpv02.OKHttp.NetworkTaskInterface;
import com.cicnp.rgtech.cicnpv02.OKHttp.SucessOrFail;
import com.cicnp.rgtech.cicnpv02.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.R.id.message;

/**
 * A simple {@link Fragment} subclass.
 */
public class WatchListFragment extends Fragment {

    View view;

    //Recycler View
    RecyclerView recyclerView;
    WatchListRecyclerAdapter adapter;
    List<WatchListRecyclerDataWrapper> watchList = new ArrayList();

    public WatchListFragment() {
        // Required empty public constructor
        adapter = new WatchListRecyclerAdapter(watchList);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_watch_list, container, false);

        //Define recyclerview
        recyclerView = (RecyclerView) view.findViewById(R.id.watchlist_recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapter);

        String reg_url = "http://192.168.10.76:8080/CICNP/v1/getUserDetail";
        RequestBody registerFormBody = new FormBody.Builder()
                .add("name", "abc")
                .build();

        GetDataFromNetwork getDataFromNetwork = new GetDataFromNetwork(reg_url, registerFormBody, getActivity());
        getDataFromNetwork.setSucessOrFailListener(new NetworkTaskInterface() {
            @Override
            public void CallbackMethodForNetworkTask(final JSONObject message) {

                try {
                    watchList.add(new WatchListRecyclerDataWrapper(message.getString("father_name"), R.drawable.main_background, "Father Name"));
                    watchList.add(new WatchListRecyclerDataWrapper(message.getString("bod"), R.drawable.main_background, "Bod"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        getDataFromNetwork.getData();

        return view;
    }


}
