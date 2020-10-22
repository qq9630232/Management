package com.example.freightmanagement.Activity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freightmanagement.Adapter.IMListAdapter;
import com.example.freightmanagement.R;
import com.example.freightmanagement.Utils.PermissionChecker;
import com.example.freightmanagement.Utils.Utils;
import com.example.freightmanagement.View.ChatRoomPresenter;
import com.example.freightmanagement.View.DemoMsgHelper;
import com.example.freightmanagement.View.LiveVideoView;
import com.example.freightmanagement.View.OnMsgCallBack;
import com.example.freightmanagement.View.RoomMessagesView;
import com.example.freightmanagement.common.EmClientRepository;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMChatRoom;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.pili.pldroid.player.widget.PLVideoView;

import java.util.List;

public class PlayerActivity extends LiveBaseActivity implements RoomMessagesView.MessageViewListener, ChatRoomPresenter.OnChatRoomListener, View.OnClickListener {

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
    private ImageView mIvLeftBack;
    private TextView mTvCount;
    private TextView mTvJoin;

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
//        EMClient.getInstance().chatManager().addMessageListener(msgListener);

        mIvLeftBack = findViewById(R.id.iv_left_back);
        mIvLeftBack.setOnClickListener(this);
        mTvCount = findViewById(R.id.tv_count);
        mTvJoin = findViewById(R.id.tv_join);
    }

    /**
     * 动态申请权限
     *
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

        EmClientRepository emClientRepository = new EmClientRepository();
        emClientRepository.getMembers(roomId, new EMValueCallBack() {
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

    }

    /**
     * 初始化发送消息按钮
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
        DemoMsgHelper.getInstance().init(roomId);
//        roomId = "127395385376769";
        presenter = new ChatRoomPresenter(this, roomId);
        presenter.setOnChatRoomListener(this);
        EMClient.getInstance().chatroomManager().joinChatRoom(roomId, new EMValueCallBack<EMChatRoom>() {

            @Override
            public void onSuccess(EMChatRoom value) {
                //加入聊天室成功
                Log.e(TAG, "onLoadData2Remote: ");
                EMClient.getInstance().chatroomManager().addChatRoomChangeListener(presenter);
                onMessageListInit();
            }

            @Override
            public void onError(final int error, String errorMsg) {
                //加入聊天室失败
                Log.e(TAG, "onLoadData2Remote: ");
            }
        });
        presenter.setOnChatRoomListener(this);

    }

    /**
     * 直播
     */
    private void live() {
        String mLiveUrl = getIntent().getStringExtra("live_url");
        mIsLiveStreaming = getIntent().getIntExtra("liveStreaming", 1) == 1;

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
        EMClient.getInstance().chatroomManager().leaveChatRoom(roomId);
        EMClient.getInstance().chatManager().removeMessageListener(presenter);

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

    @Override
    public void onChatRoomOwnerChanged(String chatRoomId, String newOwner, String oldOwner) {

    }
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
    @Override
    public void onChatRoomMemberAdded(String participant) {
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
            mListview.smoothScrollToPosition(adapter.getItemCount() - 1);
        }
    }

    @Override
    public void onMessageSelectLast() {

    }

    @Override
    public void onMessageChanged() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left_back:
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
