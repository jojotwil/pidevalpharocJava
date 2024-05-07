package com.example.demo;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import com.twilio.Twilio;

public class smsAPI {
    private static final String ACCOUNT_SID = "ACace154ef9e4b3245d0255ef11d0f6cc5";
    private static final String AUTH_TOKEN = "14038d2c0e2ceac812de4e69215f7130";

    public static void init() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public static void sendSMS(String to, String from, String body) {
        Message message = Message.creator(
                        new PhoneNumber(to),
                        new PhoneNumber(from),
                        body)
                .create();

        System.out.println("SMS sent successfully. SID: " + message.getSid());
    }
}
