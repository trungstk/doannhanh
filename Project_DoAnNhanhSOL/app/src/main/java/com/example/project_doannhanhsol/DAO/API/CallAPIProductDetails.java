package com.example.project_doannhanhsol.DAO.API;

import com.example.project_doannhanhsol.DTO.ProductDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CallAPIProductDetails {
    @GET("api/ProductDetails/GetProductDetails/{Id_product}")
    Call<List<ProductDetails>> GetProductDetails(@Path("Id_product") int Id_product);
    @GET("api/ProductDetails/GetProductDetail/{id}")
    Call<ProductDetails> GetProductDetail(@Path("id") int id);

}
