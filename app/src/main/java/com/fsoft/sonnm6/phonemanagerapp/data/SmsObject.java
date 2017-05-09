package com.fsoft.sonnm6.phonemanagerapp.data;

/**
 * Created by SonNM6 on 4/27/2017.
 */

public class SmsObject extends InfomationConversation {
    private String mContent;
    private String mNameFile;
    private long mTimeSms;

    public SmsObject(boolean isIncoming, double phoneNumberCustomer, String content, long timeSms) {
        super(isIncoming, phoneNumberCustomer);
        mContent = content;
        mTimeSms = timeSms;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public String getNameFile() {
        return mNameFile;
    }

    public void setNameFile(String mNameFile) {
        this.mNameFile = mNameFile;
    }

    public long getTimeSms() {
        return mTimeSms;
    }

    public void setTimeSms(long mTimeSms) {
        this.mTimeSms = mTimeSms;
    }

}
