package com.example.project_doannhanhsol.DAO.API;

import com.example.project_doannhanhsol.DTO.Notify;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CallAPINotify {
    @POST("api/Notifies/PostNotify")
    Call<Notify>PostNotify(@Body Notify notify);
    @GET("api/Notifies/GetNotifys/{Id_Account}")
    Call<List<Notify>>GetNotifys(@Path("Id_Account")int Id_Account);
    @GET("api/Notifies/GetNotifies/{Id_Account}")
    Call<List<Notify>>GetNotifies(@Path("Id_Account")int Id_Account);
    @PUT("api/Notifies/PutNotify/{id}")
    Call<Notify>PutNotify(@Path("id")int Id_Account,@Body Notify notify);
}
