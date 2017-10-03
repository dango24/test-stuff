package com.icarusrises.teststuff.gateway;

import com.icarusrises.teststuff.retrofit.infrastrucre.UrlWrapper;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface GatewayRetrofitRequests {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("rc9s21rc")
    Call<Void> sendMessage(@Body String message);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("additional-time")
    Call<Integer> additionalTimeForWebTestToFinishInSec();

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("next-web-site")
    Call<UrlWrapper> getNextSpeedTestWebSite();

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("next-urls")
    Call<List<String>> getNextUrls(@Query("num_of_comparison_per_test") int numOfComparisonPerTest);


    @Multipart
    @POST("upload")
    Call<String> uploadPhoto(
        @Part("message") RequestBody message,
        @Part MultipartBody.Part photo
    );
}
