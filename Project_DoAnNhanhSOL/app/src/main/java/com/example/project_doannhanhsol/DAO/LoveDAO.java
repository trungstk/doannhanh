package com.example.project_doannhanhsol.DAO;


import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONDOWN;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONUP;

import static com.example.project_doannhanhsol.BUS.HandlingBUS.IDACCOUNT;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.IDPRODUCT;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_doannhanhsol.BUS.Love.LoveAdapterBUS;
import com.example.project_doannhanhsol.DAO.API.CallAPI;
import com.example.project_doannhanhsol.DTO.Love;
import com.example.project_doannhanhsol.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoveDAO {
    public static void Checklove(Activity activity, ImageView ImageView_ProductLove) {
      CallAPI.CallAPILove.Checklove(IDACCOUNT,IDPRODUCT).enqueue(new Callback<List<Love>>() {
          @Override
          public void onResponse(Call<List<Love>> call, Response<List<Love>> response) {
              if (response.isSuccessful()){
                  ImageView_ProductLove.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_heart));
                  ImageView_ProductLove.setOnTouchListener(new View.OnTouchListener() {
                      @Override
                      public boolean onTouch(View view, MotionEvent motionEvent) {
                          if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                              ImageView_ProductLove.startAnimation(ANIMATIONUP);
                          } else {
                              if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                                  ImageView_ProductLove.startAnimation(ANIMATIONDOWN);
                                  ImageView_ProductLove.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_heart_1));
                                  DeleteLove(response.body().get(0).getId_love());
                              }
                          }
                          return true;
                      }
                  });
              }else{
                  ImageView_ProductLove.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_heart_1));
                  ImageView_ProductLove.setOnTouchListener(new View.OnTouchListener() {
                      @Override
                      public boolean onTouch(View view, MotionEvent motionEvent) {
                          if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                              ImageView_ProductLove.startAnimation(ANIMATIONUP);
                          } else {
                              if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                                  ImageView_ProductLove.startAnimation(ANIMATIONDOWN);
                                  ImageView_ProductLove.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_heart));
                                  Love love=new Love(0,IDACCOUNT,IDPRODUCT,"không");
                                  PostLove(love);
                              }
                          }
                          return true;
                      }
                  });
              }

          }

          @Override
          public void onFailure(Call<List<Love>> call, Throwable t) {

          }
      });
    }
    public static void PostLove(Love love) {
      CallAPI.CallAPILove.PostLove(love).enqueue(new Callback<Boolean>() {
          @Override
          public void onResponse(Call<Boolean> call, Response<Boolean> response) {

          }

          @Override
          public void onFailure(Call<Boolean> call, Throwable t) {

          }
      });
    }
    public static void DeleteLove(int id) {
      CallAPI.CallAPILove.DeleteLove(id).enqueue(new Callback<Boolean>() {
          @Override
          public void onResponse(Call<Boolean> call, Response<Boolean> response) {

          }

          @Override
          public void onFailure(Call<Boolean> call, Throwable t) {

          }
      });
    }
    public static void checklove(Activity activity, RecyclerView recyclerView, LinearLayout LinearLayout_NoLove){
        CallAPI.CallAPILove.checklove(IDACCOUNT).enqueue(new Callback<List<Love>>() {
            @Override
            public void onResponse(Call<List<Love>> call, Response<List<Love>> response) {
                if (response.isSuccessful()){
                    LinearLayout_NoLove.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    LoveAdapterBUS loveAdapterBUS=new LoveAdapterBUS(response.body(),activity);
                    recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                    recyclerView.setAdapter(loveAdapterBUS);
                }else {
                    LinearLayout_NoLove.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Love>> call, Throwable t) {

            }
        });
    }

}
