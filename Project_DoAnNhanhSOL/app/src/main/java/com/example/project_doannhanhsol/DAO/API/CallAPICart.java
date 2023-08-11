package com.example.project_doannhanhsol.DAO.API;

import com.example.project_doannhanhsol.DTO.Cart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CallAPICart {
    @POST("api/Cart/PostCart")
    Call<List<Cart>> PostCart(@Body Cart cart);
    @PUT("api/Cart/PutCart/{id}")
    Call<Cart> PutCart(@Path ("id")int id,@Body Cart cart);
    @GET("api/Cart/GetCartAccpunt/{id_account}")
    Call<List<Cart>> GetCartAccpunt(@Path("id_account") int id_account);
    @DELETE("api/Cart/DeleteCart/{id}")
    Call<Cart> DeleteCart(@Path("id") int id);
    @GET("api/Cart/GetCartAccpunt/TotalMoney/{id_account}")
    Call<String> GetTotalMoney(@Path("id_account") int id_account);
}
