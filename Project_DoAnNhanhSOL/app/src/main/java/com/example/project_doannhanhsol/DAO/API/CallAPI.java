package com.example.project_doannhanhsol.DAO.API;

import static com.example.project_doannhanhsol.DAO.API.OkHttpUtils.getUnsafeOkHttpClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public interface CallAPI {
    public static String url = "https://192.168.1.4:45455/";
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    Retrofit retrofit2 = new Retrofit.Builder().baseUrl(url).client(getUnsafeOkHttpClient().build())
            .addConverterFactory(GsonConverterFactory.create(gson)).build();
    //Call Api tài khoản
    CallAPIAccount callAPIAccount = retrofit2.create(CallAPIAccount.class);
    //Call Api danh mục
    CallAPICategory callApiCategory = retrofit2.create(CallAPICategory.class);
    //Call Api gió hàng
    CallAPICart callApiCart = retrofit2.create(CallAPICart.class);
    //Call Api sản phẩm yêu thích
    CallAPILove CallAPILove = retrofit2.create(CallAPILove.class);
    //Call Api thông báo
    CallAPINotify CallAPINotify = retrofit2.create(CallAPINotify.class);
    //Call Api đơn hàng
    CallAPIOrder callApiOrder = retrofit2.create(CallAPIOrder.class);
    //Call Api Sản phẩm
    CallAPIProduct callApiProduct=retrofit2.create(CallAPIProduct.class);
    //Call Api chi tiết sản phẩm
    CallAPIProductDetails callApiProductDetails=retrofit2.create(CallAPIProductDetails.class);
}
