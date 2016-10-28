package com.cicnp.rgtech.cicnpv02.OKHttp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.cicnp.rgtech.cicnpv02.R;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkTask {

    private static final String TAG = "NetworkTask";

    private SucessOrFail mSucessOrFail;
    public String mUrl;
    public RequestBody mPostBody;

    public NetworkTask(String url, RequestBody postBody) {
        mUrl = url;
        mPostBody = postBody;
    }

    public void setSucessOrFailListener(SucessOrFail mSucessOrFail) {
        this.mSucessOrFail = mSucessOrFail;
    }

    public void postData() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(mUrl)
                .post(mPostBody)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mSucessOrFail.onFail(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    mSucessOrFail.onResponse(call, response);
                } catch (JSONException e) {
                    Log.e(TAG, e+"");
                }
            }
        });
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        } else {
            Toast.makeText(context, R.string.network_unavailable_message
                    , Toast.LENGTH_LONG).show();
        }

        return isAvailable;
    }


}


