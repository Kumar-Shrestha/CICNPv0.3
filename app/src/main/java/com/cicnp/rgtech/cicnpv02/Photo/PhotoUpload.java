package com.cicnp.rgtech.cicnpv02.Photo;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.cicnp.rgtech.cicnpv02.OKHttp.NetworkTask;
import com.cicnp.rgtech.cicnpv02.OKHttp.SucessOrFail;
import com.cicnp.rgtech.cicnpv02.R;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;

import static okhttp3.RequestBody.create;

public class PhotoUpload {

    public Activity activity;
    NetworkTask netTask;
    SucessOrFail mSucessOrFail;

    public PhotoUpload(Activity activity)
    {
        this.activity = activity;
    }

    public void setSucessOrFailListener(SucessOrFail sucessOrFail){
        mSucessOrFail = sucessOrFail;
    }

    public void uploadImage(String sourceImageFile, String imageName) {

        try {
            File sourceFile = new File(sourceImageFile);

            final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/*");

            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("fileToUpload", imageName+".jpeg", create(MEDIA_TYPE_PNG, sourceFile))
                    .addFormDataPart("result", "my_image")
                    .build();

            netTask = new NetworkTask(activity.getResources().getString(R.string.url_photo), requestBody);

            netTask.setSucessOrFailListener(new SucessOrFail() {
                @Override
                public void onResponse(Call call, Response response) throws IOException, JSONException {
                    mSucessOrFail.onResponse(call, response);
                }

                @Override
                public void onFail(Call call, IOException e) {
                    mSucessOrFail.onFail(call, e);
                }
            });

            netTask.postData();


        } catch (Exception e) {
            Toast.makeText(activity.getApplicationContext(), "Please choose photo", Toast.LENGTH_SHORT).show();
        }
    }


}
