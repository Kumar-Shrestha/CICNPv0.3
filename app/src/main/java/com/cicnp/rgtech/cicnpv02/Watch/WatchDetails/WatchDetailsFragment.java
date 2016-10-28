package com.cicnp.rgtech.cicnpv02.Watch.WatchDetails;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.cicnp.rgtech.cicnpv02.OKHttp.NetworkTask;
import com.cicnp.rgtech.cicnpv02.OKHttp.SucessOrFail;
import com.cicnp.rgtech.cicnpv02.R;

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
                try {
                    String reg_url = "http://192.168.10.76:8080/CICNP/v1/getUserDetail";
                    RequestBody registerFormBody = new FormBody.Builder()
                            .add("name", "abc")
                            .build();

                    NetworkTask registerTask = new NetworkTask(reg_url, registerFormBody);

                    registerTask.setSucessOrFailListener(new SucessOrFail() {
                        @Override
                        public void onResponse(Call call, Response response) {

                            try {
                                String responseJSON = response.body().string();
                                Log.d("TAG", responseJSON);
                                if (response.isSuccessful()) {
                                    JSONObject object = new JSONObject(responseJSON);
                                    final String message = object.getString("message");
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getContext(), message , Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            } catch (final IOException e) {
                                e.printStackTrace();
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getContext(), "Error while uploading information. Error: " , Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } catch (JSONException e) {
                                e.printStackTrace();
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getContext(), "Error while uploading information. Error: " , Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFail(Call call, IOException e) {
                            //TODO:handle client side failure
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getContext(), "Client side error", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    });

                    registerTask.postData();
                }
                catch (Exception e)
                {
                    Toast.makeText(getContext(),"Posting data not completed",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

}
