package com.icarusrises.teststuff.retrofit.infrastrucre;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class RetrofitBuilder {


    public static Retrofit.Builder Retrofit(String url) {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                                                          .readTimeout(130, TimeUnit.SECONDS)
                                                          .writeTimeout(130, TimeUnit.SECONDS)
                                                          .connectTimeout(130, TimeUnit.SECONDS)
                                                          .build();

        Retrofit.Builder retrofit = new Retrofit.Builder()
                                                .client(okHttpClient)
                                                .baseUrl(url);

        Gson gson = new GsonBuilder().setLenient().create();
        retrofit.addConverterFactory(GsonConverterFactory.create(gson));

        return retrofit;
    }

}
