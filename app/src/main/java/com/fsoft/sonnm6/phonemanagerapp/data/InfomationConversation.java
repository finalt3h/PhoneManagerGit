package com.fsoft.sonnm6.phonemanagerapp.data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by SonNM6 on 4/27/2017.
 */

public abstract class InfomationConversation {
    protected boolean mIsIncoming;
    protected double mPhoneNumberCustomer;

    public InfomationConversation(boolean mIsIncoming, double mPhoneNumberCustomer) {
        this.mIsIncoming = mIsIncoming;
        this.mPhoneNumberCustomer = mPhoneNumberCustomer;
    }

    public boolean isIsIncoming() {
        return mIsIncoming;
    }

    public void setIsIncoming(boolean mIsIncoming) {
        this.mIsIncoming = mIsIncoming;
    }

    public double getPhoneNumberCustomer() {
        return mPhoneNumberCustomer;
    }

    public void setPhoneNumberCustomer(double mPhoneNumberCustomer) {
        this.mPhoneNumberCustomer = mPhoneNumberCustomer;
    }
    public static String coverTime(long time){
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(stamp.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss");
        String format = formatter.format(date);
        System.out.println(format );
        return format;
    }

    public static String coverSecond(long time){
        return
    }


}
