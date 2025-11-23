package com.connecivity.comunication.infrastructure.sms;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmsConfig {

    @Value("${sms.account-sid}")
    private String accountSid;
    @Value("${sms.auth-token}")
    private String authToken;

    @PostConstruct
    public void twilioConfig() {
        Twilio.init(accountSid, authToken);
    }
}
