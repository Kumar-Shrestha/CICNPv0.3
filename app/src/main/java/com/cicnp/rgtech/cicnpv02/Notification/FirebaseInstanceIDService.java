package com.cicnp.rgtech.cicnpv02.Notification;

import android.util.Log;

import com.cicnp.rgtech.cicnpv02.R;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {


    private static final String TAG = "FirebaseInstanceIDSrvc";


    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, token);
        registerToken(token,"2");
    }

    private void registerToken(String token, String organizationId) {

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("token", token)
                .add("org_id",organizationId)
                .build();

        Request request = new Request.Builder()
                .url(getResources().getString(R.string.url_register_user_token))
                .post(body)
                .build();

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
