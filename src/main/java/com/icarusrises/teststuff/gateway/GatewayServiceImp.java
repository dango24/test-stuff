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

        File photoFile = new File("/home/dango/Downloads/dango_p.jpg");

        RequestBody photoPart = RequestBody.create(MediaType.parse(".jpg"), photoFile);

        MultipartBody.Part file = MultipartBody.Part.createFormData("photo", photoFile.getName(), photoPart);

        return requestHandler.execute(gatewayRetrofitRequests.uploadPhoto(message,file));
    }
}
