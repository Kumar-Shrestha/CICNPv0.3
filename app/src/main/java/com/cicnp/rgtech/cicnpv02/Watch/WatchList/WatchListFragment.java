package com.cicnp.rgtech.cicnpv02.Watch.WatchList;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cicnp.rgtech.cicnpv02.R;

import java.util.ArrayList;
import java.util.List;

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

        watchList.add(new WatchListRecyclerDataWrapper("Kumar", R.drawable.search, "High"));
        watchList.add(new WatchListRecyclerDataWrapper("Kumar", R.drawable.search, "High"));


        return view;
    }

}
