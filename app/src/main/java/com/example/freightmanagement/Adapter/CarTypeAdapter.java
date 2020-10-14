package com.example.freightmanagement.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.freightmanagement.Bean.CheliangJcBean;
import com.example.freightmanagement.R;

import java.util.List;

public class CarTypeAdapter  extends RecyclerView.Adapter<CarTypeAdapter.ViewHolder> {
    private final LayoutInflater layoutInflater;
    private Context context;
    private List<CheliangJcBean.DataBean.CompleteBosBean> pages;

    public CarTypeAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public CarTypeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CarTypeAdapter.ViewHolder(layoutInflater.inflate(R.layout.item_car_type, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (pages != null) {
            holder.mCheckBox.setText(pages.get(position).getCheckData().getItem());
            int checkState = pages.get(position).getState();
            if(checkState == 1){
                holder.mCheckBox.setChecked(true);
            }else {
                holder.mCheckBox.setChecked(false);
            }


        }
    }

    @Override
    public int getItemCount() {
        return  pages != null && pages.size() > 0 ? pages.size() : 0;

    }

    public void setPages(List<CheliangJcBean.DataBean.CompleteBosBean> list) {
        this.pages = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View itemView;
        CheckBox mCheckBox;
        TextView mTvOther;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            mCheckBox = itemView.findViewById(R.id.checkbox);
            mTvOther = itemView.findViewById(R.id.tv_other);
        }
    }

}
