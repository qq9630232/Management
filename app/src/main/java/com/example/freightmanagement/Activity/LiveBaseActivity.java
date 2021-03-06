package com.example.freightmanagement.Activity;

import android.os.Bundle;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import com.example.freightmanagement.Base.BaseActivity;


/**
 * Created by wei on 2016/6/12.
 */
public abstract class LiveBaseActivity extends BaseActivity {
    protected static final String TAG = "LiveActivity";
//    ImageView coverImage;
//    protected LiveRoom liveRoom;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        liveRoom = (LiveRoom) getIntent().getSerializableExtra("liveroom");
//        if(liveRoom == null) {
//            finish();
//            return;
//        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        onActivityCreated(savedInstanceState);
        initView();
        initListener();
        initData();
    }

    protected abstract void onActivityCreated(@Nullable Bundle savedInstanceState);

    protected void initView() {
//        coverImage = findViewById(R.id.cover_image);
//        Glide.with(mContext)
//                .load(liveRoom.getCover())
//                .error(R.drawable.em_live_default_bg)
//                .into(coverImage);
    }

    protected void initListener() {}

    protected void initData() {
    }
}
