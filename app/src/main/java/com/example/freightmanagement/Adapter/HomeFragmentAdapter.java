package com.example.freightmanagement.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.freightmanagement.R;
import com.example.freightmanagement.Utils.OnItemClickListener;
import com.example.freightmanagement.Utils.PrefUtilsData;

import java.util.List;

/**
 * 所有分类   Adapter
 */
public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.HomeViewHolder> {


    private Context context;
    private final LayoutInflater layoutInflater;
    private List<String> dataList;
    private List<Integer> iconList;
    private int[] icons = new int[]{R.mipmap.img_jiashiyuan, R.mipmap.img_clxx, R.mipmap.img_peixun, R.mipmap.img_hetong, R.mipmap.img_chengnuoshu, R.mipmap.img_zerenshu, R.mipmap.img_lihui, R.mipmap.img_mima};
    private OnItemClickListener listener;
    private int selectorPosition;
    private int playcount;

    public void setData(List<String> commendarray,int[] icons) {
        this.dataList = commendarray;
        this.icons = icons;
        notifyDataSetChanged();
    }

    public HomeFragmentAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeFragmentAdapter.HomeViewHolder(layoutInflater.inflate(R.layout.home_item_layout, parent, false));

    }

    @Override
    public void onBindViewHolder(final HomeViewHolder holder, final int position) {
        if (dataList != null && dataList.size() > 0) {
            if (position == 0) {
                if (PrefUtilsData.getType().equals("1")) {
                    holder.tv_name.setText("驾驶员信息");
                } else if (PrefUtilsData.getType().equals("2")) {
                    holder.tv_name.setText("车主信息");
                } else {
                    holder.tv_name.setText("企业信息");
                }
            } else {

                holder.tv_name.setText(dataList.get(position));
            }
//            holder.img.setImageResource(icons[position]);
            Glide.with(context).load(icons[position]).into(holder.img);
        } else {
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return null == dataList || dataList.size() == 0 ? 0 : dataList.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tv_name;
        private View itemView;

        public HomeViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tv_name = itemView.findViewById(R.id.tv_name);
            img = itemView.findViewById(R.id.home_jp_img);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
