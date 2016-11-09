package com.cicnp.rgtech.cicnpv02.SearchBlacklist;


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
import com.cicnp.rgtech.cicnpv02.OKHttp.NetworkTaskInterface;
import com.cicnp.rgtech.cicnpv02.R;
import com.cicnp.rgtech.cicnpv02.Watch.WatchList.WatchListRecyclerAdapter;
import com.cicnp.rgtech.cicnpv02.Watch.WatchList.WatchListRecyclerDataWrapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

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




        //Get data from network
        String reg_url = getString(R.string.url_userDetail);
        RequestBody registerFormBody = new FormBody.Builder()
                .add("criteria", SearchBlackListFragment.searchCriteria)
                .add("value", SearchBlackListFragment.searchContent)
                .build();
        GetDataFromNetwork getDataFromNetwork = new GetDataFromNetwork(reg_url, registerFormBody, getActivity());
        getDataFromNetwork.setSucessOrFailListener(new NetworkTaskInterface() {
            @Override
            public void CallbackMethodForNetworkTask(final String message) {
                if(message.equals("failure"))
                {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                // jsonString is a string variable that holds the JSON
                                JSONArray itemArray=new JSONArray(message);
                                for (int i = 0; i < itemArray.length(); i++) {
                                    JSONObject object=itemArray.getJSONObject(i);

                                    searchList.add(new SearchResultRecyclerDataWrapper( object.getString("name") , getString(R.string.url_testImageUrl)));

                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            adapter.notifyDataSetChanged();
                                        }
                                    });

                                }
                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            //JSONObject messageObject = new JSONObject(message);

                        }
                    });
                }
            }
        });

        getDataFromNetwork.getData();

        //searchList.add(new SearchResultRecyclerDataWrapper("Name" , getString(R.string.url_testImageUrl)));

        return view;
    }

}
