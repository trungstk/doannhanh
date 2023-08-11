package com.example.project_doannhanhsol.BUS.Category;


import static com.example.project_doannhanhsol.BUS.HandlingBUS.IDCATEGORY;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.IDPRODUCT;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.NAMECATEGORY;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project_doannhanhsol.DTO.Category;
import com.example.project_doannhanhsol.GUI.Product.CategoryProductActivity;
import com.example.project_doannhanhsol.R;

import java.util.List;

public class Category_AdapterBUS extends RecyclerView.Adapter<Category_AdapterBUS.Category_ViewHolder> {
    List<Category> list_Category;
    Activity activity;

    public Category_AdapterBUS(List<Category> list_Category, Activity activity) {
        this.list_Category = list_Category;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Category_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new Category_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Category_ViewHolder holder, int position) {
        Category category = list_Category.get(position);
        Glide.with(activity).load(category.getPictureCategory()).error(R.drawable.ic_address_location).into(holder.ImageView_Category);
        holder.TextView_NameCategory.setText(category.getNameCategory());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IDCATEGORY = category.getId_Category();
                NAMECATEGORY = category.getNameCategory();
                activity.startActivity(new Intent(activity, CategoryProductActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list_Category != null) {
            return list_Category.size();
        }
        return 0;
    }

    public class Category_ViewHolder extends RecyclerView.ViewHolder {
        ImageView ImageView_Category;
        TextView TextView_NameCategory;

        public Category_ViewHolder(@NonNull View itemView) {
            super(itemView);
            ImageView_Category = itemView.findViewById(R.id.ImageView_Category);
            TextView_NameCategory = itemView.findViewById(R.id.TextView_NameCategory);
        }
    }
}
