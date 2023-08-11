package com.example.project_doannhanhsol.DAO;

import static com.example.project_doannhanhsol.BUS.HandlingBUS.CURRENCYVN;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.IDACCOUNT;
import static com.example.project_doannhanhsol.GUI.HomePage.CartFragment.getcart_GetTotalMoney;
import static com.example.project_doannhanhsol.GUI.HomePage.CartFragment.getdata;
import static com.example.project_doannhanhsol.GUI.MainActivity.chipNavigationBar;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_doannhanhsol.BUS.Cart.Cart_AdapterBUS;
import com.example.project_doannhanhsol.BUS.Cart.CheckoutCart_AdapterBUS;
import com.example.project_doannhanhsol.DAO.API.CallAPI;
import com.example.project_doannhanhsol.DTO.Cart;
import com.example.project_doannhanhsol.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartDAO {
    public static void PostCart(Cart cart) {
        CallAPI.callApiCart.PostCart(cart).enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful()) {
                    Cart cart1 = response.body().get(0);
                    Cart cart_ = new Cart(cart1.getId_Cart(),
                            cart1.getId_Account(), cart1.getId_productdetails(),
                            cart1.getQuantity() + cart.getQuantity(),
                            cart1.getTotalMoney() + cart.getTotalMoney(), cart1.getNotes());
                    PutCart(response.body().get(0).getId_Cart(), cart_);
                }
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {

            }
        });
    }

    public static void PutCart(int id, Cart cart) {
        CallAPI.callApiCart.PutCart(id, cart).enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {

            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {

            }
        });
    }

    public static void PutCart1(int id, Cart cart) {
        CallAPI.callApiCart.PutCart(id, cart).enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                getcart_GetTotalMoney();
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {

            }
        });
    }

    public static void GetCartAccpunt(RecyclerView recyclerView, Activity activity, LinearLayout LinearLayout_BuyProduct, LinearLayout LinearLayout_Cart, TextView TextView_Amount) {
        CallAPI.callApiCart.GetCartAccpunt(IDACCOUNT).enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful()) {
                    LinearLayout_Cart.setVisibility(View.VISIBLE);
                    LinearLayout_BuyProduct.setVisibility(View.GONE);
                    TextView_Amount.setText(response.body().size() + "");

                    recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                    Cart_AdapterBUS cart_adapterBUS = new Cart_AdapterBUS(response.body(), activity, recyclerView);
                    recyclerView.setAdapter(cart_adapterBUS);
                } else {
                    LinearLayout_BuyProduct.setVisibility(View.VISIBLE);
                    LinearLayout_Cart.setVisibility(View.GONE);
                    TextView_Amount.setText("0");
                }

            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {

            }
        });
    }

    public static void GetCartorder(RecyclerView recyclerView, Activity activity, TextView TextView_Amount) {
        CallAPI.callApiCart.GetCartAccpunt(IDACCOUNT).enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful()) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                    CheckoutCart_AdapterBUS cart_adapterBUS = new CheckoutCart_AdapterBUS(response.body(), activity);
                    recyclerView.setAdapter(cart_adapterBUS);
                    TextView_Amount.setText(response.body().size() + "");
                }

            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {

            }
        });
    }

    public static void DeleteCart(int id) {
        CallAPI.callApiCart.DeleteCart(id).enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                getdata();
                getcart_GetTotalMoney();
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Log.e("TAB_", t.getMessage());
            }
        });
    }

    public static void GetTotalMoney(TextView TextView_TotalMoney) {
        CallAPI.callApiCart.GetTotalMoney(IDACCOUNT).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    TextView_TotalMoney.setText(CURRENCYVN.format(Integer.parseInt(response.body())));
                } else {
                    TextView_TotalMoney.setText(CURRENCYVN.format(0));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public static void GetCartAmount() {
        CallAPI.callApiCart.GetCartAccpunt(IDACCOUNT).enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful()) {
                    chipNavigationBar.showBadge(R.id.menu_Cart, response.body().size());
                }else {
                    chipNavigationBar.dismissBadge(R.id.menu_Cart);
                }
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {

            }
        });
    }
}
