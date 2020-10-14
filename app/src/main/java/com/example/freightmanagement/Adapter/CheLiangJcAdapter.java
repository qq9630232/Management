package com.example.freightmanagement.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.freightmanagement.Activity.CarCheckedInfoActivity;
import com.example.freightmanagement.Bean.CheliangJcBean;
import com.example.freightmanagement.R;

import java.io.Serializable;
import java.util.List;

/**
 * Created by songdechuan on 2020/8/6.
 */

public class CheLiangJcAdapter extends RecyclerView.Adapter<CheLiangJcAdapter.TrainingViewHolder> {
    private final LayoutInflater layoutInflater;
    private Context context;
    private List<CheliangJcBean.DataBean> pages;

    public CheLiangJcAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public TrainingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CheLiangJcAdapter.TrainingViewHolder(layoutInflater.inflate(R.layout.cheliang_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(TrainingViewHolder holder, int position) {
        if (pages != null) {
            CheliangJcBean.DataBean.CarBoBean carBo = pages.get(position).getCarBo();
            CheliangJcBean.DataBean.DriverBoBean driverBo = pages.get(position).getDriverBo();
            holder.tv_date.setText(pages.get(position).getCheckDateTime()+"");
            holder.tv_car_num.setText(carBo.getCertificateDrivingBo().getPlateNo());
            holder.tv_person.setText(driverBo.getCertificateDriverBo().getName());
            int type = pages.get(position).getType();
            String content = "";
            if(type == 1){
                content = "出车前";
            }else if(type == 2){
                content = "行车中";
            }else {
                content = "收车后";
            }
            holder.tv_car_type.setText(content);
            final List<CheliangJcBean.DataBean.CompleteBosBean> completeBos = pages.get(position).getCompleteBos();
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CarCheckedInfoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list", (Serializable)completeBos);
                    bundle.putSerializable("imgList",pages.get(position).getPicUrl());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

            if(position%2==0){
                holder.lin_item.setBackgroundColor(context.getResources().getColor(R.color.blue_ecf3f7));
            }else {
                holder.lin_item.setBackgroundColor(context.getResources().getColor(R.color.white));
            }
        }
    }

    @Override
    public int getItemCount() {
        return  pages != null && pages.size() > 0 ? pages.size() : 0;

    }

    public class TrainingViewHolder extends RecyclerView.ViewHolder {
        TextView tv_date;
        TextView tv_person;
        TextView tv_car_num;
        TextView tv_car_type;
        private LinearLayout lin_item;
        private View itemView;

        public TrainingViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_person = itemView.findViewById(R.id.tv_person);
            tv_car_num = itemView.findViewById(R.id.tv_car_num);
            tv_car_type = itemView.findViewById(R.id.tv_car_type);
            lin_item = itemView.findViewById(R.id.lin_item);

        }
    }

    public void setPages(List<CheliangJcBean.DataBean> data) {
        this.pages = data;
        notifyDataSetChanged();
    }
}
