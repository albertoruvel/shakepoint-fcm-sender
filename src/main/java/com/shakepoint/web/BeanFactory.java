package com.shakepoint.web;

import com.github.roar109.syring.annotation.ApplicationProperty;
import com.shakepoint.web.service.FcmClient;
import retrofit2.Retrofit;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

public class BeanFactory {

    private Retrofit retrofit;

    @Inject
    @ApplicationProperty(type = ApplicationProperty.Types.SYSTEM, name = "com.shakepoint.web.fcm.serverUrl")
    private String fcmServerUrl;

    @Produces
    public FcmClient fcmClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(fcmServerUrl)
                    .build();
        }
        return retrofit.create(FcmClient.class);
    }
}
