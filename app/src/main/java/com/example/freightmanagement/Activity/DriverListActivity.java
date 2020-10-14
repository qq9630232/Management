package com.example.freightmanagement.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freightmanagement.Adapter.DriverListAdapter;
import com.example.freightmanagement.Base.BaseActivity;
import com.example.freightmanagement.Base.BaseApiConstants;
import com.example.freightmanagement.Bean.ContractBean;
import com.example.freightmanagement.Bean.DriverListBean;
import com.example.freightmanagement.R;
import com.example.freightmanagement.Utils.Network.OnRequestResultForCommon;
import com.example.freightmanagement.Utils.Network.RestApi;
import com.example.freightmanagement.Utils.ToastUtils;
import com.example.freightmanagement.presenter.DriverListPresenter;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public  class DriverListActivity extends BaseActivity<DriverListPresenter> implements DriverListPresenter.View, DriverListAdapter.ItemClickListener, DriverListAdapter.ItemLongClickListener {
    private RecyclerView mRvDriver;
    private DriverListAdapter driverListAdapter;
    private AlertDialog.Builder builder;
    private List<DriverListBean.DataBean> data;
    private int contractId;
    private TextView et_search;
    private ImageButton ib_search;

    @Override
    public int setLayoutResource() {
        return R.layout.activity_driver_list;
    }

    @Override
    protected void onInitView() {
        EventBus.getDefault().register(this);
        setDefaultTitle("驾驶员列表");
        et_search = findViewById(R.id.et_search);
        ib_search = findViewById(R.id.ib_search);
        ib_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchDriver();
                et_search.clearFocus();

            }
        });
        mRvDriver = findViewById(R.id.rv_driver);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRvDriver.setLayoutManager(linearLayoutManager);
        driverListAdapter = new DriverListAdapter(this);
        mRvDriver.setAdapter(driverListAdapter);
        driverListAdapter.setOnItemClickListener(this);
        driverListAdapter.setOnItemLongClickListener(this);
    }

    private void searchDriver() {
        String searchKey = et_search.getText().toString();
        if(searchKey.isEmpty()){
            mPresenter.getList();
        }else{
            mPresenter.searchDriver(searchKey);
        }
    }

    @Override
    protected void onLoadData2Remote() {
        mPresenter.getList();
    }

    @Override
    public void success(String msg) {
        DriverListBean driverListBean = new Gson().fromJson(msg, DriverListBean.class);
        data = driverListBean.getData();
        driverListAdapter.setData(data);
    }

    @Override
    public void getContractResult(String json) {
        ContractBean contractBean = new Gson().fromJson(json, ContractBean.class);
        contractId = contractBean.getData().getId();
    }

    @Override
    protected DriverListPresenter onInitLogicImpl() {
        return new DriverListPresenter();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, DrivierxiangqingActivity.class);
        intent.putExtra("Id", data.get(position).getId());
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position) {
        showTwo(position);
    }



    /**
     * 两个按钮的 dialog
     *
     * @param
     */
    private void showTwo(final int position) {
        builder = new AlertDialog.Builder(this).setIcon(R.mipmap.icon_oreder_remark_delete).setTitle("解除合同")
                .setMessage("是否解除此合同？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //ToDo: 你想做的事情
                        int driverId = data.get(position).getId();
                        RestApi.getInstance().get(BaseApiConstants.API_CONTRACT_GET_BY_DRIVER_ID + driverId, new OnRequestResultForCommon() {
                            @Override
                            public void onSuccess(String json) {
                                //ToastUtils.popUpToast("解约成功");
                                ContractBean contractBean = new Gson().fromJson(json,ContractBean.class);
                                contractId =  contractBean.getData().getId();
                                RestApi.getInstance().get(BaseApiConstants.API_RESLOVE_CONTRACT + contractId, new OnRequestResultForCommon() {
                                    @Override
                                    public void onSuccess(String json) {
                                        //ToastUtils.popUpToast("解约成功");
                                        super.onSuccess(json);

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

                                editConstract(0);
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

                        // editConstract(contractId);
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
    @Override
    public void resloveSuccess(String json){
        ToastUtils.popUpToast("提交成功");

        EventBus.getDefault().post(json);
        finish();

    }

    public void editConstract(int contractId) {

        ToastUtils.popUpToast("提交成功");

        EventBus.getDefault().post("resloveConstractOk");
        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public  void delResult(String res){}

    @Subscribe
    public void onEventMainThread(String msg) {
        if(msg.equals("resloveConstractOk")){
            mPresenter.getList();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}


