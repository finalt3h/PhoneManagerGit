package com.fsoft.sonnm6.phonemanagerapp;


import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fsoft.sonnm6.phonemanagerapp.data.MySharedPreferences;
import com.fsoft.sonnm6.phonemanagerapp.data.NetworkStateReceiver;
import com.fsoft.sonnm6.phonemanagerapp.service.MyService;
import com.fsoft.sonnm6.phonemanagerapp.view.LoginFragment;
import com.fsoft.sonnm6.phonemanagerapp.view.SettingFragment;

public class MainActivity extends AppCompatActivity implements NetworkStateReceiver.NetworkStateReceiverListener {
    private static final String TAG = "MainActivity";
    private static final int MY_RESULT_PERMISSION = 1;
    private Fragment fragmentSetting;
    private Fragment fragmentLogin;
    public static final String ACTION_LOGIN_SUCCESS = "ACTION_LOGIN_SUCCESS";
    private static final IntentFilter s_Filter = new IntentFilter();

    private NetworkStateReceiver networkStateReceiver;


    static {
        s_Filter.addAction(ACTION_LOGIN_SUCCESS);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(networkStateReceiver);
        unregisterReceiver(broadcastReceiver);
        broadcastReceiver = null;
        fragmentLogin = null;
        fragmentSetting = null;
        super.onDestroy();
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ACTION_LOGIN_SUCCESS)) {
                callFragment(fragmentSetting);
            }
        }
    };

    private void regesterBroadcastInternet() {
        networkStateReceiver = new NetworkStateReceiver();
        networkStateReceiver.addListener(this);
        this.registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        regesterBroadcastInternet();
        registerReceiver(broadcastReceiver, s_Filter);
        setContentView(R.layout.activity_main);
        fragmentSetting = SettingFragment.newInstance();
        fragmentLogin = LoginFragment.newInstance();
        if (MySharedPreferences.getInstance(this).getStatePass()) {
            callFragment(fragmentSetting);

        } else {

            String pass = MySharedPreferences.getInstance(this).getPass();
            if (pass != null && pass.trim().length() > 0) {
                callFragment(fragmentLogin);
            } else {
                callFragment(fragmentSetting);
            }


        }
        checkPermissions();


    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS, Manifest.permission.READ_PHONE_STATE},
                    MY_RESULT_PERMISSION);
            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    MY_RESULT_PERMISSION);
            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        } else {
            Log.d(TAG, "checkPermissions: ok ok");
        }
    }

    public void callFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main, fragment);
        transaction.commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

    }

    @Override
    public void networkAvailable() {
        startService(new Intent(this, MyService.class));
    }

    @Override
    public void networkUnavailable() {

    }
}
