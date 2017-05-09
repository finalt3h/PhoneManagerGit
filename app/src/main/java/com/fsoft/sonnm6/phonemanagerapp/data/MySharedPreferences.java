package com.fsoft.sonnm6.phonemanagerapp.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.fsoft.sonnm6.phonemanagerapp.model.SmsObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


/**
 * Created by sonnm6 on 6/28/2016.
 */

public class MySharedPreferences {
    private static final String TAG = "MySharedPreferences";
    private static final String KEY_TOKEN = "KEY_TOKEN";
    private static final String KEY_API = "KEY_API";
    private static final String KEY_PASS = "KEY_PASS";
    private static final String KEY_LIST_SENDER = "KEY_LIST_SENDER";
    private static final String KEY_STATE_PASS = "KEY_STATE_PASS";
    private static final String KEY_LIST_SMS = "KEY_LIST_SMS";
    private static final String KEY_SETTING_FIRST = "KEY_SETTING_FIRST";

    private static MySharedPreferences sMySharedpreferences;
    private Context mContext;
    public static final String NAME_DATA = "MyData";
    private static SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    public Gson gson;
    public ArrayList<SmsObject> smsObjects;


    private MySharedPreferences(Context context) {
        mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(NAME_DATA,
                Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        gson = new Gson();
        if (getSettingFirst()) {
            ArrayList<String> sender = new ArrayList<>();
            sender.add("VIETCOMBANK");
            sender.add("TECHCOMBANK");
            sender.add("VIETINBANK");
            sender.add("ACB");
            saveListSender(sender);
            saveSettingFirst(false);
        }
    }

    public static MySharedPreferences getInstance(Context context) {
        if (sMySharedpreferences == null) {
            sMySharedpreferences = new MySharedPreferences(context);
        } else {
            mSharedPreferences = context.getSharedPreferences(NAME_DATA,
                    Context.MODE_PRIVATE);
        }
        return sMySharedpreferences;
    }

    // add String json ResponseJson and setup status Login=true. and Email.
    public void saveSetting(String token, String api) {
        mEditor.putString(KEY_API, token);
        mEditor.putString(KEY_TOKEN, api);
        mEditor.commit();
    }

    public void savePass(String pass) {
        mEditor.putString(KEY_PASS, pass);
        mEditor.commit();
    }

    public void saveAPI(String api) {
        mEditor.putString(KEY_API, api);
        mEditor.commit();
    }
    public void saveSettingFirst(boolean api) {
        mEditor.putBoolean(KEY_SETTING_FIRST, api);
        mEditor.commit();
    }

    public void saveStatePass(boolean api) {
        mEditor.putBoolean(KEY_STATE_PASS, api);
        mEditor.commit();
    }

    public boolean getStatePass() {
        return mSharedPreferences.getBoolean(KEY_STATE_PASS, true);
    }
    public boolean getSettingFirst() {
        return mSharedPreferences.getBoolean(KEY_SETTING_FIRST, true);
    }

    public void saveToken(String token) {
        mEditor.putString(KEY_TOKEN, token);
        mEditor.commit();
    }

    public String getPass() {
        return mSharedPreferences.getString(KEY_PASS, null);
    }

    public String getToken() {
        return mSharedPreferences.getString(KEY_TOKEN, null);
    }

    public String getAPI() {
        return mSharedPreferences.getString(KEY_API, null);
    }

    // remove infomation User when logout.
    public void removeDataLogin() {
        mEditor.remove(KEY_TOKEN);
        mEditor.remove(KEY_API);
        mEditor.remove(KEY_PASS);
        mEditor.commit();
    }

    public void saveListSender(ArrayList<String> strings) {
        if (strings != null) {
            try {
                String listSender = gson.toJson(strings);
                mEditor.putString(KEY_LIST_SENDER, listSender);
                mEditor.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public ArrayList<String> getListSender() {
        ArrayList<String> strings = new ArrayList<>();
        strings = gson.fromJson(mSharedPreferences.getString(KEY_LIST_SENDER, null), new TypeToken<ArrayList<String>>() {
        }.getType());
//        Log.d(TAG, "getListSender: " + strings.size());
        if (strings == null) {
            strings = new ArrayList<>();
        }
        return strings;
    }

    public void saveListSMS(ArrayList<SmsObject> strings) {
        if (strings != null) {
            try {
                String listSender = gson.toJson(strings);
                mEditor.putString(KEY_LIST_SMS, listSender);
                mEditor.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public ArrayList<SmsObject> getListSMS() {
        ArrayList<SmsObject> strings = new ArrayList<>();
        strings = gson.fromJson(mSharedPreferences.getString(KEY_LIST_SMS, null), new TypeToken<ArrayList<SmsObject>>() {
        }.getType());
//        Log.d(TAG, "getListSender: " + strings.size());
        if (strings == null) {
            strings = new ArrayList<>();
        }
        return strings;

    }

    public void clearListSMS() {
        mEditor.remove(KEY_LIST_SMS);
        mEditor.commit();
    }
}