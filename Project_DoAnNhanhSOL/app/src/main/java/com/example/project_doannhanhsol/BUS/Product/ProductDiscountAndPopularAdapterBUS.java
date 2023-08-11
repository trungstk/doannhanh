package com.example.project_doannhanhsol.BUS.Product;


import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONDOWN;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONUP;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.IDCATEGORY;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.IDPRODUCT;
import static com.example.project_doannhanhsol.DAO.ProductDetailsDAO.GetProductDetails;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.project_doannhanhsol.DTO.Product;
import com.example.project_doannhanhsol.GUI.Product.ProductDetailsActivity;
import com.example.project_doannhanhsol.R;


import java.util.List;

public class ProductDiscountAndPopularAdapterBUS extends RecyclerView.Adapter<ProductDiscountAndPopularAdapterBUS.ProductDiscountAndPopularViewhodler> {
    List<Product> products;
    Activity activity;

    public ProductDiscountAndPopularAdapterBUS(List<Product> products, Activity activity) {
        this.products = products;
        this.activity = activity;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductDiscountAndPopularViewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_discount_and_popular, parent, false);
        return new ProductDiscountAndPopularViewhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDiscountAndPopularViewhodler holder, int position) {
        Product product = products.get(position);
        holder.TextView_NameProduct.setText(product.getNameProduct());
        Glide.with(activity).load(product.getImagelinks()).apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(15))).error(R.drawable.ic_error).into(holder.ImageView_Product);
        GetProductDetails(product.getId_product(), holder.TextView_Price, holder.TextView_Promotionalprice);
        holder.LinearLayout_Product.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    holder.LinearLayout_Product.startAnimation(ANIMATIONUP);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        holder.LinearLayout_Product.startAnimation(ANIMATIONDOWN);
                        IDPRODUCT = product.getId_product();
                        IDCATEGORY = product.getId_danhmuc();
                        activity.startActivity(new Intent(activity, ProductDetailsActivity.class));
                    }
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (products != null) {
            return products.size();
        }
        return 0;
    }

    public class ProductDiscountAndPopularViewhodler extends RecyclerView.ViewHolder {
        ImageView ImageView_Product;
        TextView TextView_NameProduct, TextView_Price, TextView_Promotionalprice;
        LinearLayout LinearLayout_Product;

        public ProductDiscountAndPopularViewhodler(@NonNull View itemView) {
            super(itemView);
            ImageView_Product = itemView.findViewById(R.id.ImageView_Product);
            TextView_NameProduct = itemView.findViewById(R.id.TextView_NameProduct);
            TextView_Price = itemView.findViewById(R.id.TextView_Price);
            TextView_Promotionalprice = itemView.findViewById(R.id.TextView_Promotionalprice);
            LinearLayout_Product = itemView.findViewById(R.id.LinearLayout_Product);
        }
    }
}
