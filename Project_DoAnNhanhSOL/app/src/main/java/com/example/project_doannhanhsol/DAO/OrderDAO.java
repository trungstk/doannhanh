package com.example.project_doannhanhsol.DAO;

import static com.example.project_doannhanhsol.BUS.HandlingBUS.IDACCOUNT;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.IDCATEGORY;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.IDPRODUCT;
import static com.example.project_doannhanhsol.DAO.CartDAO.DeleteCart;
import static com.example.project_doannhanhsol.DAO.NotifyDAO.PostNotify;
import static com.example.project_doannhanhsol.GUI.HomePage.CartFragment.getcart_GetTotalMoney;
import static com.example.project_doannhanhsol.GUI.HomePage.CartFragment.getdata;
import static com.example.project_doannhanhsol.GUI.Order.CancelledFragment.getdata_CF;
import static com.example.project_doannhanhsol.GUI.Order.WaitForConfirmationFragment.getdata_WCF;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_doannhanhsol.BUS.Order.Order_AdapterBUS;
import com.example.project_doannhanhsol.DAO.API.CallAPI;
import com.example.project_doannhanhsol.DTO.Cart;
import com.example.project_doannhanhsol.DTO.Notify;
import com.example.project_doannhanhsol.DTO.Order;
import com.example.project_doannhanhsol.DTO.Product;
import com.example.project_doannhanhsol.DTO.ProductDetails;
import com.example.project_doannhanhsol.GUI.Product.ProductDetailsActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDAO {
    public static void Postorder_(TextView Message, TextView PaymentMethod,Activity activity) {
        CallAPI.callApiCart.GetCartAccpunt(IDACCOUNT).enqueue(new Callback<List<Cart>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful()) {
                    for (Cart cart : response.body()) {
                        Order order = new Order(0, cart.getId_Account(), cart.getId_productdetails(), cart.getQuantity(), cart.getTotalMoney(), 0, Message.getText().toString().trim(),
                                PaymentMethod.getText().toString().trim(), java.time.LocalDateTime.now() + "", "Không");
                        Postorders(order,cart);
                        Notify notify=new Notify(0,cart.getId_Account(),cart.getId_productdetails(),0,0);
                        PostNotify(notify);
                        DeleteCart(cart.getId_Cart());
                    }
                    getdata();
                    getcart_GetTotalMoney();
                    activity.onBackPressed();
                }

            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {

            }
        });
    }
    public static void Postorders(Order order,Cart cart){
        CallAPI.callApiOrder.Postorder_(order).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                CallAPI.callApiCart.DeleteCart(cart.getId_Cart()).enqueue(new Callback<Cart>() {
                    @Override
                    public void onResponse(Call<Cart> call, Response<Cart> response) {

                    }

                    @Override
                    public void onFailure(Call<Cart> call, Throwable t) {
                        Log.e("TAB_", t.getMessage());
                    }
                });
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {

            }
        });

    }
    public static void Getorder(int Status, Activity activity, RecyclerView recyclerView, LinearLayout linearLayout) {
        CallAPI.callApiOrder.Getorder(IDACCOUNT, Status).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful()) {
                    recyclerView.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.GONE);
                    Order_AdapterBUS order_adapterBUS = new Order_AdapterBUS(response.body(), activity);
                    recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                    recyclerView.setAdapter(order_adapterBUS);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {

            }
        });
    }

    public static void Putorder_(int id, Order order) {
        CallAPI.callApiOrder.Putorder_(id, order).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                getdata_WCF();
                getdata_CF();
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {

            }
        });
    }
    public static void BuyBackProducts(Order order,Activity activity){
        CallAPI.callApiProductDetails.GetProductDetail(order.getId_productdetails()).enqueue(new Callback<ProductDetails>() {
            @Override
            public void onResponse(Call<ProductDetails> call, Response<ProductDetails> response) {
                IDPRODUCT = response.body().getId_product();
                CallAPI.callApiProduct.GetProduct(response.body().getId_product()).enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {
                        IDCATEGORY = response.body().getId_danhmuc();
                        activity.startActivity(new Intent(activity, ProductDetailsActivity.class));
                    }

                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {

                    }
                });
            }
            @Override
            public void onFailure(Call<ProductDetails> call, Throwable t) {

            }
        });

    }
}
