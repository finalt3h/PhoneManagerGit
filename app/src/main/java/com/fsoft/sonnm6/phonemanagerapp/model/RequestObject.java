package com.fsoft.sonnm6.phonemanagerapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by SonNM6 on 12/27/2016.
 */
public class RequestObject implements Serializable {
    @SerializedName("token")
    public String token;
    @SerializedName("id_")
    public String id_;
    @SerializedName("sender")
    public String sender;
    @SerializedName("message")
    public String message;

    public RequestObject(String token, String id, String sender, String message) {
        this.token = token;
        this.id_ = id;
        this.sender = sender;
        this.message = message;
    }
}
