package com.cicnp.rgtech.cicnpv02.AddBlacklist;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cicnp.rgtech.cicnpv02.OKHttp.SucessOrFail;
import com.cicnp.rgtech.cicnpv02.Photo.PhotoUpload;
import com.cicnp.rgtech.cicnpv02.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Photo extends Fragment {

    View view;

    PhotoUpload photoUpload;
    Bitmap selectedImage = null;
    public static String imagePath;
    ImageView photo;

    public Photo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_photo, container, false);

        photo = (ImageView) view.findViewById(R.id.addBlacklist_imageView_photo);

        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck == PackageManager.PERMISSION_DENIED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_CONTACTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        101);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }


        }


        //Open Gallery
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Bring up gallery to select a photo
            startActivityForResult(intent, 1010);
        }


        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null && resultCode == getActivity().RESULT_OK) {
            Uri photoUri = data.getData();

            try {
                selectedImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), photoUri);

                //Saving to external storage
                File file;
                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                selectedImage.compress(Bitmap.CompressFormat.JPEG, 60, bytearrayoutputstream);

                String state = Environment.getExternalStorageState();
                if (Environment.MEDIA_MOUNTED.equals(state)) {
                    file = new File(Environment.getExternalStorageDirectory() + "/profilepic.jpg");
                    try {
                        FileOutputStream fileoutputstream = null;
                        file.createNewFile();
                        fileoutputstream = new FileOutputStream(file);
                        fileoutputstream.write(bytearrayoutputstream.toByteArray());
                        fileoutputstream.close();

                        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "profilepic.jpg");
                        FileInputStream fileInputStream = null;
                        fileInputStream = new FileInputStream(file);
                        imagePath = file.toString();
                        Log.d("FILE", (String.valueOf(file.length())));
                        fileInputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                // Load the selected image into a preview
                // ImageView ivPreview = (ImageView) findViewById(R.id.ivPreview);
//                imageView_uploadPhoto.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                imageView_uploadPhoto.setImageBitmap(profilepic);
                setReducedImageSize(photo, imagePath);

//                String[] filePathColumn = {MediaStore.Images.Media.DATA};
//
//                Cursor cursor = getActivity().getContentResolver().query(photoUri, filePathColumn, null, null, null);
//
//                if (cursor != null) {
//                    cursor.moveToFirst();
//
//                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                    imagePath = cursor.getString(columnIndex);
//                    cursor.close();


//
                //  }
//                File sourceFile = new File(imagePath);
//                if( sourceFile.length()>500000)
//                {
//                    Toast.makeText(getContext(),"Large file size",Toast.LENGTH_SHORT).show();
//                }

            } catch (IOException e) {
                e.printStackTrace();
                Log.d("Photo Selection Error:", e.toString());
            }

        }
    }

    //scale the image down to fit the imageView and not consume more memory
    void setReducedImageSize(ImageView mImageView, String mImageFileLocation) {

        //get the view's width and height
        int targetImageViewWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
        int targetImageViewHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());

        BitmapFactory.Options options = new BitmapFactory.Options();
        //get the image information
        //for dummy load
        options.inJustDecodeBounds = true;

        //load with options
        BitmapFactory.decodeFile(mImageFileLocation, options);

        //the width and height of camera image
        int cameraImageWidth = options.outWidth;
        int cameraImageHeight = options.outHeight;


        //calculate the scaling difference between the imageView and camera Image
        int scaleFactor = Math.min(cameraImageWidth / targetImageViewWidth,
                cameraImageHeight / targetImageViewHeight);

        options.inSampleSize = scaleFactor;
        //now for actual load turn off the dummy load
        options.inJustDecodeBounds = false;

        Bitmap photoReducedSize = BitmapFactory.decodeFile(mImageFileLocation, options);
        mImageView.setImageBitmap(photoReducedSize);
    }

}
