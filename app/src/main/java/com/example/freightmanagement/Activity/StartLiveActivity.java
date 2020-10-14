package com.example.freightmanagement.Activity;

import android.content.Intent;
import android.hardware.Camera;
import android.view.View;
import android.widget.Button;

import com.example.freightmanagement.Base.BaseActivity;
import com.example.freightmanagement.Base.BaseApiConstants;
import com.example.freightmanagement.Base.BaseResponse;
import com.example.freightmanagement.Bean.TuiliuKeyBean;
import com.example.freightmanagement.R;
import com.example.freightmanagement.Utils.CameraConfig;
import com.example.freightmanagement.Utils.DateUtil;
import com.example.freightmanagement.Utils.Network.OnRequestResultForCommon;
import com.example.freightmanagement.Utils.Network.RestApi;
import com.example.freightmanagement.Utils.PrefUtilsData;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qiniu.pili.droid.streaming.CameraStreamingSetting;

public class StartLiveActivity extends BaseActivity {
    private Button mBtnLiv;

    @Override
    public int setLayoutResource() {
        return R.layout.activity_start_live;
    }
    private CameraConfig buildCameraConfig() {
        CameraConfig cameraConfig = new CameraConfig();
        cameraConfig.mFrontFacing = true;
        cameraConfig.mSizeLevel = CameraStreamingSetting.PREVIEW_SIZE_LEVEL.MEDIUM;
        cameraConfig.mSizeRatio = CameraStreamingSetting.PREVIEW_SIZE_RATIO.RATIO_16_9;
        cameraConfig.mFocusMode = Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO;
        cameraConfig.mIsFaceBeautyEnabled = true;
        cameraConfig.mIsCustomFaceBeauty = false;
        cameraConfig.mContinuousAutoFocus = true;
        cameraConfig.mPreviewMirror = false;
        cameraConfig.mEncodingMirror = false;

        return cameraConfig;
    }
//    private String chatroomId = "127395385376769";
    @Override
    protected void onInitView() {

        mBtnLiv = findViewById(R.id.btn_liv);
        mBtnLiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getKey("安全例会", DateUtil.getCishi(), PrefUtilsData.getUserId(), DateUtil.getCishi());
            }
        });
    }

    @Override
    protected void onLoadData2Remote() {

    }

    private void getKey(String mName, String startTime, String sponsor, String endTime) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", mName);
        jsonObject.addProperty("startTime", startTime);
        jsonObject.addProperty("sponsor", sponsor);
        jsonObject.addProperty("endTime", endTime);
        RestApi.getInstance().post(BaseApiConstants.API_PLAY_KEY, jsonObject.toString(), new OnRequestResultForCommon() {
            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
                TuiliuKeyBean tuiliuKeyBean = new Gson().fromJson(json, TuiliuKeyBean.class);
                TuiliuKeyBean.DataBean data = tuiliuKeyBean.getData();
                getPushUrl(data.getStreamKey());
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

    private void getPushUrl(String key){
        RestApi.getInstance().post(BaseApiConstants.API_PUSH_URL+key,"",new OnRequestResultForCommon() {
            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
                BaseResponse response = new Gson().fromJson(json, BaseResponse.class);
                String url = (String) response.getData();
                Intent intent = new Intent(StartLiveActivity.this, SWCameraStreamingActivity.class);
                intent.putExtra("push_url",url);
                intent.putExtra("INPUT_TEXT", "");
                intent.putExtra("TRANSFER_MODE_QUIC", false);
                intent.putExtra("url", url);
                intent.putExtra("CameraConfig", buildCameraConfig());
                startActivity(intent);

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
