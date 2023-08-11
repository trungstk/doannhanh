package com.example.project_doannhanhsol.DAO.API;


import com.example.project_doannhanhsol.DTO.Love;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CallAPILove {
    @GET("api/Love/checklove/{Id_Account}/{Id_product}")
    Call<List<Love>> Checklove(@Path("Id_Account") int Id_Account, @Path("Id_product") int Id_product);
    @POST("api/Love/PostLove")
    Call<Boolean> PostLove(@Body Love love);
    @POST("api/Love/DeleteLove/{id}")
    Call<Boolean> DeleteLove(@Path("id") int id);
    @GET("api/Love/checklove/{Id_Account}")
    Call<List<Love>> checklove(@Path("Id_Account") int Id_Accountt);
}
