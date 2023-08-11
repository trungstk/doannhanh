package com.example.project_doannhanhsol.BUS.Product;


import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONDOWN;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONUP;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.IDCATEGORY;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.IDPRODUCT;
import static com.example.project_doannhanhsol.DAO.ProductDetailsDAO.GetProductDetails;

import android.annotation.SuppressLint;
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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import com.example.project_doannhanhsol.DTO.Product;
import com.example.project_doannhanhsol.DTO.ProductDetails;
import com.example.project_doannhanhsol.GUI.Product.ProductDetailsActivity;
import com.example.project_doannhanhsol.R;

import java.util.List;

public class Product_AdapterBUS extends RecyclerView.Adapter<Product_AdapterBUS.Product_ViewHolder> {
    List<Product> Products;
    Activity activity;

    public Product_AdapterBUS(List<Product> products, Activity activity) {
        Products = products;
        this.activity = activity;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Product_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new Product_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Product_ViewHolder holder, int position) {
        Product product = Products.get(position);
        Glide.with(activity).load(product.getImagelinks()).apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(15))).error(R.drawable.ic_error).into(holder.ImageView_Product);
        holder.TextView_NameProduct.setText(product.getNameProduct());
        holder.TextView_Content.setText(product.getContent());
        holder.TextView_Scale.setText(product.getSales() + "");
        holder.TextView_View.setText(product.getViews() + "");
        GetProductDetails(product.getId_product(), holder.TextView_Price, holder.TextView_Promotionalprice);
        holder.LinearLayout_Product.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
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
        if (Products != null) {
            return Products.size();
        }
        return 0;
    }

    public class Product_ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout LinearLayout_Product;
        ImageView ImageView_Product;
        TextView TextView_NameProduct, TextView_Content, TextView_Price, TextView_Promotionalprice, TextView_Scale, TextView_View;

        public Product_ViewHolder(@NonNull View itemView) {
            super(itemView);
            LinearLayout_Product = itemView.findViewById(R.id.LinearLayout_Product);
            ImageView_Product = itemView.findViewById(R.id.ImageView_Product);
            TextView_NameProduct = itemView.findViewById(R.id.TextView_NameProduct);
            TextView_Content = itemView.findViewById(R.id.TextView_Content);
            TextView_Price = itemView.findViewById(R.id.TextView_Price);
            TextView_Promotionalprice = itemView.findViewById(R.id.TextView_Promotionalprice);
            TextView_Scale = itemView.findViewById(R.id.TextView_Scale);
            TextView_View = itemView.findViewById(R.id.TextView_View);
        }
    }
}
