package com.cicnp.rgtech.cicnpv02.SearchBlacklist.SearchResult;


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
import com.cicnp.rgtech.cicnpv02.SearchBlacklist.SearchDetails.SearchDetailsFragment;
import com.cicnp.rgtech.cicnpv02.SearchBlacklist.SearchFragment.SearchBlackListFragment;
import com.cicnp.rgtech.cicnpv02.SearchBlacklist.SearchFragment.SearchResultRecyclerAdapter;
import com.cicnp.rgtech.cicnpv02.SearchBlacklist.SearchFragment.SearchResultRecyclerDataWrapper;
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
public class SearchResultFragment extends Fragment implements RecyclerItemClickListener.OnItemClickListener {

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                                JSONObject messageObject = new JSONObject(message);

                                for(int i=0; i<messageObject.length(); i++)
                                {
                                    JSONObject object = messageObject.getJSONObject(Integer.toString(i));
                                    searchList.add(new SearchResultRecyclerDataWrapper(object.getString("name"),
                                            getString(R.string.url_localPhoto)+ object.getString("photo")));
                                }
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        adapter.notifyDataSetChanged();
                                    }
                                });

                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }


                        }
                    });
                }
            }
        });

        getDataFromNetwork.getData();


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
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, this));




        return view;
    }

    @Override
    public void onItemClick(View view, int position) {
        SearchDetailsFragment searchDetailsFragment = new SearchDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("UniqueVariable", searchList.get(position).name);
        searchDetailsFragment.setArguments(bundle);
        android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.searchBlackList_container, searchDetailsFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onLongItemClick(View view, int position) {

    }
}
