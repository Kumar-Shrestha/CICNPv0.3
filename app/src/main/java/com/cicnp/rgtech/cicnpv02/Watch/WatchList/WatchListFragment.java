package com.cicnp.rgtech.cicnpv02.Watch.WatchList;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cicnp.rgtech.cicnpv02.OKHttp.GetDataFromNetwork;
import com.cicnp.rgtech.cicnpv02.OKHttp.NetworkTaskInterface;
import com.cicnp.rgtech.cicnpv02.R;
import com.cicnp.rgtech.cicnpv02.RecyclerView.RecyclerItemClickListener;
import com.cicnp.rgtech.cicnpv02.Watch.WatchDetails.WatchDetailsFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class WatchListFragment extends Fragment implements RecyclerItemClickListener.OnItemClickListener {

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get shared preferences data from shared preferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        final List<String> watchCitizenshipNoList = new ArrayList<>();

        if(sharedPreferences.getInt("totalWatch", 0) == 0)
        {
            Toast.makeText(getContext(), "No Watch", Toast.LENGTH_SHORT).show();
        }

        for(int i=0; i<sharedPreferences.getInt("totalWatch", 0); i++)
        {
            watchCitizenshipNoList.add(sharedPreferences.getString("watchCitizenshipNo"+Integer.toString(i+1), "N/A"));


            //Get data from network
            String reg_url = getString(R.string.url_userDetail);
            RequestBody registerFormBody = new FormBody.Builder()
                    .add("criteria","citizen")
                    .add("value", watchCitizenshipNoList.get(i))
                    .build();
            GetDataFromNetwork getDataFromNetwork = new GetDataFromNetwork(reg_url, registerFormBody, getActivity());
            getDataFromNetwork.setSucessOrFailListener(new NetworkTaskInterface() {
                @Override
                public void CallbackMethodForNetworkTask(String message) {
                    try {
                        JSONObject messageObject = new JSONObject(message);

                        for(int i=0; i<messageObject.length(); i++)
                        {
                            JSONObject object = messageObject.getJSONObject(Integer.toString(i));
                            watchList.add(new WatchListRecyclerDataWrapper(object.getString("name"),
                                    getString(R.string.url_localPhoto) + object.getString("photo"),
                                    object.getString("contact_no"),
                                    watchCitizenshipNoList.get(i)));
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            getDataFromNetwork.getData();
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_watch_list, container, false);

        getActivity().setTitle("Watch List");



        //Define recyclerview
        recyclerView = (RecyclerView) view.findViewById(R.id.watchlist_recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapter);

        //Recycler view click listener
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, this));

        return view;
    }


    @Override
    public void onItemClick(View view, int position) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);

        WatchDetailsFragment watchDetailsFragment = new WatchDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("UniqueVariable", sharedPreferences.getString("watchCitizenshipNo"+Integer.toString(position+1), "N/A"));
        watchDetailsFragment.setArguments(bundle);
        android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.watch_container, watchDetailsFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onLongItemClick(View view, int position) {

    }
}
