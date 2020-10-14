package com.example.freightmanagement.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.freightmanagement.Bean.MeetingListBean;
import com.example.freightmanagement.R;
import com.example.freightmanagement.Utils.StringUtil;

import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.ViewHolder> {
    private final LayoutInflater layoutInflater;
    private Context context;
    private List<MeetingListBean.DataBean>  pages;
    public MeetingAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }
    // 利用接口 -> 给RecyclerView设置点击事件
    private ItemClickListener mItemClickListener;

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;

    }
    @Override
    public MeetingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MeetingAdapter.ViewHolder(layoutInflater.inflate(R.layout.item_meeting, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingAdapter.ViewHolder holder, int position) {
        holder.mTvLihui.setText(StringUtil.isNotEmpty(pages.get(position).getName())?pages.get(position).getName():"");
        holder.mtvTime.setText(StringUtil.isNotEmpty(pages.get(position).getStartTimeStr())?pages.get(position).getStartTimeStr():"");
        Integer state = pages.get(position).getState();
        if(state == null) {
            state = 0;
        }
            holder.mTvStatus.setText(state == 1 ? "进行中" : "已结束");
        if(state == 1){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mItemClickListener != null){
                        mItemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return  pages != null && pages.size() > 0 ? pages.size() : 0;

    }

    public void setPages(List<MeetingListBean.DataBean> list) {
        this.pages = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View itemView;
        TextView mtvTime;
        TextView mTvLihui;
        TextView mVideoUrl;
        TextView mTvStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            mtvTime = itemView.findViewById(R.id.tv_time);
            mTvLihui = itemView.findViewById(R.id.tv_lihui);
            mVideoUrl = itemView.findViewById(R.id.tv_video_url);
            mTvStatus = itemView.findViewById(R.id.tv_status);
        }
    }

}
