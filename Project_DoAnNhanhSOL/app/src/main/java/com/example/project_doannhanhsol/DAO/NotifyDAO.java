package com.example.project_doannhanhsol.DAO;

import static com.example.project_doannhanhsol.BUS.HandlingBUS.IDACCOUNT;
import static com.example.project_doannhanhsol.DAO.ProductDetailsDAO.GetDetailNotify;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.project_doannhanhsol.BUS.Notify.Notify_AdapterBUS;
import com.example.project_doannhanhsol.DAO.API.CallAPI;
import com.example.project_doannhanhsol.DTO.Notify;
import com.example.project_doannhanhsol.DTO.Order;
import com.example.project_doannhanhsol.DTO.Product;
import com.example.project_doannhanhsol.DTO.ProductDetails;
import com.example.project_doannhanhsol.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotifyDAO {
    public static void PostNotify(Notify notify) {
        CallAPI.CallAPINotify.PostNotify(notify).enqueue(new Callback<Notify>() {
            @Override
            public void onResponse(Call<Notify> call, Response<Notify> response) {

            }

            @Override
            public void onFailure(Call<Notify> call, Throwable t) {

            }
        });
    }

    public static void GetNotify(Activity activity, RecyclerView recyclerView) {
        CallAPI.CallAPINotify.GetNotifys(IDACCOUNT).enqueue(new Callback<List<Notify>>() {
            @Override
            public void onResponse(Call<List<Notify>> call, Response<List<Notify>> response) {
                Notify_AdapterBUS notify_adapterBUS = new Notify_AdapterBUS(response.body(), activity);
                recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                recyclerView.setAdapter(notify_adapterBUS);
            }

            @Override
            public void onFailure(Call<List<Notify>> call, Throwable t) {

            }
        });
    }

    public static void PutNotify(int id, Notify notify) {
        CallAPI.CallAPINotify.PutNotify(id, notify).enqueue(new Callback<Notify>() {
            @Override
            public void onResponse(Call<Notify> call, Response<Notify> response) {

            }

            @Override
            public void onFailure(Call<Notify> call, Throwable t) {

            }
        });
    }

    public static void GetNotifies(Activity activity) {
        CallAPI.CallAPINotify.GetNotifies(IDACCOUNT).enqueue(new Callback<List<Notify>>() {
            @Override
            public void onResponse(Call<List<Notify>> call, Response<List<Notify>> response) {
                if (response.isSuccessful()) {
                    for (Notify notify : response.body()) {
                        Notify notify1 = new Notify(notify.getId_notify(), notify.getId_Account(), notify.getId_productdetails(), notify.getStatus(), 1);
                        switch (notify.getStatus()) {
                            case 1:
                                PutNotify(notify.getId_notify(), notify1);
                                GetDetailNotify(notify.getId_productdetails(), activity, " của bạn đang trên đường đến với bạn, bạn hãy chuẩn bị để có bữa ăn ngon miệng");
                                break;
                            case 2:
                                PutNotify(notify.getId_notify(), notify1);
                                GetDetailNotify(notify.getId_productdetails(), activity, " của bạn đã được giao tới bạn, cửa hàng chúng tối chúc bạn có một bữa ăn ngon miệng");
                                break;
                            case 3:
                                PutNotify(notify.getId_notify(), notify1);
                                GetDetailNotify(notify.getId_productdetails(), activity, " của bạn đã được hủy!");
                                break;
                        }

                    }
                }

            }

            @Override
            public void onFailure(Call<List<Notify>> call, Throwable t) {

            }
        });
    }
}
