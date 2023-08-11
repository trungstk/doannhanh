package com.example.project_doannhanhsol.DAO.API;

import com.example.project_doannhanhsol.DTO.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CallAPICategory {
    @GET("api/Category")
    Call<List<Category>> GetCategory();
}
