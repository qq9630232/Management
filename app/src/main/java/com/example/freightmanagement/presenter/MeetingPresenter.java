package com.example.freightmanagement.presenter;

import com.example.freightmanagement.Base.BasePresenter;
import com.example.freightmanagement.Utils.Network.OnRequestResultForCommon;
import com.example.freightmanagement.Utils.Network.RestApi;
import com.example.freightmanagement.presenter.constract.MeetingConstact;
import static com.example.freightmanagement.Base.BaseApiConstants.API_GET_MEETING;
import static com.example.freightmanagement.Base.BaseApiConstants.API_PLAY_URL;

public class MeetingPresenter extends BasePresenter<MeetingConstact.View> implements MeetingConstact {


    @Override
    public void getAllMeetings() {
        RestApi.getInstance().get(API_GET_MEETING, new OnRequestResultForCommon() {
            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
              mView.result(json);
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
    public void getPullUrl(String key) {
        RestApi.getInstance().post(API_PLAY_URL.concat("/").concat(key),"", new OnRequestResultForCommon() {
            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
                mView.pullUrl(json);
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
