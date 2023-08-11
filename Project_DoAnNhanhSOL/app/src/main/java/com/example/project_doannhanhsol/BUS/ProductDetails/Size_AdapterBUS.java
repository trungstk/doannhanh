package com.example.project_doannhanhsol.BUS.ProductDetails;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_doannhanhsol.DTO.ProductDetails;
import com.example.project_doannhanhsol.R;

import java.util.List;

public class Size_AdapterBUS extends RecyclerView.Adapter<Size_AdapterBUS.Size_viewhodler>{
    List<ProductDetails> list;
    Activity activity;
    int selectedPos = 0;
    Get_Money get_money;

    public Size_AdapterBUS(List<ProductDetails> list, Activity activity, Get_Money get_money) {
        this.list = list;
        this.activity = activity;
        this.get_money = get_money;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Size_viewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_size, parent, false);
        return new Size_viewhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Size_viewhodler holder, int position) {
        ProductDetails ct_san_pham = list.get(position);
        holder.textView_Size.setChecked(position == selectedPos);
        holder.textView_Size.setText(ct_san_pham.getSize());
        if (holder.textView_Size.isChecked()) {
            holder.card_view.setCardBackgroundColor(activity.getResources().getColor(R.color.color_orange));
            holder.textView_Size.setTextColor(activity.getResources().getColor(R.color.white));
            holder.textView_Size.setButtonTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.white)));

            get_money.Get_Money(ct_san_pham.getId_productdetails(),ct_san_pham.getPrice(),ct_san_pham.getPromotionalprice());
        } else {
            holder.textView_Size.setButtonTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.black)));
            holder.card_view.setCardBackgroundColor(activity.getResources().getColor(R.color.white));
            holder.textView_Size.setTextColor(activity.getResources().getColor(R.color.black));
        }
        holder.textView_Size.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    selectedPos = holder.getAdapterPosition();
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class Size_viewhodler extends RecyclerView.ViewHolder {
        CardView card_view;
        RadioButton textView_Size;
        public Size_viewhodler(@NonNull View itemView) {
            super(itemView);
            card_view = itemView.findViewById(R.id.card_view);
            textView_Size = itemView.findViewById(R.id.textView_Size);

        }
    }
}
