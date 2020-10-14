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

import com.example.freightmanagement.Activity.CarBYInfoActivity;
import com.example.freightmanagement.Bean.CheliangByBean;
import com.example.freightmanagement.R;

import java.util.List;

/**
 * Created by songdechuan on 2020/8/6.
 */

public class CheLiangByAdapter extends RecyclerView.Adapter<CheLiangByAdapter.TrainingViewHolder> {
    private final LayoutInflater layoutInflater;
    private Context context;
    private List<CheliangByBean.DataBean> pages;

    public CheLiangByAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public TrainingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CheLiangByAdapter.TrainingViewHolder(layoutInflater.inflate(R.layout.bao_yang_ji_lu, parent, false));
    }

    @Override
    public void onBindViewHolder(TrainingViewHolder holder, int position) {
        if (pages != null) {
//            holder.et_che_pai_hao.setText(pages.get(position).()+"");
            holder.et_xing_shi_li_cheng.setText(pages.get(position).getMileage()+"");
            holder.et_bao_yang_nei_rong.setText(pages.get(position).getContent()+"");
            holder.tv_bao_yang_shi_jian.setText(pages.get(position).getTime()+"");
            holder.et_driver_name.setText(pages.get(position).getDriverBo().getCertificateIDBo().getName());
            holder.et_che_pai_hao.setText(pages.get(position).getCarBo().getCertificateDrivingBo().getPlateNo());

            if(position%2==0){
                holder.lin_item.setBackgroundColor(context.getResources().getColor(R.color.blue_ecf3f7));
            }else {
                holder.lin_item.setBackgroundColor(context.getResources().getColor(R.color.white));
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CarBYInfoActivity.class);
                    Bundle bundle = new Bundle();
                    final CheliangByBean.DataBean dateBean = pages.get(position);
//                    bundle.putSerializable((""),dateBean.get);
                    bundle.putSerializable("mileage",  dateBean.getMileage());
                    bundle.putSerializable("content",  dateBean.getContent());
                    bundle.putSerializable("time", dateBean.getTime());
                    bundle.putSerializable("imgList",dateBean.getPicUrl());
                    bundle.putSerializable("driverName",pages.get(position).getDriverBo().getCertificateIDBo().getName());
                    bundle.putSerializable("plateNo",pages.get(position).getCarBo().getCertificateDrivingBo().getPlateNo());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return  pages != null && pages.size() > 0 ? pages.size() : 0;

    }

    public class TrainingViewHolder extends RecyclerView.ViewHolder {
        TextView et_xing_shi_li_cheng,et_bao_yang_nei_rong,tv_bao_yang_shi_jian,et_driver_name,et_che_pai_hao;
        private View itemView;
        private LinearLayout lin_item;
        public TrainingViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            et_xing_shi_li_cheng = itemView.findViewById(R.id.et_xing_shi_li_cheng);
            et_bao_yang_nei_rong = itemView.findViewById(R.id.et_bao_yang_nei_rong);
            tv_bao_yang_shi_jian = itemView.findViewById(R.id.tv_bao_yang_shi_jian);
            lin_item = itemView.findViewById(R.id.lin_item);
            et_driver_name = itemView.findViewById(R.id.et_driver_name);
            et_che_pai_hao = itemView.findViewById(R.id.et_che_pai_hao);
        }
    }

    public void setPages(List<CheliangByBean.DataBean> data) {
        this.pages = data;
        notifyDataSetChanged();
    }
}
