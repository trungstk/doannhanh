package com.example.project_doannhanhsol.BUS.Notify;

import static com.example.project_doannhanhsol.DAO.ProductDetailsDAO.GetProductDetailNotify;
import static com.example.project_doannhanhsol.GUI.MainActivity.chipNavigationBar;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_doannhanhsol.DTO.Notify;
import com.example.project_doannhanhsol.R;

import java.util.List;

public class Notify_AdapterBUS extends RecyclerView.Adapter<Notify_AdapterBUS.Notify_Viewholder> {
    List<Notify>notifies;
    Activity activity;

    public Notify_AdapterBUS(List<Notify> notifies, Activity activity) {
        this.notifies = notifies;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Notify_Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notify,parent,false);
        return new Notify_Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Notify_Viewholder holder, int position) {
        Notify notify=notifies.get(position);

        switch (notify.getStatus()){
            case 1:
                GetProductDetailNotify(notify.getId_productdetails(),holder.ImageView_Product,holder.TextView_Title,activity);
                holder.TextView_Content.setText("Món ăn của bạn đang được nhân viên giao hàng của chúng tôi giao một cách nhanh nhất bạn hay đợi để thưởng thức món ăn ngon nhất của cửa hàng chúng tôi.");
                break;
            case 2:
                GetProductDetailNotify(notify.getId_productdetails(),holder.ImageView_Product,holder.TextView_Title,activity);
                holder.TextView_Content.setText("Cảm ơn bạn đã đặt hàng của chúng tôi, chúc quý khách ngon miện.");
                break;
            case 3:
                GetProductDetailNotify(notify.getId_productdetails(),holder.ImageView_Product,holder.TextView_Title,activity);
                holder.TextView_Content.setText("Có vẻ món ăn này không phù hợp với bạn, bạn hãy chọn món ăn mà bạn thích nhất.");
                holder.TextView_Content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chipNavigationBar.setItemSelected(R.id.menu_HomePage, true);
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (notifies!=null){
            return notifies.size();
        }
        return 0;
    }

    public class Notify_Viewholder extends RecyclerView.ViewHolder {
        ImageView ImageView_Product;
        TextView TextView_Title,TextView_Content,TextView_Datetime;
        public Notify_Viewholder(@NonNull View itemView) {
            super(itemView);
            ImageView_Product=itemView.findViewById(R.id.ImageView_Product);
            TextView_Title=itemView.findViewById(R.id.TextView_Title);
            TextView_Content=itemView.findViewById(R.id.TextView_Content);
            TextView_Datetime=itemView.findViewById(R.id.TextView_Datetime);
        }
    }
}
