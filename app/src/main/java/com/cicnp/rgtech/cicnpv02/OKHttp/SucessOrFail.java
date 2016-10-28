package com.cicnp.rgtech.cicnpv02.OKHttp;

import org.json.JSONException;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Response;

public interface SucessOrFail {
    public void onResponse(Call call, Response response) throws IOException, JSONException;
    public void onFail(Call call, IOException e);
}
