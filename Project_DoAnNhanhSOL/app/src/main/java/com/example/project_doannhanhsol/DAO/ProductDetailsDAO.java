package com.example.project_doannhanhsol.DAO;


import static com.example.project_doannhanhsol.BUS.HandlingBUS.CURRENCYVN;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.ERROR_CALL_API_PRODUCTDETAILS;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.Notify;

import android.app.Activity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.project_doannhanhsol.BUS.ProductDetails.Get_Money;
import com.example.project_doannhanhsol.BUS.ProductDetails.Size_AdapterBUS;
import com.example.project_doannhanhsol.DAO.API.CallAPI;
import com.example.project_doannhanhsol.DTO.Cart;
import com.example.project_doannhanhsol.DTO.Product;
import com.example.project_doannhanhsol.DTO.ProductDetails;
import com.example.project_doannhanhsol.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsDAO {
    public static void GetProductDetails(int Id_product, TextView TextView_Price, TextView TextView_Promotionalprice) {
        CallAPI.callApiProductDetails.GetProductDetails(Id_product).enqueue(new Callback<List<ProductDetails>>() {
            @Override
            public void onResponse(Call<List<ProductDetails>> call, Response<List<ProductDetails>> response) {
                if (response.isSuccessful()) {
                    int Position = response.body().size() - 1;
                    if (response.body().get(0).getPromotionalprice() == 0 || response.body().get(Position).getPromotionalprice() == 0) {
                        TextView_Price.setText(CURRENCYVN.format(response.body().get(0).getPrice()) + "-" + CURRENCYVN.format(response.body().get(Position).getPrice()));
                        TextView_Promotionalprice.setVisibility(View.INVISIBLE);
                    } else {
                        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(CURRENCYVN.format(response.body().get(0).getPrice()) + "-" + CURRENCYVN.format(response.body().get(Position).getPrice()));
                        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
                        spannableStringBuilder.setSpan(strikethroughSpan, 0, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        TextView_Price.setText(spannableStringBuilder);
                        TextView_Promotionalprice.setText(CURRENCYVN.format(response.body().get(0).getPromotionalprice()) + "-" + CURRENCYVN.format(response.body().get(Position).getPromotionalprice()));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ProductDetails>> call, Throwable t) {
                Log.e(ERROR_CALL_API_PRODUCTDETAILS, t.getMessage());
            }
        });
    }

    public static void get_Size(Activity activity, RecyclerView recyclerView, int Id_product, Get_Money get_money) {
        CallAPI.callApiProductDetails.GetProductDetails(Id_product).enqueue(new Callback<List<ProductDetails>>() {
            @Override
            public void onResponse(Call<List<ProductDetails>> call, Response<List<ProductDetails>> response) {
                if (response.isSuccessful()) {
                    Size_AdapterBUS size_adapterBUS = new Size_AdapterBUS(response.body(), activity, get_money);
                    recyclerView.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));
                    recyclerView.setAdapter(size_adapterBUS);
                }
            }

            @Override
            public void onFailure(Call<List<ProductDetails>> call, Throwable t) {

            }
        });
    }

    public static void GetProductDetail(int id, TextView TextView_Promotionalprice, TextView TextView_Price, TextView TextView_Size, ImageView ImageView_Product, TextView TextView_NameProduct, Activity activity) {
        CallAPI.callApiProductDetails.GetProductDetail(id).enqueue(new Callback<ProductDetails>() {
            @Override
            public void onResponse(Call<ProductDetails> call, Response<ProductDetails> response) {
                if (response.isSuccessful()) {
                    ProductDetails productDetails = response.body();
                    if (productDetails.getPromotionalprice() == 0) {
                        TextView_Price.setText(CURRENCYVN.format(productDetails.getPrice()));
                        TextView_Promotionalprice.setVisibility(View.GONE);
                    } else {
                        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(CURRENCYVN.format(productDetails.getPrice()));
                        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
                        spannableStringBuilder.setSpan(strikethroughSpan, 0, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        TextView_Price.setText(spannableStringBuilder);
                        TextView_Promotionalprice.setText(CURRENCYVN.format(productDetails.getPromotionalprice()));
                    }
                    TextView_Size.setText("Khích cỡ: " + productDetails.getSize());
                    CallAPI.callApiProduct.GetProduct(productDetails.getId_product()).enqueue(new Callback<Product>() {
                        @Override
                        public void onResponse(Call<Product> call, Response<Product> response) {
                            Glide.with(activity).load(response.body().getImagelinks()).apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(15))).error(R.drawable.ic_error).into(ImageView_Product);
                            TextView_NameProduct.setText(response.body().getNameProduct());
                        }

                        @Override
                        public void onFailure(Call<Product> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ProductDetails> call, Throwable t) {

            }
        });
    }

    public static void GetProductDetailNotify(int id, ImageView ImageView_Product, TextView TextView_NameProduct, Activity activity) {
        CallAPI.callApiProductDetails.GetProductDetail(id).enqueue(new Callback<ProductDetails>() {
            @Override
            public void onResponse(Call<ProductDetails> call, Response<ProductDetails> response) {
                if (response.isSuccessful()) {
                    CallAPI.callApiProduct.GetProduct(response.body().getId_product()).enqueue(new Callback<Product>() {
                        @Override
                        public void onResponse(Call<Product> call, Response<Product> response) {
                            Glide.with(activity).load(response.body().getImagelinks()).apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(15))).error(R.drawable.ic_error).into(ImageView_Product);
                            TextView_NameProduct.setText("Món ăn " + response.body().getNameProduct());
                        }

                        @Override
                        public void onFailure(Call<Product> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ProductDetails> call, Throwable t) {

            }
        });
    }

    public static void GetDetailNotify(int id,Activity activity,String content) {
        CallAPI.callApiProductDetails.GetProductDetail(id).enqueue(new Callback<ProductDetails>() {
            @Override
            public void onResponse(Call<ProductDetails> call, Response<ProductDetails> response) {
                if (response.isSuccessful()) {
                    Log.e("s",response.body().getSize()+"");
                    CallAPI.callApiProduct.GetProduct(response.body().getId_product()).enqueue(new Callback<Product>() {
                        @Override
                        public void onResponse(Call<Product> call, Response<Product> response) {
                            if (response.isSuccessful()){
                                Log.e("ss",response.body().getNameProduct());
                                Notify(activity,"Món ăn "+response.body().getNameProduct()+content);
                            }
                        }

                        @Override
                        public void onFailure(Call<Product> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ProductDetails> call, Throwable t) {

            }
        });
    }

}
