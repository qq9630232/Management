package com.example.freightmanagement.presenter;

import com.example.freightmanagement.Base.BaseApiConstants;
import com.example.freightmanagement.Base.BasePresenter;
import com.example.freightmanagement.Utils.Network.OnRequestResultForCommon;
import com.example.freightmanagement.Utils.Network.RestApi;
import com.example.freightmanagement.model.DriverInfoSubmitParam;
import com.example.freightmanagement.presenter.constract.SelectCarConstact;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import static com.example.freightmanagement.Base.BaseApiConstants.API_DRIVER_REGISTER;

public class SelectCarPresenter extends BasePresenter<SelectCarConstact.View> implements SelectCarConstact {
    @Override
    public void submit(DriverInfoSubmitParam submitParam) {
        String json = new Gson().toJson(submitParam);
        RestApi.getInstance().post(API_DRIVER_REGISTER, json, new OnRequestResultForCommon() {
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
    public void getCar(int pageNum,int pageSize) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("pageNum", pageNum);
        jsonObject.addProperty("pageSize", pageSize);
        RestApi.getInstance().post(BaseApiConstants.API_XUANZECHELIANG, jsonObject.toString(),new OnRequestResultForCommon() {
            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
                mView.carList(json);
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
    public void searchCar(String key) {
        RestApi.getInstance().get(BaseApiConstants.API_SEARCH_CAR + key, new OnRequestResultForCommon() {
            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
                mView.carList(json);
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
