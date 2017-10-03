package com.icarusrises.teststuff.gateway;

import com.google.gson.Gson;
import com.icarusrises.teststuff.exceptions.UserInterruptException;
import com.icarusrises.teststuff.retrofit.infrastrucre.RequestHandler;
import com.icarusrises.teststuff.retrofit.infrastrucre.RetrofitBuilder;
import com.icarusrises.teststuff.retrofit.infrastrucre.UrlWrapper;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GatewayServiceImp {

    @Value("${central_url}")
    private String centralUrl;

    private RequestHandler requestHandler;
    private GatewayRetrofitRequests gatewayRetrofitRequests;

    @PostConstruct
    public void init() {
        Retrofit retrofit = RetrofitBuilder.Retrofit(centralUrl)
                                           .build();

        gatewayRetrofitRequests = retrofit.create(GatewayRetrofitRequests.class);
    }

    @Autowired
    public void setRequestHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }


    public int additionalTimeForWebTestToFinishInSec() throws UserInterruptException {
        return requestHandler.execute(gatewayRetrofitRequests.additionalTimeForWebTestToFinishInSec());
    }

    public UrlWrapper getNextSpeedTestWebSite() throws UserInterruptException {
        return requestHandler.execute(gatewayRetrofitRequests.getNextSpeedTestWebSite());
    }

    public List<String> getNextUrls(int numOfComparisonPerTest) throws UserInterruptException {
        return requestHandler.execute(gatewayRetrofitRequests.getNextUrls(numOfComparisonPerTest));
    }

    public String upload() throws UserInterruptException {

        RequestBody message = RequestBody.create(MultipartBody.FORM, new Gson().toJson(new UrlWrapper("dango&Esif")));

        File photoFile1 = new File("/home/dango/Downloads/dango_p.jpg");
        RequestBody photoPart1 = RequestBody.create(MediaType.parse(".jpg"), photoFile1);
        MultipartBody.Part file1 = MultipartBody.Part.createFormData("photo1", photoFile1.getName(), photoPart1);

        File photoFile2 = new File("/home/dango/Downloads/esfir_p.jpg");
        RequestBody photoPart2 = RequestBody.create(MediaType.parse(".jpg"), photoFile2);
        MultipartBody.Part file2 = MultipartBody.Part.createFormData("photo2", photoFile2.getName(), photoPart2);

        List<MultipartBody.Part> parts = new ArrayList<>();

        parts.add(file1);
        parts.add(file2);

        return requestHandler.execute(gatewayRetrofitRequests.uploadPhoto2(message, parts));
    }
}
