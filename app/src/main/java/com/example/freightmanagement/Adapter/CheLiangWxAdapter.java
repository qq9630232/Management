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

import com.example.freightmanagement.Activity.CarWeiXiuInfoActivity;
import com.example.freightmanagement.Bean.CheliangWeiXiuBean;
import com.example.freightmanagement.R;

import java.util.List;

/**
 * Created by songdechuan on 2020/8/6.
 */

public class CheLiangWxAdapter extends RecyclerView.Adapter<CheLiangWxAdapter.TrainingViewHolder> {
    private final LayoutInflater layoutInflater;
    private Context context;
    private List<CheliangWeiXiuBean.DataBean> pages;

    public CheLiangWxAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public TrainingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CheLiangWxAdapter.TrainingViewHolder(layoutInflater.inflate(R.layout.wei_xiu_ji_lu, parent, false));
    }

    @Override
    public void onBindViewHolder(TrainingViewHolder holder, int position) {
        if (pages != null) {
            holder.et_xing_shi_li_cheng.setText(pages.get(position).getMileage()+"");
            holder.et_wei_xiu_dan_wei.setText(pages.get(position).getServicer()+"");
            holder.et_wei_xiu_nei_rong.setText(pages.get(position).getContent()+"");
            holder.et_bu_jian_chang_jia.setText(pages.get(position).getPartMaker()+"");
            holder.tv_wei_xiu_shi_jian.setText(pages.get(position).getTime()+"");
            holder.et_driver_name.setText(pages.get(position).getDriverBo().getCertificateIDBo().getName()+"");
            holder.et_che_pai_hao.setText(pages.get(position).getCarBo().getCertificateDrivingBo().getPlateNo());
            if(position%2==0){
                holder.lin_item.setBackgroundColor(context.getResources().getColor(R.color.blue_ecf3f7));
            }else {
                holder.lin_item.setBackgroundColor(context.getResources().getColor(R.color.white));
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CarWeiXiuInfoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("mileage",  pages.get(position).getMileage().toString());
                    bundle.putSerializable("servicer",  pages.get(position).getServicer());
                    bundle.putSerializable("neirong",  pages.get(position).getContent());
                    bundle.putSerializable("partMaker",  pages.get(position).getPartMaker());
                    bundle.putSerializable("time", pages.get(position).getTime());
                    bundle.putSerializable("imgList",pages.get(position).getPicUrl());
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
        TextView et_xing_shi_li_cheng,et_wei_xiu_dan_wei,et_wei_xiu_nei_rong,et_bu_jian_chang_jia,tv_wei_xiu_shi_jian,et_driver_name,et_che_pai_hao;
        private LinearLayout lin_item;
        private View itemView;

        public TrainingViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            et_xing_shi_li_cheng = itemView.findViewById(R.id.et_xing_shi_li_cheng);
            et_wei_xiu_dan_wei = itemView.findViewById(R.id.et_wei_xiu_dan_wei);
            et_wei_xiu_nei_rong = itemView.findViewById(R.id.et_wei_xiu_nei_rong);
            et_bu_jian_chang_jia = itemView.findViewById(R.id.et_bu_jian_chang_jia);
            tv_wei_xiu_shi_jian = itemView.findViewById(R.id.tv_wei_xiu_shi_jian);
            lin_item = itemView.findViewById(R.id.lin_item);
            et_driver_name = itemView.findViewById(R.id.et_driver_name);
            et_che_pai_hao = itemView.findViewById(R.id.et_che_pai_hao);
        }
    }

    public void setPages(List<CheliangWeiXiuBean.DataBean> data) {
        this.pages = data;
        notifyDataSetChanged();
    }
}
