package com.cicnp.rgtech.cicnpv02.SearchBlacklist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cicnp.rgtech.cicnpv02.R;
import com.cicnp.rgtech.cicnpv02.Watch.WatchList.WatchListRecyclerAdapter;
import com.cicnp.rgtech.cicnpv02.Watch.WatchList.WatchListRecyclerDataWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultFragment extends Fragment {

    View view;

    //Recycler View
    RecyclerView recyclerView;
    SearchResultRecyclerAdapter adapter;
    List<SearchResultRecyclerDataWrapper> searchList = new ArrayList();

    public SearchResultFragment() {
        // Required empty public constructor
        adapter = new SearchResultRecyclerAdapter(searchList);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search_result, container, false);


        //Define recyclerview
        recyclerView = (RecyclerView) view.findViewById(R.id.searchBlacklist_recyclerView_result);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapter);


        return view;
    }

}
