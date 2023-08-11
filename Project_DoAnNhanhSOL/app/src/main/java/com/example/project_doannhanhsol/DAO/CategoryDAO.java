package com.example.project_doannhanhsol.DAO;

import static com.example.project_doannhanhsol.BUS.HandlingBUS.ERROR_CALL_API_CATEGORY;

import android.app.Activity;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_doannhanhsol.BUS.Category.Category_AdapterBUS;
import com.example.project_doannhanhsol.DAO.API.CallAPI;
import com.example.project_doannhanhsol.DTO.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryDAO {
    public static void getCategories(Activity activity, RecyclerView recyclerview) {
        CallAPI.callApiCategory.GetCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()){
                    Category_AdapterBUS category_adapterBUS=new Category_AdapterBUS(response.body(), activity);
                    recyclerview.setLayoutManager(new LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false));
                    recyclerview.setAdapter(category_adapterBUS);
                }
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e(ERROR_CALL_API_CATEGORY,t.getMessage());

            }
        });
    }
}
