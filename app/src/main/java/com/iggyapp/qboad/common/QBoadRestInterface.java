package com.iggyapp.qboad.common;

import com.iggyapp.qboad.model.UserModel;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface QBoadRestInterface {

    @GET("login/index")
    Single<UserModel> sendLogin();
//    Single<UserModel> sendLogin(@Header("authorization") String authorization);

//    @POST("users/active")
//    Single<UserModel> sendLogin(@Header("authorization") String authorization);

}
