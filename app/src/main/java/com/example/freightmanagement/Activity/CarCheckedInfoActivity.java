package com.example.freightmanagement.Activity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freightmanagement.Adapter.CarTypeAdapter;
import com.example.freightmanagement.Adapter.ImageListAdapter;
import com.example.freightmanagement.Base.BaseActivity;
import com.example.freightmanagement.Bean.CheliangJcBean;
import com.example.freightmanagement.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.freightmanagement.Base.BaseApiConstants.IMAGE_BASE_URL;

public class CarCheckedInfoActivity extends BaseActivity {
    private RecyclerView mRv;
    private CarTypeAdapter carTypeAdapter;
    private RecyclerView mRvImg;
    private List<String> imglist = new ArrayList<>();
    private ImageListAdapter imageListAdapter;

    @Override
    public int setLayoutResource() {
        return R.layout.activity_car_check_info;
    }

    @Override
    protected void onInitView() {
        setDefaultTitle("检查情况");
        mRv = findViewById(R.id.rv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(gridLayoutManager);
        carTypeAdapter = new CarTypeAdapter(this);
        mRv.setAdapter(carTypeAdapter);
        mRvImg = findViewById(R.id.rv_img);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRvImg.setLayoutManager(linearLayoutManager);
        imageListAdapter = new ImageListAdapter(this);
        mRvImg.setAdapter(imageListAdapter);
    }

    @Override
    protected void onLoadData2Remote() {
        List<CheliangJcBean.DataBean.CompleteBosBean> list = (List<CheliangJcBean.DataBean.CompleteBosBean>) getIntent().getSerializableExtra("list");
        carTypeAdapter.setPages(list);
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
