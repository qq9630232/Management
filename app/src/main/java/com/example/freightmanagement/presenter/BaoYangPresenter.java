package com.example.freightmanagement.presenter;

import com.example.freightmanagement.Base.BaseApiConstants;
import com.example.freightmanagement.Base.BasePresenter;
import com.example.freightmanagement.Bean.ImageUploadBean;
import com.example.freightmanagement.Utils.Network.OnRequestResultForCommon;
import com.example.freightmanagement.Utils.Network.RestApi;
import com.example.freightmanagement.Utils.PrefUtilsData;
import com.example.freightmanagement.presenter.constract.BaoYangConstact;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.List;

import static com.example.freightmanagement.Base.BaseApiConstants.API_DUOIMAGE_UPLOAD;


/**
 * Created by songdechuan on 2020/8/6.
 */

public class BaoYangPresenter extends BasePresenter<BaoYangConstact.View> implements BaoYangConstact {



    @Override
    public void getTrainingList(String carOwnerId,String content,double mileage,String picUrl,String time) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("carOwnerId", carOwnerId);
        jsonObject.addProperty("content", content);
        jsonObject.addProperty("driverId", PrefUtilsData.getUserId());
        jsonObject.addProperty("mileage", mileage);
        jsonObject.addProperty("picUrl", picUrl);
        jsonObject.addProperty("time", time);
        RestApi.getInstance().post(BaseApiConstants.API_ADDBAOYANG, jsonObject.toString(), new OnRequestResultForCommon() {
            @Override
            public void onSuccess(String json) {
                mView.mSuc();
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
    public void upload(List<String> file, final int type) {

        RestApi.getInstance().postImageList(API_DUOIMAGE_UPLOAD, file, new OnRequestResultForCommon() {
            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
                ImageUploadBean imageUploadBean = new Gson().fromJson(json, ImageUploadBean.class);
                mView.imageUrl(imageUploadBean);
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
    }}
