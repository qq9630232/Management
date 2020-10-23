package com.example.freightmanagement.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.Camera;
import android.media.AudioFormat;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freightmanagement.Base.BaseApiConstants;
import com.example.freightmanagement.Bean.GiftBean;
import com.example.freightmanagement.R;
import com.example.freightmanagement.Utils.CameraConfig;
import com.example.freightmanagement.Utils.DialogUtils;
import com.example.freightmanagement.Utils.Network.OnRequestResultForCommon;
import com.example.freightmanagement.Utils.Network.RestApi;
import com.example.freightmanagement.Utils.ToastUtils;
import com.example.freightmanagement.Utils.Utils;
import com.example.freightmanagement.View.CameraPreviewFrameView;
import com.example.freightmanagement.View.ChatRoomPresenter;
import com.example.freightmanagement.View.DemoConstants;
import com.example.freightmanagement.View.DemoHelper;
import com.example.freightmanagement.View.DemoMsgHelper;
import com.example.freightmanagement.View.OnCustomMsgReceiveListener;
import com.example.freightmanagement.View.OnMsgCallBack;
import com.example.freightmanagement.View.RoomMessagesView;
import com.example.freightmanagement.View.SingleBarrageView;
import com.example.freightmanagement.common.EmClientRepository;
import com.google.gson.JsonObject;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMChatRoom;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMCustomMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.qiniu.pili.droid.streaming.AVCodecType;
import com.qiniu.pili.droid.streaming.CameraStreamingSetting;
import com.qiniu.pili.droid.streaming.MediaStreamingManager;
import com.qiniu.pili.droid.streaming.MicrophoneStreamingSetting;
import com.qiniu.pili.droid.streaming.StreamingPreviewCallback;
import com.qiniu.pili.droid.streaming.StreamingProfile;
import com.qiniu.pili.droid.streaming.StreamingState;
import com.qiniu.pili.droid.streaming.SurfaceTextureCallback;
import com.qiniu.pili.droid.streaming.WatermarkSetting;

import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

public class SWCameraStreamingActivity extends StreamingBaseActivity implements StreamingPreviewCallback,
        CameraPreviewFrameView.Listener,
        SurfaceTextureCallback, View.OnClickListener, ChatRoomPresenter.OnChatRoomListener, OnCustomMsgReceiveListener {


    CameraPreviewFrameView mCameraPreviewSurfaceView;
    private MediaStreamingManager mMediaStreamingManager;
    private StreamingProfile mProfile;
    private String TAG = "StreamingByCameraActivity";
    private CameraConfig mCameraConfig;
    private CameraStreamingSetting mCameraStreamingSetting;
    private boolean mIsNeedFB;
    private boolean mIsPreviewMirror;
    private boolean mIsEncodingMirror;
    private int mCurrentCamFacingIndex;
    private TextView mCameraSwitchBtn;
    private boolean mIsPictureStreaming = false;
    private Switcher mSwitcher = new Switcher();
    private RoomMessagesView messageView;
    protected Handler handler = new Handler();
    private String chatroomId = "";
    private SingleBarrageView barrageView;
    private RecyclerView listview;
    private ListAdapter adapter;
    private EMConversation conversation;
    private String push_url;
    private ImageView mIvLeftBack;
    private TextView mTvCount;
    private TextView mTvJoin;


    @Override
    public int setLayoutResource() {
        return R.layout.activity_s_w_camera_streaming;
    }

    @Override
    protected void onInitView() {

        messageView = (RoomMessagesView) bindView(R.id.message_view);
        barrageView = (SingleBarrageView) bindView(R.id.barrageView);
        listview = (RecyclerView) findViewById(R.id.listview);
        showInputView();
        push_url = getIntent().getStringExtra("push_url");
        chatroomId = getIntent().getStringExtra("roomId");
        mIvLeftBack = findViewById(R.id.iv_left_back);
        mIvLeftBack.setOnClickListener(this);
        mTvCount = findViewById(R.id.tv_count);

        mTvJoin = findViewById(R.id.tv_join);

        DemoMsgHelper.getInstance().init(chatroomId);
        presenter = new ChatRoomPresenter(this, chatroomId);
        presenter.setOnChatRoomListener(this);
        DemoMsgHelper.getInstance().setOnCustomMsgReceiveListener(this);
    }

    private void showInputView() {

        messageView.setShowInputView(true);
        messageView.getInputView().requestFocus();
        messageView.getInputView().requestFocusFromTouch();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Utils.showKeyboard(messageView.getInputView());
            }
        }, 200);
    }

    protected ChatRoomPresenter presenter;

    @Override
    protected void onLoadData2Remote() {
        onMessageListInit();
        EmClientRepository emClientRepository = new EmClientRepository();
        emClientRepository.getMembers(chatroomId, new EMValueCallBack() {
            @Override
            public void onSuccess(Object value) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<String> list = (List<String>) value;
                        mTvCount.setText("当前人数：" + list.size());
                    }
                });


            }

            @Override
            public void onError(int error, String errorMsg) {

            }
        });

        EMClient.getInstance().chatroomManager().joinChatRoom(chatroomId, new EMValueCallBack<EMChatRoom>() {

            @Override
            public void onSuccess(EMChatRoom value) {
                //加入聊天室成功
                Log.e(TAG, "onLoadData2Remote: ");
                addChatRoomChangeListener();

            }

            @Override
            public void onError(final int error, String errorMsg) {
                //加入聊天室失败
                Log.e(TAG, "onLoadData2Remote: ");
            }
        });


    }

    protected void onMessageListInit() {
        messageView.init();
        conversation = EMClient.getInstance().chatManager().getConversation(chatroomId, EMConversation.EMConversationType.ChatRoom, true);
        adapter = new ListAdapter(getContext(), conversation);
        listview.setLayoutManager(new LinearLayoutManager(getContext()));
        listview.setAdapter(adapter);
//        getContext().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
        messageView.setMessageViewListener(new RoomMessagesView.MessageViewListener() {
            @Override
            public void onMessageSend(String content, final boolean isBarrageMsg) {
                presenter.sendTxtMsg(content, isBarrageMsg, new OnMsgCallBack() {
                    @Override
                    public void onSuccess(EMMessage message) {
                        //刷新消息列表
                        if (adapter != null) {
                            adapter.refresh();
                            listview.smoothScrollToPosition(adapter.getItemCount() - 1);
                        }
//                                if (isBarrageMsg) {
//                                    barrageView.addData(message);
//                                }
                    }
                });
            }

            @Override
            public void onItemClickListener(final EMMessage message) {
                //if(message.getFrom().equals(EMClient.getInstance().getCurrentUser())){
                //    return;
                //}
                String clickUsername = message.getFrom();
//                        showUserDetailsDialog(clickUsername);
            }

            @Override
            public void onHiderBottomBar() {
//                        comment_image.setVisibility(View.VISIBLE);
            }
        });
//            }
//        });
    }
    /**
     * add chat room change listener
     */
    protected void addChatRoomChangeListener() {
        EMClient.getInstance().chatroomManager().addChatRoomChangeListener(presenter);
    }
    private boolean isPictureStreaming() {
        if (mIsPictureStreaming) {
            Toast.makeText(SWCameraStreamingActivity.this, "is picture streaming, operation failed!", Toast.LENGTH_SHORT).show();
        }
        return mIsPictureStreaming;
    }


    private CameraStreamingSetting buildCameraStreamingSetting() {

        mCameraConfig = (CameraConfig) getIntent().getSerializableExtra("CameraConfig");

        CameraStreamingSetting cameraStreamingSetting = new CameraStreamingSetting();
        cameraStreamingSetting.setCameraId(mCameraConfig.mFrontFacing ? Camera.CameraInfo.CAMERA_FACING_FRONT : Camera.CameraInfo.CAMERA_FACING_BACK)
                .setCameraPrvSizeLevel(mCameraConfig.mSizeLevel)
                .setCameraPrvSizeRatio(mCameraConfig.mSizeRatio)
                .setFocusMode(mCameraConfig.mFocusMode)
                .setContinuousFocusModeEnabled(mCameraConfig.mContinuousAutoFocus)
                .setFrontCameraPreviewMirror(mCameraConfig.mPreviewMirror)
                .setFrontCameraMirror(mCameraConfig.mEncodingMirror).setRecordingHint(false)
                .setResetTouchFocusDelayInMs(3000)
                .setBuiltInFaceBeautyEnabled(!mCameraConfig.mIsCustomFaceBeauty)
                .setFaceBeautySetting(new CameraStreamingSetting.FaceBeautySetting(1.0f, 1.0f, 0.8f));

        if (mCameraConfig.mIsFaceBeautyEnabled) {
            cameraStreamingSetting.setVideoFilter(CameraStreamingSetting.VIDEO_FILTER_TYPE.VIDEO_FILTER_BEAUTY);
        } else {
            cameraStreamingSetting.setVideoFilter(CameraStreamingSetting.VIDEO_FILTER_TYPE.VIDEO_FILTER_NONE);
        }

        return cameraStreamingSetting;
    }

    @Override
    protected void initStreamingManager() {

        CameraPreviewFrameView cameraPreviewFrameView = (CameraPreviewFrameView) findViewById(R.id.cameraPreview_surfaceView);
        mMediaStreamingManager = new MediaStreamingManager(this, cameraPreviewFrameView, AVCodecType.SW_VIDEO_WITH_SW_AUDIO_CODEC);
        mProfile = new StreamingProfile();
//        mProfile.setPictureStreamingResourceId(R.mipmap.pause_publish);
        String url = push_url;

        try {
            mProfile.setPublishUrl(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        MicrophoneStreamingSetting microphoneStreamingSetting = null;
        mCameraStreamingSetting = buildCameraStreamingSetting();
        if (mAudioStereoEnable) {
            /**
             * Notice !!! {@link AudioFormat#CHANNEL_IN_STEREO} is NOT guaranteed to work on all devices.
             */
            microphoneStreamingSetting = new MicrophoneStreamingSetting();
//            microphoneStreamingSetting.setChannelConfig(AudioFormat.CHANNEL_IN_STEREO);
        }
        mMediaStreamingManager.prepare(mCameraStreamingSetting, microphoneStreamingSetting, buildWatermarkSetting(), mProfile);
//        mMediaStreamingManager.setAutoRefreshOverlay(true);

        if (mCameraConfig.mIsCustomFaceBeauty) {
            mMediaStreamingManager.setSurfaceTextureCallback(this);
        }
        cameraPreviewFrameView.setListener(this);
        mMediaStreamingManager.setStreamingSessionListener(this);
        mMediaStreamingManager.setStreamStatusCallback(this);
        mMediaStreamingManager.setAudioSourceCallback(this);
        mMediaStreamingManager.setStreamingStateListener(this);
        mIsEncOrientationPort = true;
        mIsNeedFB = mCameraConfig.mIsFaceBeautyEnabled;
        mIsPreviewMirror = mCameraConfig.mPreviewMirror;
        mIsEncodingMirror = mCameraConfig.mEncodingMirror;
        mCurrentCamFacingIndex = mCameraConfig.mFrontFacing ? 1 : 0;

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(mIsEncOrientationPort ? ActivityInfo.SCREEN_ORIENTATION_USER : ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        mCameraSwitchBtn = (TextView) bindView(R.id.camera_switch_btn);
        mMediaStreamingManager.setVideoFilterType(CameraStreamingSetting.VIDEO_FILTER_TYPE.VIDEO_FILTER_BEAUTY);
        mCameraSwitchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPictureStreaming()) {
                    return;
                }
                mCameraSwitchBtn.removeCallbacks(mSwitcher);
                mCameraSwitchBtn.postDelayed(mSwitcher, 100);
            }
        });
    }

    @Override
    protected boolean startStreaming() {
        return mMediaStreamingManager.startStreaming();
    }

    @Override
    protected boolean stopStreaming() {
        return mMediaStreamingManager.stopStreaming();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMediaStreamingManager.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // You must invoke pause here.
        mMediaStreamingManager.pause();
    }


    @Override
    public void onAudioSourceAvailable(ByteBuffer byteBuffer, int i, long l, boolean b) {

    }

    @Override
    public void notifyStreamStatusChanged(StreamingProfile.StreamStatus streamStatus) {
        Log.e(TAG, "StreamStatus = " + streamStatus);
    }

    @Override
    public boolean onRecordAudioFailedHandled(int i) {
        Log.i(TAG, "onRecordAudioFailedHandled");
        return false;
    }

    @Override
    public boolean onRestartStreamingHandled(int code) {
        Log.i(TAG, "onRestartStreamingHandled");
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (mMediaStreamingManager != null) {
                    mMediaStreamingManager.startStreaming();
                }
            }
        }).start();
        return false;
    }

    @Override
    public Camera.Size onPreviewSizeSelected(List<Camera.Size> list) {
        return null;
    }


    @Override
    public void onStateChanged(StreamingState streamingState, Object extra) {
        Log.e(TAG, "streamingState = " + streamingState + "extra = " + extra);
        switch (streamingState) {
            case PREPARING:
                Log.e(TAG, "PREPARING");
                break;
            case READY:
                Log.e(TAG, "READY");
                // start streaming when READY
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (mMediaStreamingManager != null) {
                            mMediaStreamingManager.startStreaming();
                            Log.e(TAG, "run: " + "推流" + mMediaStreamingManager.startStreaming());
                        }
                    }
                }).start();
                break;
            case CONNECTING:
                Log.e(TAG, "连接中");
                break;
            case STREAMING:
                Log.e(TAG, "推流中");
                // The av packet had been sent.
                break;
            case SHUTDOWN:
                Log.e(TAG, "直播中断");
                // The streaming had been finished.
                break;
            case IOERROR:
                // Network connect error.
                Log.e(TAG, "网络连接失败");
                break;
            case OPEN_CAMERA_FAIL:
                Log.e(TAG, "摄像头打开失败");
                // Failed to open camera.
                break;
            case DISCONNECTED:
                Log.e(TAG, "已经断开连接");
                // The socket is broken while streaming
                break;
            case TORCH_INFO:
                Log.e(TAG, "开启闪光灯");
                break;

        }
    }

    /**
     * Accept only 32 bit png (ARGB)
     *
     * @return
     */
    private WatermarkSetting buildWatermarkSetting() {

        WatermarkSetting watermarkSetting = new WatermarkSetting(this);
        watermarkSetting.setResourceId(R.mipmap.ic_launcher);
        watermarkSetting.setAlpha(100);
        watermarkSetting.setSize(WatermarkSetting.WATERMARK_SIZE.MEDIUM);
        watermarkSetting.setLocation(WatermarkSetting.WATERMARK_LOCATION.SOUTH_EAST);

        return watermarkSetting;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onZoomValueChanged(float factor) {
        return false;
    }

    @Override
    public boolean onPreviewFrame(byte[] bytes, int i, int i1, int i2, int i3, long l) {
        return false;
    }

    @Override
    public void onSurfaceCreated() {

    }

    @Override
    public void onSurfaceChanged(int i, int i1) {

    }

    @Override
    public void onSurfaceDestroyed() {

    }

    @Override
    public int onDrawFrame(int i, int i1, int i2, float[] floats) {
        return 0;
    }

//    @Override
//    public void onChatRoomOwnerChanged(String chatRoomId, String newOwner, String oldOwner) {
//
//    }
    private Handler mCountDownHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //时间
            if ((msg.arg1 -= 1) <= 0) {
                //重置
                mTvJoin.setText("");
                mTvJoin.setVisibility(View.GONE);
//                mTvHqyzm.setTextColor(getResources().getColor(R.color.color_FFFF5C1F));
//                mTvHqyzm.setBackground(getResources().getDrawable(R.drawable.yellow_line90));
            } else {

            }
        }
    };
//    @Override
//    public void onChatRoomMemberAdded(String participant) {
//        mTvJoin.setVisibility(View.VISIBLE);
//        mTvJoin.setText(participant+"进入了直播间");
//        Message message = mCountDownHandler.obtainMessage();
//        message.arg1 = 60;
//        mCountDownHandler.sendMessage(message);
//    }
//
//    @Override
//    public void onChatRoomMemberExited(String participant) {
//
//    }
//
//    @Override
//    public void onMessageReceived() {
//        //刷新消息列表
//        if (adapter != null) {
//            adapter.refresh();
//            listview.smoothScrollToPosition(adapter.getItemCount() - 1);
//        }
//    }
//
//    @Override
//    public void onMessageSelectLast() {
//
//    }
//
//    @Override
//    public void onMessageChanged() {
//
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left_back:
                exitLive();
                break;
        }
    }

    @Override
    public void onChatRoomOwnerChanged(String chatRoomId, String newOwner, String oldOwner) {
        ToastUtils.popUpToast("bbbbb");
    }

    @Override
    public void onChatRoomMemberAdded(String participant) {
        ToastUtils.popUpToast("aaaaa");
        mTvJoin.setVisibility(View.VISIBLE);
        mTvJoin.setText(participant+"进入了直播间");
        Message message = mCountDownHandler.obtainMessage();
        message.arg1 = 60;
        mCountDownHandler.sendMessage(message);
    }

    @Override
    public void onChatRoomMemberExited(String participant) {

    }

    @Override
    public void onMessageReceived() {
        //刷新消息列表
        if (adapter != null) {
            adapter.refresh();
            listview.smoothScrollToPosition(adapter.getItemCount() - 1);
        }
    }

    @Override
    public void onMessageSelectLast() {

    }

    @Override
    public void onMessageChanged() {

    }

    @Override
    public void onReceiveGiftMsg(EMMessage message) {

    }

    @Override
    public void onReceivePraiseMsg(EMMessage message) {

    }

    @Override
    public void onReceiveBarrageMsg(EMMessage message) {

    }

    private class Switcher implements Runnable {
        @Override
        public void run() {
            mCurrentCamFacingIndex = (mCurrentCamFacingIndex + 1) % CameraStreamingSetting.getNumberOfCameras();
            CameraStreamingSetting.CAMERA_FACING_ID facingId;
            if (mCurrentCamFacingIndex == CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_BACK.ordinal()) {
                facingId = CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_BACK;
            } else if (mCurrentCamFacingIndex == CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_FRONT.ordinal()) {
                facingId = CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_FRONT;
            } else {
                facingId = CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_3RD;
            }
            Log.i(TAG, "switchCamera:" + facingId);
            mMediaStreamingManager.switchCamera(facingId);

            mIsEncodingMirror = mCameraConfig.mEncodingMirror;
            mIsPreviewMirror = facingId == CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_FRONT ? mCameraConfig.mPreviewMirror : false;
        }
    }

    private class ListAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private final Context context;
        EMMessage[] messages;


        public ListAdapter(Context context, EMConversation conversation) {
            this.context = context;
            messages = conversation.getAllMessages().toArray(new EMMessage[0]);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_room_msgs_item, parent, false));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            final EMMessage message = messages[position];
            String from = message.getFrom();
            String nickName = DemoHelper.getNickName(from);
            boolean isSelf = EMClient.getInstance().getCurrentUser().equals(from);
            if (message.getBody() instanceof EMTextMessageBody) {
                boolean memberAdd = false;
                Map<String, Object> ext = message.ext();
                if (ext.containsKey(DemoConstants.MSG_KEY_MEMBER_ADD)) {
                    memberAdd = (boolean) ext.get(DemoConstants.MSG_KEY_MEMBER_ADD);
                }
                String content = ((EMTextMessageBody) message.getBody()).getMessage();
                if (memberAdd) {
                    showMemberAddMsg(holder.name, nickName, content);
                } else {
                    showText(holder.name, nickName, isSelf, content);
                }
            } else if (message.getBody() instanceof EMCustomMessageBody) {
                DemoMsgHelper msgHelper = DemoMsgHelper.getInstance();
                if (msgHelper.isGiftMsg(message)) {
                    showGiftMessage(holder.name, nickName, isSelf, message);
                } else if (msgHelper.isPraiseMsg(message)) {
                    showPraiseMessage(holder.name, nickName, isSelf, message);
                } else if (msgHelper.isBarrageMsg(message)) {
                    showBarrageMessage(holder.name, nickName, isSelf, message);
                }
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                  String M= message;
                }
            });
        }

        private void showMemberAddMsg(TextView name, String nickName, String content) {
            StringBuilder builder = new StringBuilder();
            builder.append(nickName).append(" ").append(context.getString(R.string.em_live_msg_member_add));
            SpannableString span = new SpannableString(builder.toString());
            span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.white)), 0, nickName.length() + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.gray_background)),
                    nickName.length() + 1, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            name.setText(span);
        }

        private void showText(TextView name, String nickName, boolean isSelf, String content) {
            StringBuilder builder = new StringBuilder();
            builder.append(nickName).append(":").append(content);
            SpannableString span = new SpannableString(builder.toString());
            span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.white)), 0, nickName.length() + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new ForegroundColorSpan(isSelf ? ContextCompat.getColor(getContext(), R.color.white) : Color.parseColor("#FFC700")),
                    nickName.length() + 1, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            name.setText(span);
        }

        private void showGiftMessage(TextView name, String nickName, boolean isSelf, EMMessage message) {
            GiftBean bean = DemoHelper.getGiftById(DemoMsgHelper.getInstance().getMsgGiftId(message));
            int num = DemoMsgHelper.getInstance().getMsgGiftNum(message);
            String giftName = "礼物";
            if (bean != null) {
                giftName = bean.getName();
            }
            String content = context.getString(R.string.em_live_msg_gift, nickName, giftName, num);
            SpannableString span = new SpannableString(content);
            span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.white)), 0, nickName.length() + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.gray_background)),
                    nickName.length() + 1, nickName.length() + 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new ForegroundColorSpan(Color.parseColor("#ff68ff95")),
                    nickName.length() + 4, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            name.setText(span);
        }

        private void showPraiseMessage(TextView name, String nickName, boolean isSelf, EMMessage message) {
            String content = context.getString(R.string.em_live_msg_like, nickName, DemoMsgHelper.getInstance().getMsgPraiseNum(message));
            SpannableString span = new SpannableString(content);
            span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.white)), 0, nickName.length() + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.gray_background)),
                    nickName.length() + 1, nickName.length() + 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new ForegroundColorSpan(Color.parseColor("#ff68ff95")),
                    nickName.length() + 3, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            name.setText(span);
        }

        private void showBarrageMessage(TextView name, String nickName, boolean isSelf, EMMessage message) {
            showText(name, nickName, isSelf, DemoMsgHelper.getInstance().getMsgBarrageTxt(message));
        }

        @Override
        public int getItemCount() {
            return messages.length;
        }

        public void refresh() {
            messages = conversation.getAllMessages().toArray(new EMMessage[0]);

            ((Activity) getContext()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    notifyDataSetChanged();

                }
            });
        }

    }


    private class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView content;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            content = (TextView) itemView.findViewById(R.id.content);
        }
    }


    private void getKey(String mName, String startTime, String sponsor, String endTime) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", mName);
        jsonObject.addProperty("startTime", startTime);
        jsonObject.addProperty("sponsor", sponsor);
        jsonObject.addProperty("endTime", endTime);
        RestApi.getInstance().post(BaseApiConstants.API_PLAY_KEY, "", new OnRequestResultForCommon() {
            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
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
    protected boolean getFitsSystemWindows() {
        return false;
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        exitLive();
    }

    private void exitLive() {
        DialogUtils.showTipsSelectDialog(this, "确认结束直播？", "取消", "确定", new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("roomId", chatroomId);
                String json = jsonObject.toString();
                RestApi.getInstance().post(BaseApiConstants.API_END_MEETING, json, new OnRequestResultForCommon() {
                    @Override
                    public void onSuccess(String json) {
                        super.onSuccess(json);
//                        jsonObject.addProperty("startTime", roomId);
//                        mView.success();
                        finish();
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
        }, true);
    }
}
