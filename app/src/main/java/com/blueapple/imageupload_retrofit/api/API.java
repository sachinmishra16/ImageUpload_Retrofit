package com.blueapple.imageupload_retrofit.api;


import com.blueapple.imageupload_retrofit.model.ImageRequest;
import com.blueapple.imageupload_retrofit.model.ImageResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface API {

    @POST("voucher/screenshot")
    Call<ImageResponse> uploadImage(

            @Header("Content-Type")
                    String headerValue,
            @Body ImageRequest imageRequest
            );

}
