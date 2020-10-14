package com.example.freightmanagement.presenter;

import com.example.freightmanagement.Base.BaseApiConstants;
import com.example.freightmanagement.Base.BasePresenter;
import com.example.freightmanagement.Bean.ImageUploadBean;
import com.example.freightmanagement.Utils.Network.OnRequestResultForCommon;
import com.example.freightmanagement.Utils.Network.RestApi;
import com.example.freightmanagement.presenter.constract.TrainingConstact;
import com.example.freightmanagement.presenter.constract.WeiXiuConstact;
import com.google.gson.Gson;

import java.io.File;
import java.util.List;

import static com.example.freightmanagement.Base.BaseApiConstants.API_DUOIMAGE_UPLOAD;
import static com.example.freightmanagement.Base.BaseApiConstants.API_IMAGE_UPLOAD;


/**
 * Created by songdechuan on 2020/8/6.
 */

public class WeiXiuPresenter extends BasePresenter<WeiXiuConstact.View> implements WeiXiuConstact {

    @Override
    public void addWeiXiu(String json) {
        RestApi.getInstance().post(BaseApiConstants.API_ADDWEIXIU, json, new OnRequestResultForCommon() {
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
    public void getTestList(int id) {
        RestApi.getInstance().get("/cccc/examination/getExaminationDriverDatas/"+id, new OnRequestResultForCommon() {
            @Override
            public void onSuccess(String json) {
                mView.testResult(json);
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
    }
}
