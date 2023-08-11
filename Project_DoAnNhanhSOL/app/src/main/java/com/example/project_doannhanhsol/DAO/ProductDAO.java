package com.example.project_doannhanhsol.DAO;

import static com.example.project_doannhanhsol.BUS.HandlingBUS.ERROR_CALL_API_PRODUCT;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.IDCATEGORY;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import com.example.project_doannhanhsol.BUS.Product.ProductDiscountAndPopularAdapterBUS;
import com.example.project_doannhanhsol.BUS.Product.Product_AdapterBUS;
import com.example.project_doannhanhsol.DAO.API.CallAPI;
import com.example.project_doannhanhsol.DTO.Product;
import com.example.project_doannhanhsol.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDAO {

    public static void GetProductDiscount(Activity activity, RecyclerView recyclerView) {
        CallAPI.callApiProduct.GetProductDiscount().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    ProductDiscountAndPopularAdapterBUS category_adapterBUS = new ProductDiscountAndPopularAdapterBUS(response.body(), activity);
                    recyclerView.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false));
                    recyclerView.setAdapter(category_adapterBUS);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(ERROR_CALL_API_PRODUCT, t.getMessage());
            }
        });
    }

    public static void GetProductPopular(Activity activity, RecyclerView recyclerView) {
        CallAPI.callApiProduct.GetProductPopular().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    ProductDiscountAndPopularAdapterBUS category_adapterBUS = new ProductDiscountAndPopularAdapterBUS(response.body(), activity);
                    recyclerView.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false));
                    recyclerView.setAdapter(category_adapterBUS);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(ERROR_CALL_API_PRODUCT, t.getMessage());
            }
        });
    }

    public static void GetProducts(Activity activity, RecyclerView recyclerView) {
        CallAPI.callApiProduct.GetProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    Product_AdapterBUS productAdapterBUS = new Product_AdapterBUS(response.body(), activity);
                    recyclerView.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));
                    recyclerView.setAdapter(productAdapterBUS);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(ERROR_CALL_API_PRODUCT, t.getMessage());
            }
        });
    }
    public static void GetProductLike(Activity activity, RecyclerView recyclerView) {
        CallAPI.callApiProduct.GetProductLike().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    Product_AdapterBUS productAdapterBUS = new Product_AdapterBUS(response.body(), activity);
                    recyclerView.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));
                    recyclerView.setAdapter(productAdapterBUS);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(ERROR_CALL_API_PRODUCT, t.getMessage());
            }
        });
    }

    public static void GetProduct(Activity activity, int id, ImageView ImageView_Product, TextView TextView_NameProduct, TextView TextView_Content, TextView TextView_Scale, TextView TextView_View) {
        CallAPI.callApiProduct.GetProduct(id).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Product product = response.body();
                Glide.with(activity).load(product.getImagelinks())
                        .apply(new RequestOptions().transform(new CenterCrop())
                                .transform(new RoundedCorners(15))).error(R.drawable.ic_error).into(ImageView_Product);
                TextView_NameProduct.setText(product.getNameProduct());
                TextView_Content.setText(product.getContent());
                TextView_Scale.setText(product.getSales() + "");
                TextView_View.setText(product.getViews() + "");
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
    }
    public static void GetProduct1(Activity activity, int id, ImageView ImageView_Product,
                                   TextView TextView_NameProduct, TextView TextView_Content, TextView TextView_Scale, TextView TextView_View,TextView IDCategory) {
        CallAPI.callApiProduct.GetProduct(id).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Product product = response.body();
                Glide.with(activity).load(product.getImagelinks())
                        .apply(new RequestOptions().transform(new CenterCrop())
                                .transform(new RoundedCorners(15))).error(R.drawable.ic_error).into(ImageView_Product);
                TextView_NameProduct.setText(product.getNameProduct());
                TextView_Content.setText(product.getContent());
                TextView_Scale.setText(product.getSales() + "");
                TextView_View.setText(product.getViews() + "");
                IDCategory.setText(product.getId_danhmuc()+"");

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
    }
    public static void GetProductsPage(Activity activity, RecyclerView recyclerView, int PageNumber, List<Product> products, ProgressBar progressBar) {
        CallAPI.callApiProduct.GetProductsPage(PageNumber, 10).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                progressBar.setVisibility(View.GONE);
                for (Product product : response.body()) {
                    products.add(new Product(product.getId_product(), product.getId_danhmuc(), product.getSales(), product.getViews()
                            , product.getNameProduct(), product.getContent(), product.getImagelinks(), product.getJoinDate()));
                }
                Product_AdapterBUS productAdapterBUS = new Product_AdapterBUS(products, activity);
                recyclerView.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));
                recyclerView.setAdapter(productAdapterBUS);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    public static void GetSearchProduct(Activity activity, RecyclerView recyclerView, String search, TextView TextView_ERRORSearch) {
        CallAPI.callApiProduct.GetSearchProduct(search).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    TextView_ERRORSearch.setVisibility(View.GONE);
                    Product_AdapterBUS productAdapterBUS = new Product_AdapterBUS(response.body(), activity);
                    recyclerView.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));
                    recyclerView.setAdapter(productAdapterBUS);
                } else {
                    Product_AdapterBUS productAdapterBUS = new Product_AdapterBUS(response.body(), activity);
                    recyclerView.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));
                    recyclerView.setAdapter(productAdapterBUS);
                    TextView_ERRORSearch.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    public static void GetProductCategory(Activity activity, RecyclerView recyclerView, int PageNumber, int id_category, List<Product> products, ProgressBar progressBar) {
        CallAPI.callApiProduct.GetProductCategory(PageNumber, 10, id_category).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                progressBar.setVisibility(View.GONE);
                for (Product product : response.body()) {
                    products.add(new Product(product.getId_product(), product.getId_danhmuc(), product.getSales(), product.getViews()
                            , product.getNameProduct(), product.getContent(), product.getImagelinks(), product.getJoinDate()));
                }
                Product_AdapterBUS productAdapterBUS = new Product_AdapterBUS(products, activity);
                recyclerView.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));
                recyclerView.setAdapter(productAdapterBUS);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    public static void GetSearchProductCategory(Activity activity, RecyclerView recyclerView, String search, int id_category, TextView TextView_ERRORSearch) {
        CallAPI.callApiProduct.GetSearchProductCategory(search, id_category).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    TextView_ERRORSearch.setVisibility(View.GONE);
                    Product_AdapterBUS productAdapterBUS = new Product_AdapterBUS(response.body(), activity);
                    recyclerView.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));
                    recyclerView.setAdapter(productAdapterBUS);
                } else {
                    Product_AdapterBUS productAdapterBUS = new Product_AdapterBUS(response.body(), activity);
                    recyclerView.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));
                    recyclerView.setAdapter(productAdapterBUS);
                    TextView_ERRORSearch.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }
}
