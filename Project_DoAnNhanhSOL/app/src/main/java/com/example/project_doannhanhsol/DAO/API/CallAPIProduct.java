package com.example.project_doannhanhsol.DAO.API;


import com.example.project_doannhanhsol.DTO.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CallAPIProduct {
    @GET("api/Product/GetProductDiscount")
    Call<List<Product>> GetProductDiscount();

    @GET("api/Product/GetProductPopular")
    Call<List<Product>> GetProductPopular();

    @GET("api/Products/GetProducts")
    Call<List<Product>> GetProducts();

    @GET("api/Products/GetProductLike")
    Call<List<Product>> GetProductLike();

    @GET("api/Products/GetProduct/{id}")
    Call<Product> GetProduct(@Path("id") int id);

    @GET("api/Products/GetProductsPage/{pageNumber}/{pageSize}")
    Call<List<Product>> GetProductsPage(@Path("pageNumber") int PageNumber, @Path("pageSize") int PageSize);

    @GET("api/Products/GetSearchProduct/{search}")
    Call<List<Product>> GetSearchProduct(@Path("search") String search);

    @GET("api/Products/GetProductCategory/{pageNumber}/{pageSize}/{id_category}")
    Call<List<Product>> GetProductCategory(@Path("pageNumber") int PageNumber, @Path("pageSize") int PageSize, @Path("id_category") int id_category);

    @GET("api/Products/GetSearchProductCategory/{search}/{id_category}")
    Call<List<Product>> GetSearchProductCategory(@Path("search") String search, @Path("id_category") int id_category);

}
