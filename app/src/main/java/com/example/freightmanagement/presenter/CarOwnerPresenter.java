package com.example.freightmanagement.presenter;

import com.example.freightmanagement.Base.BasePresenter;
import com.example.freightmanagement.Base.BaseResponse;
import com.example.freightmanagement.Bean.ImageUploadBean;
import com.example.freightmanagement.Utils.Network.OnRequestResultForCommon;
import com.example.freightmanagement.Utils.Network.RestApi;
import com.example.freightmanagement.model.CarOwnerSubmitParam;
import com.example.freightmanagement.presenter.constract.CarOwnerConstact;
import com.google.gson.Gson;

import java.io.File;

import static com.example.freightmanagement.Base.BaseApiConstants.API_CAR_OWNER_REGISTER;
import static com.example.freightmanagement.Base.BaseApiConstants.API_IMAGE_UPLOAD;

/**
 * Created by songdechuan on 2020/8/18.
 */

public class CarOwnerPresenter extends BasePresenter<CarOwnerConstact.View> implements CarOwnerConstact {
    @Override
    public void submit(CarOwnerSubmitParam submitParam) {
        String json = new Gson().toJson(submitParam);
        RestApi.getInstance().post(API_CAR_OWNER_REGISTER, json, new OnRequestResultForCommon() {
            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
                BaseResponse response = new BaseResponse();
                if(response.getCode()==0){
                    mView.success();
                }
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
    public void upload(File file, final int type) {
        RestApi.getInstance().postImage(API_IMAGE_UPLOAD, file, new OnRequestResultForCommon() {
            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
                ImageUploadBean imageUploadBean = new Gson().fromJson(json, ImageUploadBean.class);
                mView.imageUrl(imageUploadBean.getData(),type);
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
