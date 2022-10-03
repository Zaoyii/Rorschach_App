package com.zcyi.rorschach.Util.Request;

import com.zcyi.rorschach.Entity.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserRequest {

    @GET(value = "User/Login")
    Call<ApiResult<User>> Login(@Query("userName") String Username, @Query("userPassword") String Password);
}
