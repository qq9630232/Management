package com.example.freightmanagement.Activity;

import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freightmanagement.Adapter.ImageListAdapter;
import com.example.freightmanagement.Base.BaseActivity;
import com.example.freightmanagement.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.freightmanagement.Base.BaseApiConstants.IMAGE_BASE_URL;

public class CarWeiXiuInfoActivity extends BaseActivity {
    private RecyclerView mRvImg;
    private List<String> imglist = new ArrayList<>();
    private ImageListAdapter imageListAdapter;
    private TextView et_xing_shi_li_cheng;
    private TextView et_wei_xiu_nei_rong;
    private TextView tv_wei_xiu_shi_jian;
    private TextView et_wei_xiu_dan_wei;
    private TextView et_bu_jian_chang_jia;
    private TextView et_driver_name;
    private TextView et_che_pai_hao;

    @Override
    public int setLayoutResource() {
        return R.layout.activity_car_weixiu_info;
    }

    @Override
    protected void onInitView() {
        setDefaultTitle("维修详情");
        et_xing_shi_li_cheng = findViewById(R.id.et_xing_shi_li_cheng);
        et_wei_xiu_dan_wei = findViewById(R.id.et_wei_xiu_dan_wei);
        et_wei_xiu_nei_rong = findViewById(R.id.et_wei_xiu_nei_rong);
        et_bu_jian_chang_jia = findViewById(R.id.et_bu_jian_chang_jia);
        tv_wei_xiu_shi_jian = findViewById(R.id.tv_wei_xiu_shi_jian);
        mRvImg = findViewById(R.id.rv_img);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRvImg.setLayoutManager(linearLayoutManager);
        imageListAdapter = new ImageListAdapter(this);
        mRvImg.setAdapter(imageListAdapter);
        et_driver_name = findViewById(R.id.et_driver_name);
        et_che_pai_hao = findViewById(R.id.et_che_pai_hao);
    }

    @Override
    protected void onLoadData2Remote() {
        et_driver_name.setText(getIntent().getSerializableExtra("driverName").toString());
        et_che_pai_hao.setText(getIntent().getSerializableExtra("plateNo").toString());
        et_wei_xiu_dan_wei.setText(getIntent().getSerializableExtra("servicer").toString());
        et_bu_jian_chang_jia.setText(getIntent().getSerializableExtra("partMaker").toString());
        et_xing_shi_li_cheng.setText(getIntent().getSerializableExtra("mileage").toString());
        et_wei_xiu_nei_rong.setText(getIntent().getSerializableExtra("neirong").toString());
        tv_wei_xiu_shi_jian.setText(getIntent().getSerializableExtra("time").toString());
        imglist = Arrays.asList(getIntent().getSerializableExtra("imgList").toString().split(","));
//        String strImg = getIntent().getSerializableExtra("imgList").toString();
//        imglist = strImg.split(",");
        for(int i = 0; i<imglist.size(); i++){
            String imgUrl = IMAGE_BASE_URL + imglist.get(i);
            imglist.set(i,imgUrl);
        }
        imageListAdapter.setData(imglist);
    }
}
