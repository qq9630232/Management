package com.example.freightmanagement.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freightmanagement.Base.BaseApiConstants;
import com.example.freightmanagement.Bean.ContractBean;
import com.example.freightmanagement.Bean.DriverListBean;
import com.example.freightmanagement.R;
import com.example.freightmanagement.Utils.Network.OnRequestResultForCommon;
import com.example.freightmanagement.Utils.Network.RestApi;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by songdechuan on 2020/8/6.
 */

public class DriverListAdapter extends RecyclerView.Adapter<DriverListAdapter.ViewHolder> {
    private final LayoutInflater layoutInflater;
    private Context context;
    private List<DriverListBean.DataBean> data;
    private static int TAG_VIEW = 0;
    private String TAG = "GiftAdapter";
    private HashMap<Integer, Boolean> states = new HashMap<>();
    private boolean isBind;
    private long startTime = 0;
    private long endTime = 0;

    // 利用接口 -> 给RecyclerView设置点击事件
    private ItemClickListener mItemClickListener ;



    public interface ItemClickListener{
        void onItemClick(int position) ;
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }
    // 昌电机
    private ItemLongClickListener mItemLongClickListener ;
    public interface ItemLongClickListener{
        void onItemLongClick(int position) ;
    }
    public void setOnItemLongClickListener(ItemLongClickListener itemLongClickListener){
        this.mItemLongClickListener = itemLongClickListener ;

    }
    public DriverListAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         return new DriverListAdapter.ViewHolder(layoutInflater.inflate(R.layout.item_driver_info, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        int driverId = data.get(position).getId();
        RestApi.getInstance().get(BaseApiConstants.API_CONTRACT_GET_BY_DRIVER_ID + driverId, new OnRequestResultForCommon() {
            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
                ContractBean contractBean = new Gson().fromJson(json,ContractBean.class);
                startTime = contractBean.getData().getStartTime();
                endTime = contractBean.getData().getEndTime();
                holder.tv_qianyue.setText(timeStampToDate(startTime));
                holder.tv_time.setText(timeStampToDate(endTime));
            }

            @Override
            public void onFail() {
                super.onFail();
            }

            @Override
            public void netUnlink() {
                super.netUnlink();
            }
        });

        holder.tv_name.setText(data.get(position).getCertificateDriverBo().getName());
        holder.tv_car_num.setText(data.get(position).getCarBo().getCertificateDrivingBo().getPlateNo()+"");

//        holder.tv_qianyue.setText(fomatDate(startTime));
//        holder.tv_time.setText(fomatDate(endTime));
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(mItemLongClickListener != null){
                    mItemLongClickListener.onItemLongClick(position);
                }
                return false;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mItemClickListener != null){
                    mItemClickListener.onItemClick(position);
                }
            }
        });
    }

    private String timeStampToDate(long tsp, String... format) {
        SimpleDateFormat sdf;
        if (format.length < 1) {
            sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        } else {
            sdf = new SimpleDateFormat(format[0], Locale.getDefault());
        }
        return sdf.format(tsp);
    }



    @Override
    public int getItemCount() {
        return  data != null && data.size() > 0 ? data.size() : 0;

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_car_num;
        TextView tv_tongguo;
        TextView tv_time;
        TextView tv_qianyue;

        private View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_car_num = itemView.findViewById(R.id.tv_car_num);
            tv_tongguo = itemView.findViewById(R.id.tv_tongguo);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_qianyue = itemView.findViewById(R.id.tv_qianyue);

        }
    }

    public void setData(List<DriverListBean.DataBean> data) {
        this.data = data;

        notifyDataSetChanged();
    }
}
