package com.fsoft.sonnm6.phonemanagerapp.model;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by SonNM6 on 12/23/2016.
 */
public class MyToash extends Toast {
    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public MyToash(Context context) {
        super(context);
    }
}
