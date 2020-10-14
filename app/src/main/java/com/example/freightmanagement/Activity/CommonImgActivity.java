package com.example.freightmanagement.Activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freightmanagement.Adapter.ImageListAdapter;
import com.example.freightmanagement.Base.BaseActivity;
import com.example.freightmanagement.Base.BaseApiConstants;
import com.example.freightmanagement.R;
import com.example.freightmanagement.Utils.Network.OnRequestResultForCommon;
import com.example.freightmanagement.Utils.Network.RestApi;
import com.example.freightmanagement.Utils.StringUtil;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class CommonImgActivity extends BaseActivity  implements  View.OnClickListener {

    private RecyclerView mRv;
    private List<String> list = new ArrayList<>();
    private ImageListAdapter imageListAdapter;
    private AlertDialog.Builder builder;
    private TextView mTvSrue;

    private Integer contractId;

    private Integer driverId;
    private Integer carId;

    @Override
    public int setLayoutResource() {
        return R.layout.activity_common_img;
    }

    @Override
    protected void onInitView() {
        setDefaultTitle("合同");
        mRv = findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRv.setLayoutManager(linearLayoutManager);
        imageListAdapter = new ImageListAdapter(this);
        mRv.setAdapter(imageListAdapter);
        mTvSrue = findViewById(R.id.tv_srue);
        mTvSrue.setOnClickListener(this);
    }

    @Override
    protected void onLoadData2Remote() {
        String htUrl = getIntent().getStringExtra("htUrl");
        String zrUrl = getIntent().getStringExtra("zrUrl");
        String cnUrl = getIntent().getStringExtra("cnUrl");
        contractId = getIntent().getIntExtra("contractId",0);

        if(StringUtil.isNotEmpty(htUrl)){
            list.add(htUrl);
        }
        if(StringUtil.isNotEmpty(zrUrl)){
            list.add(zrUrl);
        }
        if(StringUtil.isNotEmpty(cnUrl)){
            list.add(cnUrl);
        }
        imageListAdapter.setData(list);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_srue:
                showTwo();
                //finish();
                break;
        }
    }

    /**
     * 两个按钮的 dialog
     *
     * @param
     */
    private void showTwo() {
        builder = new AlertDialog.Builder(this).setIcon(R.mipmap.icon_oreder_remark_delete).setTitle("解除合同")
                .setMessage("是否解除此合同？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //ToDo: 你想做的事情
//                        Toast.makeText(CarListManageActivity.this, "确定按钮", Toast.LENGTH_LONG).show();
//                        CarExecuteParam carExecuteParam = new CarExecuteParam();
//                        carExecuteParam.setCarId();


                        editConstract(contractId);//如何联动修改
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //ToDo: 你想做的事情
//                        Toast.makeText(CarListManageActivity.this, "关闭按钮", Toast.LENGTH_LONG).show();
                        dialogInterface.dismiss();
                    }
                });
        builder.create().show();
    }



    public void editConstract(int contractId) {

        int id = contractId;
        String json = new Gson().toJson(id);
        RestApi.getInstance().get(BaseApiConstants.API_RESLOVE_CONTRACT + contractId, new OnRequestResultForCommon() {
            @Override
            public void onSuccess(String json) {
                //ToastUtils.popUpToast("解约成功");
                super.onSuccess(json);
                EventBus.getDefault().post("contract isActive 2");
                finish();
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
    }
}
