package com.fsoft.sonnm6.phonemanagerapp.model;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Url;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by SonNM6 on 12/26/2016.
 */
public interface APISevice {
    @POST
    public Call<ResponseBody> sendService(@Url String url, @Body RequestObject requestObject);

}
