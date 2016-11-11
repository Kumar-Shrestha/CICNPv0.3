package com.cicnp.rgtech.cicnpv02.WelcomeScreen;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cicnp.rgtech.cicnpv02.MainActivity;
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

import static android.content.Context.MODE_PRIVATE;

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

                //Register token
                String reg_url = getString(R.string.url_register_user_token);
                RequestBody registerTokenBody = new FormBody.Builder()
                        .add("token", FirebaseInstanceId.getInstance().getToken())
                        .add("org_id","2")
                        .build();

                GetDataFromNetwork getDataFromNetwork = new GetDataFromNetwork(reg_url, registerTokenBody, getActivity());
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
                                else
                                {
                                    Toast.makeText(getContext(), "Token already registered", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                getDataFromNetwork.getData();


                //Login
                String login_url = getString(R.string.url_login);
                RequestBody loginBody = new FormBody.Builder()
                        .add("email", email.getText().toString())
                        .add("password",password.getText().toString())
                        .build();

                GetDataFromNetwork getLoginRequestFromNetwork = new GetDataFromNetwork(login_url, loginBody, getActivity());
                getLoginRequestFromNetwork.setSucessOrFailListener(new NetworkTaskInterface() {
                    @Override
                    public void CallbackMethodForNetworkTask(final String message) {

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(message.toString().equals("failure"))
                                {
                                    Toast.makeText(getContext(), "Login Failed. Please Retry.", Toast.LENGTH_SHORT).show();
                                }
                                //Organization details received
                                else
                                {
                                    //Save logged in organization's information
                                    try {
                                        JSONObject messageObject = new JSONObject(message);

                                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();

                                        //TODO: Set values according to message JSON
                                        editor.putBoolean("LoggedIn", true).apply();
                                        editor.putString("OrganizationFirstName", messageObject.getString("name")).apply();
                                        editor.putString("OrganizationMiddleName", messageObject.getString("middlename")).apply();
                                        editor.putString("OrganizationLastName", messageObject.getString("lastname")).apply();
                                        editor.putString("OrganizationAddress", messageObject.getString("address")).apply();
                                        editor.putString("OrganizationContactNumber", messageObject.getString("contact_no")).apply();
                                        editor.putString("OrganizationActive", messageObject.getString("active")).apply();
                                        editor.putString("OrganizationContactPerson", messageObject.getString("contact_person")).apply();
                                        editor.putString("OrganizationUserType", messageObject.getString("usertype")).apply();
                                        editor.putString("OrganizationPurpose", messageObject.getString("purpose")).apply();
                                        editor.putString("OrganizationExpiredDate", messageObject.getString("expired_date")).apply();
                                        editor.putString("OrganizationPhoto", messageObject.getString("photo")).apply();
                                        editor.putString("OrganizationRegistrationDate", messageObject.getString("reg_date")).apply();
                                        editor.putString("OrganizationRegistrationNumber", messageObject.getString("reg_no")).apply();

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    startActivity(new Intent(getContext(), MainActivity.class));

                                }
                            }
                        });
                    }
                });
                getLoginRequestFromNetwork.getData();



                break;

        }
    }
}
