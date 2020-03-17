package com.blueapple.imageupload_retrofit;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blueapple.imageupload_retrofit.api.API;
import com.blueapple.imageupload_retrofit.api.RestClient;
import com.blueapple.imageupload_retrofit.model.ImageRequest;
import com.blueapple.imageupload_retrofit.model.ImageResponse;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ImageView mImGetImage;
    private Button mBtnUpload;

    String encodedImage;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        try
        {


            Bitmap bm = BitmapFactory.decodeFile("/storage/emulated/0/download.jpg");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
            byte[] byteArrayImage = baos.toByteArray();
            encodedImage= Base64.encodeToString(byteArrayImage, Base64.DEFAULT);

            Log.d("encoded_image", encodedImage);
        }
        catch (Exception e)
        {

        }

        mBtnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                uploadImage(encodedImage);
            }
        });
    }

    private void uploadImage(String encodedImage) {

        final ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("please wait");
        progressDialog.show();

        ImageRequest imageRequest=new ImageRequest();
        imageRequest.setImage(encodedImage);
        imageRequest.setVoucherId("7110300138 19");

        API api_interface= RestClient.getRetrofit().create(API.class);

        Call<ImageResponse> call=api_interface.
                uploadImage("application/json",imageRequest);

        call.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {

                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();

                ImageResponse imageResponse=response.body();

                Log.d("image_url",imageResponse.getUrl());

                progressDialog.dismiss();


                Glide.with(MainActivity.this).load(Uri.parse(imageResponse.getUrl()))
                        .into(mImGetImage);
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {

                Log.d("failure",t.getMessage());
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        mImGetImage = (ImageView) findViewById(R.id.im_getImage);
        mBtnUpload = (Button) findViewById(R.id.btn_upload);
    }
}
