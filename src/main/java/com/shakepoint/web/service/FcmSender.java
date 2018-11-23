package com.shakepoint.web.service;

public interface FcmSender {
    public void sendSingleNotification(String notificationMessage);
    public void sendBoradcast();
}
