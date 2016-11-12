package com.cicnp.rgtech.cicnpv02.SearchBlacklist.SearchDetails;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cicnp.rgtech.cicnpv02.OKHttp.GetDataFromNetwork;
import com.cicnp.rgtech.cicnpv02.OKHttp.NetworkTaskInterface;
import com.cicnp.rgtech.cicnpv02.R;
import com.cicnp.rgtech.cicnpv02.SearchBlacklist.SearchFragment.SearchBlackListFragment;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchDetailsFragment extends Fragment implements View.OnClickListener {

    String uniqueVariable;

    ImageView imageView_photo;

    //TextViews
    TextView textView_name;
    TextView textView_fathersName;
    TextView textView_grandFathersName;
    TextView textView_permanentAddress;
    TextView textView_dateOfBirth;
    TextView textView_contactNo;
    TextView textView_citizenshipNo;
    TextView textView_citizenshipIssuedPlace;
    TextView textView_createdOn;
    TextView textView_uploadedBy;
    String blackId;
    String organizationIdUploadingBlacklist;

    Button watch;
    Button inform;

    //View
    View view;



    public SearchDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search_details, container, false);

        getActivity().setTitle("Search Details");


        uniqueVariable = getArguments().getString("UniqueVariable");



        textView_name = (TextView) view.findViewById(R.id.watchDetails_textView_name);
        textView_fathersName = (TextView) view.findViewById(R.id.watchDetails_textView_fathersName);
        textView_grandFathersName = (TextView) view.findViewById(R.id.watchDetails_textView_grandFathersName);
        textView_permanentAddress = (TextView) view.findViewById(R.id.watchDetails_textView_permanentAddress);
        textView_dateOfBirth = (TextView) view.findViewById(R.id.watchDetails_textView_dateOfBirth);
        textView_contactNo = (TextView) view.findViewById(R.id.watchDetails_textView_contactNo);
        textView_citizenshipNo = (TextView) view.findViewById(R.id.watchDetails_textView_citizenshipNo);
        textView_citizenshipIssuedPlace = (TextView) view.findViewById(R.id.watchDetails_textView_citizenshipIssuedPlace);
        textView_createdOn = (TextView) view.findViewById(R.id.watchDetails_textView_createdOn);
        textView_uploadedBy = (TextView) view.findViewById(R.id.watchDetails_textView_uploadedBy);

        imageView_photo = (ImageView) view.findViewById(R.id.watchDetails_imageView_photo);

        String reg_url = getString(R.string.url_userDetail);
        RequestBody registerFormBody = new FormBody.Builder()
                .add("criteria", "citizen")
                .add("value", uniqueVariable)
                .build();

        GetDataFromNetwork getDataFromNetwork = new GetDataFromNetwork(reg_url, registerFormBody, getActivity());
        getDataFromNetwork.setSucessOrFailListener(new NetworkTaskInterface() {
            @Override
            public void CallbackMethodForNetworkTask(final String message) {
                JSONObject jsonMessage = null;
                try {
                    JSONObject messageObject = new JSONObject(message);

                    for(int i=0; i<messageObject.length(); i++)
                    {
                        final JSONObject object = messageObject.getJSONObject(Integer.toString(i));

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {

                                    textView_name.setText(object.getString("name"));
                                    textView_fathersName.setText(object.getString("father_name"));
                                    textView_grandFathersName.setText(object.getString("grandfather_name"));
                                    textView_permanentAddress.setText(object.getString("permanent_address"));
                                    textView_dateOfBirth.setText(object.getString("bod"));
                                    textView_contactNo.setText(object.getString("contact_no"));
                                    textView_uploadedBy.setText(object.getString("upload_by"));
                                    textView_citizenshipNo.setText(object.getString("citizen_number"));
                                    textView_citizenshipIssuedPlace.setText(object.getString("citizen_issued_place"));
                                    textView_createdOn.setText(object.getString("created_on"));

                                    blackId = object.getString("black_id");
                                    organizationIdUploadingBlacklist = object.getString("organization_id");

                                    Picasso.with(getContext())
                                            .load(getString(R.string.url_localPhoto) + object.getString("photo"))
                                            .placeholder(R.drawable.dot)
                                            .into(imageView_photo);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        getDataFromNetwork.getData();



        watch = (Button) view.findViewById(R.id.searchDetails_button_watch);
        watch.setOnClickListener(this);

        inform = (Button) view.findViewById(R.id.searchDetails_button_inform);
        inform.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(
                getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);

        switch (v.getId())
        {
            case R.id.searchDetails_button_watch:

                SharedPreferences.Editor editor = sharedPreferences.edit();


                boolean watchInstanceFound = false;
                for(int i=1; i<=sharedPreferences.getInt("totalWatch", 0); i++)
                {
                    if(sharedPreferences.getString("watchCitizenshipNo"+Integer.toString(i), "N/A").equals(textView_citizenshipNo.getText().toString()))
                    {
                        watchInstanceFound = true;
                        Toast.makeText(getContext(), "Already added to Watch", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                if(!watchInstanceFound)
                {
                    editor.putInt("totalWatch", sharedPreferences.getInt("totalWatch",0)+1 ).apply();
                    editor.putString("watchCitizenshipNo"+sharedPreferences.getInt("totalWatch", 0), textView_citizenshipNo.getText().toString() ).apply();
                    Toast.makeText(getContext(), "Added to Watch", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.searchDetails_button_inform:


                //Get data from network
                String inform_url = getString(R.string.url_inform);
                RequestBody informFormBody = new FormBody.Builder()
                        .add("message",textView_name.getText().toString()+" has contacted " + sharedPreferences.getString("OrganizationFirstName","N/A"))
                        .add("org_id", organizationIdUploadingBlacklist)
                        .build();
                GetDataFromNetwork getInformDataFromNetwork = new GetDataFromNetwork(inform_url, informFormBody, getActivity());
                getInformDataFromNetwork.setSucessOrFailListener(new NetworkTaskInterface() {
                    @Override
                    public void CallbackMethodForNetworkTask(String message) {

                        if(message.equals("failure"))
                        {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getContext(), "Failed to notify", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else {

                            try {
                                JSONObject messageObject = new JSONObject(message);

                                if (!messageObject.getString("success").equals("0")) {
                                    Toast.makeText(getContext(), "Organization has been notified.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getContext(), "Failed to notify.", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

                getInformDataFromNetwork.getData();


                break;
        }

    }
}
