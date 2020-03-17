package com.blueapple.imageupload_retrofit.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    //public static String base_url="http://clubmbd.com/incentiwised/api/";
    //public static String base_url = BuildConfig.BASE_URL;
    public static String base_url = "http://192.168.1.19/mbd_club_admin/api/";

    public static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {

/*

            retrofit = new Retrofit.Builder().
                    baseUrl(base_url).client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();mo ,.88888888` -+-
*/

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(interceptor);
            httpClient.readTimeout(4, TimeUnit.HOURS);
            httpClient.writeTimeout(4, TimeUnit.HOURS);
            httpClient.connectTimeout(4, TimeUnit.MINUTES);
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder().baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient.build())
                    .build();


        }

        return retrofit;
    }


}
