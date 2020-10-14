package com.example.freightmanagement.presenter;

import com.example.freightmanagement.Base.BaseApiConstants;
import com.example.freightmanagement.Base.BasePresenter;
import com.example.freightmanagement.Utils.Network.OnRequestResultForCommon;
import com.example.freightmanagement.Utils.Network.RestApi;
import com.example.freightmanagement.model.CarExecuteParam;
import com.example.freightmanagement.presenter.constract.DriverListConstact;
import com.google.gson.Gson;

import static com.example.freightmanagement.Base.BaseApiConstants.API_CONTRACT_GET_BY_DRIVER_ID;
import static com.example.freightmanagement.Base.BaseApiConstants.API_DRIVER_LIST;
import static com.example.freightmanagement.Base.BaseApiConstants.API_SEARCH_DRIVER;

public class DriverListPresenter extends BasePresenter<DriverListConstact .View> implements DriverListConstact {

    @Override
    public void getList() {
        RestApi.getInstance().post(API_DRIVER_LIST, "", new OnRequestResultForCommon() {
            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
                mView.success(json);
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

    @Override
    public void searchDriver(String searchKey) {
        RestApi.getInstance().get(API_SEARCH_DRIVER + searchKey,  new OnRequestResultForCommon() {
            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
                mView.success(json);
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

    @Override
    public void delete(CarExecuteParam param) {

    }

    public void editConstract(int contractId) {

        //String json = new Gson().toJson(contractId);
        RestApi.getInstance().get(BaseApiConstants.API_RESLOVE_CONTRACT + contractId, new OnRequestResultForCommon() {
            @Override
            public void onSuccess(String json) {
                //ToastUtils.popUpToast("解约成功");
                super.onSuccess(json);

               mView.resloveSuccess("resloveConstractOk");
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


    /**
     * by z 20200925
     * @param driverId
     */
    @Override
    public void getContractByDriverId(int driverId){

        String json = new Gson().toJson(driverId);
        RestApi.getInstance().get(API_CONTRACT_GET_BY_DRIVER_ID + driverId, new OnRequestResultForCommon() {
            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
                mView.getContractResult(json);
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
    };
}