package com.icarusrises.teststuff.retrofit.infrastrucre;

import com.icarusrises.teststuff.exceptions.RequestFailureException;
import com.icarusrises.teststuff.exceptions.UserInterruptException;
import retrofit2.Call;

public interface RequestHandler {

    void cancelRequest();
    <T extends Object> T execute(Call<T> request) throws RequestFailureException, UserInterruptException;
}
