package com.fsoft.sonnm6.phonemanagerapp.data;

/**
 * Created by SonNM6 on 4/27/2017.
 */

public class CallObject extends InfomationConversation {
    private long mTimeStart;
    private long mTimeEnd;
    private String mFileRecord;

    public CallObject( boolean isIncoming,double phoneNumber,long mTimeStart, long mTimeEnd,  String mFileRecord) {
        super(isIncoming,phoneNumber);
        this.mTimeStart = mTimeStart;
        this.mTimeEnd = mTimeEnd;
        this.mFileRecord = mFileRecord;
    }

    public long getTimeStart() {
        return mTimeStart;
    }

    public void setTimeStart(long mTimeStart) {
        this.mTimeStart = mTimeStart;
    }

    public long getTimeEnd() {
        return mTimeEnd;
    }

    public void setTimeEnd(long mTimeEnd) {
        this.mTimeEnd = mTimeEnd;
    }

    public String getFileRecord() {
        return mFileRecord;
    }

    public void setmFileRecord(String mFileRecord) {
        this.mFileRecord = mFileRecord;
    }
}
