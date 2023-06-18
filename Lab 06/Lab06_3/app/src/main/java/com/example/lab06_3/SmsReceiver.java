package com.example.lab06_3;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;
import com.example.lab06_3.MainActivity;
import java.util.ArrayList;

public class SmsReceiver extends BroadcastReceiver {

    public static final String SMS_FORWARD_BROADCAST_RECEIVER = "sms_forward_broadcast_receiver";
    public static final String SMS_MESSAGE_ADDRESS_KEY = "sms_messages_key";

    @Override
    public void onReceive (Context context, Intent intent) {
        Toast.makeText(context.getApplicationContext(), "SmsReceiver", Toast.LENGTH_SHORT).show();
        String queryString = "Are you OK?".toLowerCase();
        System.out.println("________" + queryString);

        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0; i < pdus.length; i++) {
                if (android.os.Build.VERSION.SDK_INT >= 23) {
                    String format = bundle.getString("format");
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                } else {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
            }

            // Create ArrayList of OriginatingAddress of messages vhich
            ArrayList<String> addresses = new ArrayList<>();

            for (SmsMessage message : messages) {

                if (message.getMessageBody().toLowerCase().contains(queryString)) {
                    addresses.add(message.getOriginatingAddress());
                }
            }
            if (addresses.size() > 0) {
                if (!MainActivity.isRunning) {
                    // Start Mainactivity if it stopped
                    Intent iMain = new Intent(context,MainActivity.class);
                    iMain.putStringArrayListExtra(SMS_MESSAGE_ADDRESS_KEY, addresses);
                    iMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
                    context.sendBroadcast(iMain);
                    context.startActivity(iMain);

                    Toast.makeText(context.getApplicationContext(), "iMainSmsReceiver", Toast.LENGTH_SHORT).show();
                } else {
                    Intent iForwardBroadcastReceiver = new Intent(SMS_FORWARD_BROADCAST_RECEIVER);

                    iForwardBroadcastReceiver.putStringArrayListExtra(SMS_MESSAGE_ADDRESS_KEY, addresses);
                    context.sendBroadcast(iForwardBroadcastReceiver);
                }
            }
        }
    }
}

