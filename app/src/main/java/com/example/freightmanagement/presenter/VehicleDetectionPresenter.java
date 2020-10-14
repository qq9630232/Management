package com.example.freightmanagement.presenter;

import com.example.freightmanagement.Base.BaseApiConstants;
import com.example.freightmanagement.Base.BasePresenter;
import com.example.freightmanagement.Bean.ImageUploadBean;
import com.example.freightmanagement.Bean.VehicleDetectionBean;
import com.example.freightmanagement.Utils.Network.OnRequestResultForCommon;
import com.example.freightmanagement.Utils.Network.RestApi;
import com.example.freightmanagement.presenter.constract.VehicleDetectionConstact;
import com.google.gson.Gson;

import java.util.List;

import static com.example.freightmanagement.Base.BaseApiConstants.API_DUOIMAGE_UPLOAD;

public class VehicleDetectionPresenter extends BasePresenter<VehicleDetectionConstact.View> implements VehicleDetectionConstact {

    @Override
    public void VehicleInformationData() {
        RestApi.getInstance().get(BaseApiConstants.API_JIANCEXIANG, new OnRequestResultForCommon() {
            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
                VehicleDetectionBean vehicleDetectionBean = new Gson().fromJson(json, VehicleDetectionBean.class);
                mView.trainingList(vehicleDetectionBean);
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
    public void addVehicleData(String json) {
        RestApi.getInstance().post(BaseApiConstants.API_ADDJIANCEXIANG, json, new OnRequestResultForCommon() {
            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
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
                mView.imageUrl(imageUploadBean,type);
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
