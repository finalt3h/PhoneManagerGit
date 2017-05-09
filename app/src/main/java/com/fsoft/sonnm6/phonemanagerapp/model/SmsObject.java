package com.fsoft.sonnm6.phonemanagerapp.model;

/**
 * Created by SonNM6 on 12/27/2016.
 */
public class SmsObject {
    public String sender;
    public String mContent;

    public SmsObject(String sender, String mContent) {
        this.sender = sender;
        this.mContent = mContent;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }
}
