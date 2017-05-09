package com.fsoft.sonnm6.phonemanagerapp.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.util.Log;


import com.fsoft.sonnm6.phonemanagerapp.data.MySharedPreferences;
import com.fsoft.sonnm6.phonemanagerapp.model.APISevice;
import com.fsoft.sonnm6.phonemanagerapp.model.RequestObject;
import com.fsoft.sonnm6.phonemanagerapp.model.SmsObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SonNM6 on 12/27/2016.
 */
public class MyService extends Service {
    private static final String TAG = "MyService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ArrayList<SmsObject> smsObjects = MySharedPreferences.getInstance(this).getListSMS();
        String api = MySharedPreferences.getInstance(this).getAPI();
        String token = MySharedPreferences.getInstance(this).getToken();
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String id = telephonyManager.getDeviceId();
        int sizeListSender = smsObjects.size();
        if (sizeListSender > 0) {
            MySharedPreferences.getInstance(this).clearListSMS();
            int i = 0;
            boolean postionEnd = false;
            for (SmsObject smsObject : smsObjects) {
                if (i == sizeListSender - 1) {
                    postionEnd = true;
                }
                sendToService(api, new RequestObject(token, id, smsObject.getSender(), smsObject.getmContent()), smsObject, postionEnd);
                i++;
            }


        } else {
            stopSelf();
        }
        return START_NOT_STICKY;

    }

    int k = 0;

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.d(TAG, "onStart: ");
        super.onStart(intent, startId);
    }

    public void sendToService(String api, RequestObject requestObject, final SmsObject smsObject, final boolean positionEnd) {
        if (api != null && api.length() > 0) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            Retrofit retrofit = new Retrofit.Builder().client(client).baseUrl("https://your.api.url/").addConverterFactory(GsonConverterFactory.create()).build();
            APISevice service = retrofit.create(APISevice.class);
            Call<ResponseBody> requestObjectCall = service.sendService(api, requestObject);
            requestObjectCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        if (positionEnd) {
                            stopSelf();
                        }
                    } else {
                        ArrayList<SmsObject> smsObjects = MySharedPreferences.getInstance(MyService.this).getListSMS();
                        smsObjects.add(smsObject);
                        MySharedPreferences.getInstance(MyService.this).saveListSMS(smsObjects);
                        if (positionEnd) {
                            stopSelf();
                        }
                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    ArrayList<SmsObject> smsObjects = MySharedPreferences.getInstance(MyService.this).getListSMS();
                    smsObjects.add(smsObject);
                    MySharedPreferences.getInstance(MyService.this).saveListSMS(smsObjects);
                    if (positionEnd) {
                        stopSelf();
                    }
                }
            });
        }
    }


}
