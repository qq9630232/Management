package com.example.freightmanagement.presenter;

import com.example.freightmanagement.Base.BaseApiConstants;
import com.example.freightmanagement.Base.BasePresenter;
import com.example.freightmanagement.Bean.TokenBean;
import com.example.freightmanagement.Utils.Network.OnRequestResultForCommon;
import com.example.freightmanagement.Utils.Network.RestApi;
import com.example.freightmanagement.Utils.PrefUtilsData;
import com.example.freightmanagement.enums.ResponseCodeEnum;
import com.example.freightmanagement.model.AccountParam;
import com.example.freightmanagement.presenter.constract.LoginConstact;
import com.google.gson.Gson;

public class LoginPresenter extends BasePresenter<LoginConstact.View> implements LoginConstact {


    @Override
    public void login(AccountParam accountParam) {
        String json = new Gson().toJson(accountParam);
        RestApi.getInstance().post(BaseApiConstants.API_LOGIN, json, new OnRequestResultForCommon() {
            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
                TokenBean loginBean = new Gson().fromJson(json, TokenBean.class);
                if (loginBean.getCode()==ResponseCodeEnum.SUCCESS.getCode()) {
                    PrefUtilsData.setIsLogin(true);
                    PrefUtilsData.setToken(loginBean.getData().getToken());
//                    PrefUtilsData.setType(loginBean.getData().getType()+"");
                    if(loginBean.getData().getUser() != null){
                        PrefUtilsData.setAdminId(String.valueOf(loginBean.getData().getUser().getAdminId()));
                        PrefUtilsData.setUserId(loginBean.getData().getUser().getId()+"");
                        PrefUtilsData.setDriverId(loginBean.getData().getUser().getDriverCertificateId()+"");
                        PrefUtilsData.setWorkId(loginBean.getData().getUser().getWorkCertificateId()+"");
                        PrefUtilsData.setIdCordId(loginBean.getData().getUser().getIdcertificateId()+"");
                        PrefUtilsData.setLoginName(loginBean.getData().getLoginName());
                    }

                    mView.getDataSuc(loginBean);
                }else {
                    mView.onFailed(json);
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
}
