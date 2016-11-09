package com.cicnp.rgtech.cicnpv02.OKHttp;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GetDataFromNetwork{

    String url;
    RequestBody body;
    Activity activity;

    NetworkTaskInterface networkTaskInterface;

    public void setSucessOrFailListener(NetworkTaskInterface networkTaskInterface) {
        this.networkTaskInterface = networkTaskInterface;
    }

    public GetDataFromNetwork(String url, RequestBody body, Activity activity)
    {
        this.url =url;
        this.body = body;
        this.activity = activity;
    }

    public void getData()
    {
        try {
            NetworkTask networkTask = new NetworkTask(url, body);

            networkTask.setSucessOrFailListener(new SucessOrFail() {
                @Override
                public void onResponse(Call call, Response response) {

                    try {
                        String responseJSON = response.body().string();
                        Log.d("TAG", responseJSON);
                        if (response.isSuccessful()) {
                            final JSONObject object = new JSONObject(responseJSON);
                            final String message = object.getString("message");

                            networkTaskInterface.CallbackMethodForNetworkTask(message);
                        }
                    } catch (final IOException e) {
                        e.printStackTrace();
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(activity, "Error while uploading information. " , Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(activity, "Error while uploading information. " , Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

                @Override
                public void onFail(Call call, IOException e) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity, "Client side error", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });

            networkTask.postData();
        }
        catch (Exception e)
        {
            Toast.makeText(activity,"Posting data not completed",Toast.LENGTH_SHORT).show();
        }
    }
}
