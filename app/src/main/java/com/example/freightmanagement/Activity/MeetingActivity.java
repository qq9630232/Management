package com.example.freightmanagement.Activity;


import android.content.Intent;
import android.hardware.Camera;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freightmanagement.Adapter.MeetingAdapter;
import com.example.freightmanagement.Base.BaseActivity;
import com.example.freightmanagement.Base.BaseApiConstants;
import com.example.freightmanagement.Base.BaseResponse;
import com.example.freightmanagement.Bean.MeetingListBean;
import com.example.freightmanagement.Bean.TuiliuKeyBean;
import com.example.freightmanagement.R;
import com.example.freightmanagement.Utils.CameraConfig;
import com.example.freightmanagement.Utils.DateUtil;
import com.example.freightmanagement.Utils.Network.OnRequestResultForCommon;
import com.example.freightmanagement.Utils.Network.RestApi;
import com.example.freightmanagement.Utils.PrefUtilsData;
import com.example.freightmanagement.enums.AdminTypeEnum;
import com.example.freightmanagement.presenter.MeetingPresenter;
import com.example.freightmanagement.presenter.constract.MeetingConstact;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMChatRoom;
import com.hyphenate.chat.EMClient;
import com.qiniu.pili.droid.streaming.CameraStreamingSetting;

import java.util.List;

public class MeetingActivity extends BaseActivity<MeetingPresenter> implements MeetingConstact.View, MeetingAdapter.ItemClickListener, View.OnClickListener {
    private static final String TAG = "MeetingActivity";
    private RecyclerView mRvMeeting;
    private MeetingAdapter meetingAdapter;
    private List<MeetingListBean.DataBean> data;
    private TextView mTvLive;

    @Override
    public int setLayoutResource() {
        return R.layout.activity_meeting;
    }

    @Override
    protected void onInitView() {
        setDefaultTitle("公司会议");
        String type = PrefUtilsData.getType();
        mTvLive = findViewById(R.id.tv_live);
        mTvLive.setOnClickListener(this);
        if (type.equals(AdminTypeEnum.COMPANY.getValue())) {
            mTvLive.setVisibility(View.VISIBLE);
        }else {
            mTvLive.setVisibility(View.GONE);
        }
        mRvMeeting = findViewById(R.id.rv_meeting);
        meetingAdapter = new MeetingAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRvMeeting.setLayoutManager(linearLayoutManager);
        mRvMeeting.setAdapter(meetingAdapter);
        meetingAdapter.setOnItemClickListener(this);

    }

    @Override
    protected void onLoadData2Remote() {
        mPresenter.getAllMeetings();
    }

    @Override
    public void result(String json) {
        MeetingListBean meetingListBean = new Gson().fromJson(json, MeetingListBean.class);
        data = meetingListBean.getData();
        meetingAdapter.setPages(data);
    }

    @Override
    public void pullUrl(String json) {
        BaseResponse response = new Gson().fromJson(json, BaseResponse.class);
        String url = (String) response.getData();
        Intent intent = new Intent(this, PlayerActivity.class);
        intent.putExtra("live_url", url);
        startActivity(intent);
    }

    @Override
    protected MeetingPresenter onInitLogicImpl() {
        return new MeetingPresenter();
    }

    @Override
    public void onItemClick(int position) {
        String streamKey = data.get(position).getStreamKey();
        String roomId = data.get(position).getRoomId();
        mPresenter.getPullUrl(streamKey);
        EMClient.getInstance().chatroomManager().joinChatRoom(roomId, new EMValueCallBack<EMChatRoom>() {

            @Override
            public void onSuccess(EMChatRoom value) {
                //加入聊天室成功
                Log.e(TAG, "onLoadData2Remote: ");
            }

            @Override
            public void onError(final int error, String errorMsg) {
                //加入聊天室失败
                Log.e(TAG, "onLoadData2Remote: ");
            }
        });
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
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_live:
                getKey("安全例会", DateUtil.getCishi(), PrefUtilsData.getUserId(), DateUtil.getCishi());
                break;
        }
    }

    /**
     * 获取直播的key
     * @param mName
     * @param startTime
     * @param sponsor
     * @param endTime
     */
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
                getPushUrl(data.getStreamKey(),data.getChatRoomId());
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
     * 获取推流地址
     * @param streamKey
     * @param roomId
     */
    private void getPushUrl(String streamKey, String roomId){
        RestApi.getInstance().post(BaseApiConstants.API_PUSH_URL+streamKey,"",new OnRequestResultForCommon() {
            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
                BaseResponse response = new Gson().fromJson(json, BaseResponse.class);
                String url = (String) response.getData();
                Intent intent = new Intent(MeetingActivity.this, SWCameraStreamingActivity.class);
                intent.putExtra("push_url",url);
                intent.putExtra("INPUT_TEXT", "");
                intent.putExtra("TRANSFER_MODE_QUIC", false);
                intent.putExtra("url", url);
                intent.putExtra("roomId",roomId);
                Log.e("zxz",url);

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
