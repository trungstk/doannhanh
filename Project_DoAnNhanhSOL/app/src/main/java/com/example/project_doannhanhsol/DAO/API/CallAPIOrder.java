package com.example.project_doannhanhsol.DAO.API;

import com.example.project_doannhanhsol.DTO.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CallAPIOrder {
    @POST("api/order/Postorder")
    Call<Order> Postorder_(@Body Order order);

    @GET("api/order/Getorder/{id_Account}/{Status}")
    Call<List<Order>> Getorder(@Path("id_Account") int id_Account, @Path("Status") int Status);

    @GET("api/order/GetorderAccount/{id_Account}")
    Call<List<Order>> GetorderAccount(@Path("id_Account") int id_Account);

    @DELETE("api/order/Deleteorder/{id}")
    Call<Order> Deleteorder(@Path("id") int id);

    @PUT("api/order/Putorder_/{id}")
    Call<Order> Putorder_(@Path("id") int id,@Body Order order);

}
