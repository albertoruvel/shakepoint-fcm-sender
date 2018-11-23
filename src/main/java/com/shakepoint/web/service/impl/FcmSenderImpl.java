package com.shakepoint.web.service.impl;

import com.github.roar109.syring.annotation.ApplicationProperty;
import com.shakepoint.web.service.FcmClient;
import com.shakepoint.web.service.FcmSender;
import org.apache.log4j.Logger;
import retrofit2.Response;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class FcmSenderImpl implements FcmSender {

    @Inject
    @ApplicationProperty(type = ApplicationProperty.Types.SYSTEM, name = "com.shakepoint.web.fcm.projectId")
    private String projectId;

    @Inject
    @ApplicationProperty(type = ApplicationProperty.Types.SYSTEM, name = "com.shakepoint.web.fcm.apiKey")
    private String fcmApiKey;

    @Inject
    private FcmClient fcmClient;

    private Logger log = Logger.getLogger(getClass());

    @Override
    public void sendSingleNotification(String notificationMessage) {
        try{
            Response<Void> response = fcmClient.sendNotification(fcmApiKey, projectId, notificationMessage)
                    .execute();
            if (! response.isSuccessful()) {
                log.error("Could not send PUSH notification: " + response.toString());
            }
        } catch(Exception ex) {
            log.error("Could not send PUSH notification", ex);
        }
    }

    @Override
    public void sendBoradcast() {

    }
}
