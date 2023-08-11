package com.example.project_doannhanhsol.DAO.API;


import com.example.project_doannhanhsol.DTO.Account;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CallAPIAccount {
        @POST("api/Account/PostAccount")
        Call<String> PostAccount(@Body Account account);
        @PUT("api/Account/PutAccount/{id}")
        Call<String> PutAccount(@Path("id") int id, @Body Account account);
        @GET("api/account/getaccount{id}")
        Call<Account> GetAccount(@Path("id") int id);
        @GET("api/account/LoginAccount/{email}/{password}")
        Call<List<Account>> CheckAccount(@Path("email") String Email, @Path("password") String Password);

}
