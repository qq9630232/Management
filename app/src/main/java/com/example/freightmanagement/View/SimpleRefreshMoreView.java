package com.example.freightmanagement.View;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.freightmanagement.R;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by ${ChenJC} on 2018/2/24.
 * 上拉更多
 */

public class SimpleRefreshMoreView extends AbRefreshMoreView {
    private TextView mTvTip;
    private ValueAnimator mAnim;
    private ImageView mIvAnim;
    private LinearLayout lin_layout;

    public SimpleRefreshMoreView(Context context) {
        super(context);
    }

    @Override
    protected void onNormalState() {
        mTvTip.setVisibility(View.VISIBLE);
        stopAnimation();
        lin_layout.setVisibility(VISIBLE);
        EventBus.getDefault().post("no_data");
        mTvTip.setText("已全部加载完毕");
        mIvAnim.setVisibility(View.GONE);
    }

    @Override
    protected void onLoadingMore() {
        mTvTip.setText("正在加载");
        lin_layout.setVisibility(VISIBLE);
        mIvAnim.setVisibility(View.VISIBLE);
        startAnimation();
    }

    @Override
    protected void onResultSuccess() {
        stopAnimation();
        lin_layout.setVisibility(VISIBLE);
        mTvTip.setText(mContext.getString(R.string.pull_to_refresh_footer_pull_label));
        onResult();
    }

    @Override
    protected void onResultFail() {
        handler.removeCallbacks(task);
        handler.postDelayed(task, delayTime);
    }

    private void onResult() {
        mTvTip.setVisibility(View.VISIBLE);
        mIvAnim.setVisibility(View.GONE);
    }

    private void startAnimation() {
        mAnim = ValueAnimator.ofFloat(mIvAnim.getRotation(), mIvAnim.getRotation() + 359);
        mAnim.setInterpolator(new LinearInterpolator());
        mAnim.setRepeatCount(ValueAnimator.INFINITE);
        mAnim.setDuration(1000);
        mAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mIvAnim.setRotation((Float) animation.getAnimatedValue());
            }
        });
        mAnim.start();
    }

    public void stopAnimation() {
        if (null != mAnim) {
            mAnim.end();
        }
    }

    @Override
    protected View onCreateView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.loadmore_default_footer, null);
    }

    @Override
    protected void initView() {
        mIvAnim = (ImageView) findViewFromId(R.id.pull_to_load_progress);
        mTvTip = (TextView) findViewFromId(R.id.loadmore_default_footer_tv);
        lin_layout = (LinearLayout) findViewFromId(R.id.lin_layout);
        lin_layout.setVisibility(GONE);
    }


    private Handler handler = new Handler();
    private long delayTime = 3000;
    private final Runnable task = new Runnable() {
        @Override
        public void run() {
            stopAnimation();
            mTvTip.setText(mContext.getString(R.string.load_more_f));
            onResult();
            handler.postDelayed(task, delayTime);

        }
    };
}
