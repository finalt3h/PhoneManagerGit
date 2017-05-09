package com.fsoft.sonnm6.phonemanagerapp.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;


import com.fsoft.sonnm6.phonemanagerapp.data.MySharedPreferences;
import com.fsoft.sonnm6.phonemanagerapp.model.SmsObject;

import java.util.ArrayList;

/**
 * Created by SonNM6 on 12/26/2016.
 */
public class IncomingSms extends BroadcastReceiver {
    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();

    public void onReceive(Context context, Intent intent) {
        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();
                    Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);
                    ArrayList<String> listSender = MySharedPreferences.getInstance(context).getListSender();
                    for (String sender : listSender) {
                        if (sender.equals(phoneNumber)) {
                            ArrayList<SmsObject> smsObjects = MySharedPreferences.getInstance(context).getListSMS();
                            smsObjects.add(new SmsObject(senderNum, message));
                            MySharedPreferences.getInstance(context).saveListSMS(smsObjects);
                            context.startService(new Intent(context, MyService.class));
                            break;
                        }
                    }

                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);

        }
    }
}
