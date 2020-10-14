package com.example.freightmanagement.Activity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freightmanagement.Adapter.IMListAdapter;
import com.example.freightmanagement.Base.BaseActivity;
import com.example.freightmanagement.Base.BaseApiConstants;
import com.example.freightmanagement.Base.BaseResponse;
import com.example.freightmanagement.R;
import com.example.freightmanagement.Utils.Config;
import com.example.freightmanagement.Utils.Network.OnRequestResultForCommon;
import com.example.freightmanagement.Utils.Network.RestApi;
import com.example.freightmanagement.Utils.PermissionChecker;
import com.example.freightmanagement.Utils.ToastUtils;
import com.example.freightmanagement.Utils.Utils;
import com.example.freightmanagement.View.ChatRoomPresenter;
import com.example.freightmanagement.View.LiveVideoView;
import com.example.freightmanagement.View.OnMsgCallBack;
import com.example.freightmanagement.View.RoomMessagesView;
import com.hyphenate.EMMessageListener;
import com.example.freightmanagement.widget.MediaController;
import com.example.freightmanagement.widget.MediaController.OnClickSpeedAdjustListener;
import com.google.gson.Gson;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLOnAudioFrameListener;
import com.pili.pldroid.player.PLOnBufferingUpdateListener;
import com.pili.pldroid.player.PLOnCompletionListener;
import com.pili.pldroid.player.PLOnErrorListener;
import com.pili.pldroid.player.PLOnInfoListener;
import com.pili.pldroid.player.PLOnVideoFrameListener;
import com.pili.pldroid.player.PLOnVideoSizeChangedListener;
import com.pili.pldroid.player.widget.PLVideoView;

import java.util.List;

public class PlayerActivity extends LiveBaseActivity implements RoomMessagesView.MessageViewListener {

    private static final String TAG = "PlayerActivity";
    private LiveVideoView mVideoView;
    private LinearLayout mLoadingView;
    private boolean mIsLiveStreaming;
//    private MediaController mMediaController;
    private int mDisplayAspectRatio = PLVideoView.ASPECT_RATIO_FIT_PARENT;
    private RecyclerView mListview;
    private RoomMessagesView messageView;
    protected ChatRoomPresenter presenter;
    private String roomId;
    private EMConversation conversation;
    private IMListAdapter adapter;
    @Override
    protected boolean getFitsSystemWindows() {
        return false;
    }
    @Override
    public int setLayoutResource() {
        return R.layout.activity_player;
    }

    @Override
    protected void onInitView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mVideoView = findViewById(R.id.videoView);
        mLoadingView = findViewById(R.id.LoadingView);
        mListview = findViewById(R.id.listview);
        messageView = findViewById(R.id.message_view);
        EMClient.getInstance().chatManager().addMessageListener(msgListener);

    }

    /**
     * 动态申请权限
     * @return
     */
    public boolean isPermissionOK() {
        PermissionChecker checker = new PermissionChecker(this);
        boolean isPermissionOK = Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checker.checkPermission();
        if (!isPermissionOK) {
            Toast.makeText(this, "Some permissions is not approved !!!", Toast.LENGTH_SHORT).show();
        }
        return isPermissionOK;
    }

    @Override
    protected void onLoadData2Remote() {
        isPermissionOK();
        live();
        sendMessage();
        EMClient.getInstance().chatManager().addMessageListener(presenter);
        onMessageListInit();
    }

    /**
     * 初始化发送消息按钮
     *
     */
    protected void onMessageListInit() {
        messageView.init();
        conversation = EMClient.getInstance().chatManager().getConversation(roomId, EMConversation.EMConversationType.ChatRoom, true);
        adapter = new IMListAdapter(getContext(), conversation);
        mListview.setLayoutManager(new LinearLayoutManager(getContext()));
        mListview.setAdapter(adapter);
        messageView.setMessageViewListener(this);
    }
    /**
     * 即时通讯
     */
    private void sendMessage() {
        roomId = getIntent().getStringExtra("roomId");
        roomId = "127395385376769";
        presenter = new ChatRoomPresenter(this, roomId);
    }

    /**
     * 直播
     */
    private void live() {
        String mLiveUrl = getIntent().getStringExtra("live_url");
        mIsLiveStreaming = getIntent().getIntExtra("liveStreaming", 1) == 1;

//        mVideoView.setMediaController(mMediaController);
        mVideoView.setVideoPath(mLiveUrl);
    }


    public void onClickSwitchScreen(View v) {
        mDisplayAspectRatio = (mDisplayAspectRatio + 1) % 5;
        mVideoView.setDisplayAspectRatio(mDisplayAspectRatio);
        switch (mVideoView.getDisplayAspectRatio()) {
            case PLVideoView.ASPECT_RATIO_ORIGIN:
                Utils.showToastTips(this, "Origin mode");
                break;
            case PLVideoView.ASPECT_RATIO_FIT_PARENT:
                Utils.showToastTips(this, "Fit parent !");
                break;
            case PLVideoView.ASPECT_RATIO_PAVED_PARENT:
                Utils.showToastTips(this, "Paved parent !");
                break;
            case PLVideoView.ASPECT_RATIO_16_9:
                Utils.showToastTips(this, "16 : 9 !");
                break;
            case PLVideoView.ASPECT_RATIO_4_3:
                Utils.showToastTips(this, "4 : 3 !");
                break;
            default:
                break;
        }
    }

    private String bytesToHex(byte[] bytes) {
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    private void updateStatInfo() {
        long bitrate = mVideoView.getVideoBitrate() / 1024;
        final String stat = bitrate + "kbps, " + mVideoView.getVideoFps() + "fps";
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                mStatInfoTextView.setText(stat);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        mMediaController.getWindow().dismiss();
        mVideoView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.stopPlayback();
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);

    }

    @Override
    public void onMessageSend(String content, boolean isBarrageMsg) {
        presenter.sendTxtMsg(content, isBarrageMsg, new OnMsgCallBack() {
            @Override
            public void onSuccess(EMMessage message) {
                //刷新消息列表
                if (adapter != null) {
                    adapter.refresh();
                    mListview.smoothScrollToPosition(adapter.getItemCount() - 1);
                }
//                                if (isBarrageMsg) {
//                                    barrageView.addData(message);
//                                }
            }
        });
    }

    @Override
    public void onItemClickListener(EMMessage message) {

    }

    @Override
    public void onHiderBottomBar() {

    }

    @Override
    protected void onActivityCreated(@Nullable Bundle savedInstanceState) {

    }

    EMMessageListener msgListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            //收到消息
            adapter.refresh(messages);
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            //收到透传消息
        }

        @Override
        public void onMessageRead(List<EMMessage> messages) {
            //收到已读回执
        }

        @Override
        public void onMessageDelivered(List<EMMessage> message) {
            //收到已送达回执
        }
        @Override
        public void onMessageRecalled(List<EMMessage> messages) {
            //消息被撤回
        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {
            //消息状态变动
        }
    };



}
