package com.icarusrises.teststuff.retrofit.infrastrucre;


import com.icarusrises.teststuff.exceptions.RequestFailureException;
import com.icarusrises.teststuff.exceptions.UserInterruptException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Objects;

@Component
public class RequestHandlerImpl implements RequestHandler {

    private Logger logger = Logger.getLogger(RequestHandlerImpl.class);

    private Call<? extends Object> currentRequest = null;

    @Override
    public void cancelRequest() {
        try {
            if (Objects.nonNull(currentRequest)) {
                currentRequest.cancel();
            }
        } catch (Exception e) {
            logger.error("Failed to cancel request, " + e.getMessage(), e);
        }
    }

    @Override
    public <T extends Object> T execute(Call<T> request) throws RequestFailureException, UserInterruptException {
        try {
            currentRequest = request;
            return executeRequest();

        } catch (IOException e) {
            if (e.getMessage().equals("Canceled")) {
                throw new UserInterruptException(e.getMessage(), e);
            } else {
                throw new RequestFailureException(e.getMessage(), e);
            }

        } finally {
            currentRequest = null;
        }
    }

    private <T extends Object> T executeRequest() throws IOException, RequestFailureException, UserInterruptException {
        Response<T> response = (Response<T>) currentRequest.execute();

        if (response.isSuccessful()) {
            return response.body();

        } else if (currentRequest.isCanceled()) {
            throw new UserInterruptException("User cancelRequest request");
        } else {
            throw new RequestFailureException(response.errorBody().string(), response.code());
        }
    }
}
