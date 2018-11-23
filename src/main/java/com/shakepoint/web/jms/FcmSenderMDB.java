package com.shakepoint.web.jms;

import com.shakepoint.web.service.FcmSender;
import org.apache.log4j.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@TransactionManagement(TransactionManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@MessageDriven(name = "FCMSenderMDB", activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "fcm_send"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class FcmSenderMDB implements MessageListener {

    private Logger LOG = Logger.getLogger(getClass());

    @Inject
    private FcmSender fcmSender;

    @Override
    public void onMessage(Message message) {
        try{
            TextMessage textMessage = (TextMessage) message;
            fcmSender.sendSingleNotification(textMessage.toString());
        } catch(Exception ex) {

        }

    }
}
