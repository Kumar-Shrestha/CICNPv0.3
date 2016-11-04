package com.cicnp.rgtech.cicnpv02.Watch.WatchList;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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




        //Get data from network
        String reg_url = getString(R.string.url_userDetail);
        RequestBody registerFormBody = new FormBody.Builder()
                .add("name", "abc")
                .build();
        GetDataFromNetwork getDataFromNetwork = new GetDataFromNetwork(reg_url, registerFormBody, getActivity());
        getDataFromNetwork.setSucessOrFailListener(new NetworkTaskInterface() {
            @Override
            public void CallbackMethodForNetworkTask(final JSONObject message) {

                try {
                    watchList.add(new WatchListRecyclerDataWrapper(message.getString("father_name"), R.drawable.main_background, "Father Name", "abc"));
                    watchList.add(new WatchListRecyclerDataWrapper(message.getString("bod"), R.drawable.main_background, "Bod", "abc"));
                    watchList.add(new WatchListRecyclerDataWrapper(message.getString("father_name"), R.drawable.main_background, "Father Name", "abc"));
                    watchList.add(new WatchListRecyclerDataWrapper(message.getString("bod"), R.drawable.main_background, "Bod", "abc"));
                    watchList.add(new WatchListRecyclerDataWrapper(message.getString("father_name"), R.drawable.main_background, "Father Name", "abc"));
                    watchList.add(new WatchListRecyclerDataWrapper(message.getString("bod"), R.drawable.main_background, "Bod", "abc"));
                    watchList.add(new WatchListRecyclerDataWrapper(message.getString("father_name"), R.drawable.main_background, "Father Name", "abc"));
                    watchList.add(new WatchListRecyclerDataWrapper(message.getString("bod"), R.drawable.main_background, "Bod", "abc"));
                    watchList.add(new WatchListRecyclerDataWrapper(message.getString("father_name"), R.drawable.main_background, "Father Name", "abc"));
                    watchList.add(new WatchListRecyclerDataWrapper(message.getString("bod"), R.drawable.main_background, "Bod", "abc"));

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



        //Recycler view click listener
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, this));

        return view;
    }


    @Override
    public void onItemClick(View view, int position) {
        WatchDetailsFragment watchDetailsFragment = new WatchDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("UniqueVariable", watchList.get(position).uniqueVariable);
        watchDetailsFragment.setArguments(bundle);
        android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.watch_container, watchDetailsFragment);
        ft.commit();
    }

    @Override
    public void onLongItemClick(View view, int position) {

    }
}
