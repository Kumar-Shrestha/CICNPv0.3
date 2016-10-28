package com.cicnp.rgtech.cicnpv02.Watch.WatchDetails;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.cicnp.rgtech.cicnpv02.OKHttp.GetDataFromNetwork;
import com.cicnp.rgtech.cicnpv02.OKHttp.NetworkTask;
import com.cicnp.rgtech.cicnpv02.OKHttp.NetworkTaskInterface;
import com.cicnp.rgtech.cicnpv02.OKHttp.SucessOrFail;
import com.cicnp.rgtech.cicnpv02.R;
import com.cicnp.rgtech.cicnpv02.Watch.WatchList.WatchListRecyclerDataWrapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class WatchDetailsFragment extends Fragment {

    Button button;

    //View
    View view;

    public WatchDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_watch_details, container, false);

        button = (Button) view.findViewById(R.id.watchdetials_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String reg_url = "http://192.168.10.76:8080/CICNP/v1/getUserDetail";
                RequestBody registerFormBody = new FormBody.Builder()
                        .add("name", "abc")
                        .build();

                GetDataFromNetwork getDataFromNetwork = new GetDataFromNetwork(reg_url, registerFormBody, getActivity());
                getDataFromNetwork.setSucessOrFailListener(new NetworkTaskInterface() {
                    @Override
                    public void CallbackMethodForNetworkTask(final JSONObject message) {

                        try {
                            Toast.makeText(getActivity(), message.getString("father_name"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
                getDataFromNetwork.getData();

            }
        });
        return view;
    }

}
