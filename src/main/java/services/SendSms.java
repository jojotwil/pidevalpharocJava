/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
/**
 *
 * @author SeifBS
 */
public class SendSms {

    public void sendSms(String message_, String number) throws UnsupportedEncodingException, MalformedURLException, IOException {

        String AUTH_TOKEN = "64fc919f89de9d103e056fea4ab1c0db";
        String ACCOUNT_SID = "AC4fda89a8e203dc207d979612fae7ebc4";


        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+216"+number),
                new PhoneNumber("+12074924507"),
                message_).create();

        System.out.println(message.getSid());
    }


}