package com.example.freightmanagement.View;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.freightmanagement.Base.BaseApplication;
import com.example.freightmanagement.R;


/**
 * 下拉demo
 */

public class SimpleRefreshHeadView extends AbRefreshHeadView {
    private final Activity mContext;
    private ImageView mIvAnim;
    private TextView mTvTip;

    public SimpleRefreshHeadView(Activity context) {
        super(context);
        this.mContext=context;
    }

    @Override
    protected View onCreateView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.view_refresh_head, null);
    }

    @Override
    protected int onCreateRefreshLimitHeight() {
        return (int) getResources().getDimension(R.dimen.x120);
    }

    @Override
    protected void initView() {
        mIvAnim = (ImageView) findViewFromId(R.id.iv_refreshing);
        mTvTip = (TextView) findViewFromId(R.id.tv_tip);
    }

    /**
     * 获取屏幕高度
     */
    private int getScreenHeight() {
        WindowManager wm = (WindowManager) getContext().getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }


    private void rotationAnimator(float rotation) {
        ValueAnimator animator = ValueAnimator.ofFloat(mIvAnim.getRotation(), rotation);
        animator.setDuration(200).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mIvAnim.setRotation((Float) animation.getAnimatedValue());
            }
        });
        animator.start();
    }

    @Override
    protected void onPullingDown() {
        mIvAnim.setVisibility(View.VISIBLE);
        Animation anim=new AnimationUtils().loadAnimation(mContext,R.anim.image_enlarge);
        anim.setFillAfter(true);//动画执行完毕后停留在最后一帧
        mIvAnim.startAnimation(anim);
        mTvTip.setText("下拉刷新");
        rotationAnimator(0f);
    }

    @Override
    protected void onReleaseState() {
        mIvAnim.setVisibility(View.VISIBLE);
//        Animation anim=new AnimationUtils().loadAnimation(mContext,R.anim.image_enlarge);
//        anim.setFillAfter(true);//动画执行完毕后停留在最后一帧
//        mIvAnim.startAnimation(anim);
        mTvTip.setText("松开刷新");
//        rotationAnimator(180f);
    }

    @Override
    protected void onRefreshing() {
        mTvTip.setText("正在刷新");
        Animation animation = AnimationUtils.loadAnimation(BaseApplication.getApp(), R.anim.rotate);
        LinearInterpolator lin = new LinearInterpolator();//设置动画匀速运动
        animation.setInterpolator(lin);
        //让旋转动画一直转，不停顿的重点
        animation.setRepeatCount(-1);
        mIvAnim.startAnimation(animation);
    }

    @Override
    protected void onResultSuccess() {
        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //更改UI；
//                mTvTip.setText(mContext.getString(R.string.pull_to_refresh_suc));
                mIvAnim.clearAnimation();
                mIvAnim.setVisibility(View.VISIBLE);
            }
        });


    }

    @Override
    protected void onResultFail() {
        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvTip.setText(mContext.getString(R.string.refresh_f));
                mIvAnim.setVisibility(View.VISIBLE);
                mIvAnim.clearAnimation();
            }
        });

    }

}
