package com.cicnp.rgtech.cicnpv02.WelcomeScreen;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cicnp.rgtech.cicnpv02.Notification.FirebaseInstanceIDService;
import com.cicnp.rgtech.cicnpv02.OKHttp.GetDataFromNetwork;
import com.cicnp.rgtech.cicnpv02.OKHttp.NetworkTaskInterface;
import com.cicnp.rgtech.cicnpv02.R;
import com.cicnp.rgtech.cicnpv02.Watch.WatchList.WatchListRecyclerDataWrapper;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeLoginFragment extends Fragment implements View.OnClickListener {

    View view;

    EditText email;
    EditText password;
    Button login;

    public WelcomeLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_welcome_login, container, false);

        email = (EditText) view.findViewById(R.id.welcome_editText_email);
        password = (EditText) view.findViewById(R.id.welcome_editText_password);

        login = (Button) view.findViewById(R.id.welcome_button_login);
        login.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.welcome_button_login:

                //Get data from network
                String reg_url = getString(R.string.url_register_user_token);
                RequestBody registerFormBody = new FormBody.Builder()
                        .add("token", FirebaseInstanceId.getInstance().getToken())
                        .add("org_id","2")
                        .build();

                GetDataFromNetwork getDataFromNetwork = new GetDataFromNetwork(reg_url, registerFormBody, getActivity());
                getDataFromNetwork.setSucessOrFailListener(new NetworkTaskInterface() {
                    @Override
                    public void CallbackMethodForNetworkTask(final String message) {

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(message.toString().equals("success"))
                                {
                                    Toast.makeText(getContext(), "Token success", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                getDataFromNetwork.getData();

                startActivity(new Intent("CICNP.Main"));

                break;

        }
    }
}
