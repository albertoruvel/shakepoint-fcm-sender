package com.shakepoint.web.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FcmClient {

    @POST("{projectId}/messages:send")
    public Call<Void> sendNotification(@Header("Authorization") String apiKey, @Path("projectId") String projectId, @Body String message);
}
