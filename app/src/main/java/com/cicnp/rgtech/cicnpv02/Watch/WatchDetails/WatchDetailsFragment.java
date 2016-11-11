package com.cicnp.rgtech.cicnpv02.Watch.WatchDetails;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cicnp.rgtech.cicnpv02.OKHttp.GetDataFromNetwork;
import com.cicnp.rgtech.cicnpv02.OKHttp.NetworkTaskInterface;
import com.cicnp.rgtech.cicnpv02.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class WatchDetailsFragment extends Fragment {

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

        getActivity().setTitle("Watch Details");


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
                .add("criteria", "name")
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






        return view;
    }

}
