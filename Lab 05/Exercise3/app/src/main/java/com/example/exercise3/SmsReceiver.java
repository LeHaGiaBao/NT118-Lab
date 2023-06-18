package com.example.exercise3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import java.util.ArrayList;
import java.util.Objects;

public class SmsReceiver extends BroadcastReceiver {

    public static final String SMS_FORWARD_BROADCAST_RECEIVER = "sms_forward_broadcast_receiver";
    public static final String SMS_MESSAGE_ADDRESS_KEY = "sms_messages_key";


    @Override
    public void onReceive(Context context, Intent intent) {
        if (Objects.equals(intent.getAction(), "android.provider.Telephony.SMS_RECEIVED")){
            String queryString = context.getString(R.string.are_you_ok).toLowerCase();

            Bundle bundle = intent.getExtras();

            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");

                SmsMessage[] messages = new SmsMessage[pdus.length];
                String format = bundle.getString("format");
                for (int i = 0; i < pdus.length; i++) {

                    if (Build.VERSION.SDK_INT >= 23) {
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                    } else {
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    }
                }

                ArrayList<String> addresses = new ArrayList<>(messages.length);
                for (SmsMessage msg : messages) {
                    if (msg.getMessageBody().toLowerCase().contains(queryString)) {
                        addresses.add(msg.getOriginatingAddress());
                    }
                }

                if (addresses.size() > 0) {
                    if (!MainActivity.isRunning) {
                        Intent mainActivityIntent = new Intent(context, MainActivity.class);
                        mainActivityIntent.putStringArrayListExtra(SMS_MESSAGE_ADDRESS_KEY, addresses);
                        context.startActivity(mainActivityIntent);
                    } else {
                        Intent iForwardBroadcastReceiver = new Intent(SMS_FORWARD_BROADCAST_RECEIVER);
                        iForwardBroadcastReceiver.putStringArrayListExtra(SMS_MESSAGE_ADDRESS_KEY, addresses);
                        context.sendBroadcast(iForwardBroadcastReceiver);
                    }
                }
            }
        }
    }
}
