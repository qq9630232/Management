package com.example.freightmanagement.View;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freightmanagement.Bean.GiftBean;
import com.example.freightmanagement.R;
import com.example.freightmanagement.Utils.Utils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMCustomMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;

import java.util.Map;

/**
 * Created by wei on 2016/6/3.
 */
public class RoomMessagesView extends RelativeLayout{
    private EMConversation conversation;
    EditText editview;
    ImageView sendBtn;
    View sendContainer;
    ImageView closeView;
    Switch switchMsgType;
    boolean isBarrageMsg;
    //ImageView danmuImage;

    public boolean isBarrageShow = false;
    private int giftOiginMarginBottom;
    private int barrageOriginMarginTop;


    public RoomMessagesView(Context context) {
        super(context);
        init(context, null);
    }

    public RoomMessagesView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoomMessagesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.widget_room_messages, this);

        editview = (EditText) findViewById(R.id.edit_text);
        sendBtn = (ImageView) findViewById(R.id.btn_send);
        closeView = (ImageView) findViewById(R.id.close_image);
        sendContainer = findViewById(R.id.container_send);
        switchMsgType = findViewById(R.id.switch_msg_type);
        //danmuImage = (ImageView) findViewById(R.id.danmu_image);

    }

    public EditText getInputView(){
        return editview;
    }

    public void init(){
        addSoftKeyboardListener();

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setSize(0, (int) EaseCommonUtils.dip2px(getContext(), 4));
        itemDecoration.setDrawable(drawable);
//        listview.addItemDecoration(itemDecoration);
        sendBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(messageViewListener != null){
                    if(TextUtils.isEmpty(editview.getText())){
                        Toast.makeText(getContext(), "文字内容不能为空！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    messageViewListener.onMessageSend(editview.getText().toString(), isBarrageMsg);
                    editview.setText("");
                }
            }
        });
        closeView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyBoard();
                if(messageViewListener != null){
                    messageViewListener.onHiderBottomBar();
                }
            }
        });
        switchMsgType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isBarrageMsg = isChecked;
            }
        });
//        listview.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                hideSoftKeyBoard();
//                return false;
//            }
//        });

        //danmuImage.setOnClickListener(new OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        if(danmuImage.isSelected()){
        //            danmuImage.setSelected(false);
        //            isBarrageShow = false;
        //        }else {
        //            danmuImage.setSelected(true);
        //            isBarrageShow = true;
        //        }
        //    }
        //});

    }

    private void addSoftKeyboardListener() {
        if(getContext() instanceof Activity) {
            SoftKeyboardChangeHelper.setOnSoftKeyboardChangeListener((Activity) getContext(), new SoftKeyboardChangeHelper.OnSoftKeyboardChangeListener() {
                @Override
                public void keyboardShow(int height) {
                    final ViewGroup parent = (ViewGroup) (RoomMessagesView.this.getParent());
                    startAnimation(height, 100, new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            int value = (int) animation.getAnimatedValue();
                            parent.scrollTo(0, value);
                        }
                    });

                    int childCount = parent.getChildCount();
                    for(int i = 0; i < childCount; i++) {
                        final View child = parent.getChildAt(i);
                        if(child instanceof SingleBarrageView) {
                            child.setBackgroundColor(Color.parseColor("#01ffffff"));
                            final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) child.getLayoutParams();
                            barrageOriginMarginTop = params.topMargin;
                            startAnimation(height - barrageOriginMarginTop * 3, 100, new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator animation) {
                                    params.topMargin = (int) animation.getAnimatedValue() + barrageOriginMarginTop;
                                    child.setLayoutParams(params);
                                }
                            });
                        }
                        if(child instanceof ShowGiveGiftView) {
                            final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) child.getLayoutParams();
                            giftOiginMarginBottom = params.bottomMargin;
                            startAnimation(giftOiginMarginBottom  - 10, 100, new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator animation) {
                                    params.bottomMargin = giftOiginMarginBottom - (int) animation.getAnimatedValue();
                                    child.setLayoutParams(params);
                                }
                            });
                        }
                    }
                }

                @Override
                public void keyboardHide(final int height) {
                    final ViewGroup parent = (ViewGroup) (RoomMessagesView.this.getParent());
                    startAnimation(height, 100, new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            int value = (int) animation.getAnimatedValue();
                            parent.scrollTo(0, height - value);
                        }
                    });
                    int childCount = parent.getChildCount();
                    for(int i = 0; i < childCount; i++) {
                        final View child = parent.getChildAt(i);
                        if(child instanceof SingleBarrageView) {
                            child.setBackground(null);
                            final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) child.getLayoutParams();
                            startAnimation(height - barrageOriginMarginTop * 3, 100, new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator animation) {
                                    params.topMargin = height - barrageOriginMarginTop * 2 -  (int) animation.getAnimatedValue();
                                    child.setLayoutParams(params);
                                }
                            });
                        }
                        if(child instanceof ShowGiveGiftView) {
                            final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) child.getLayoutParams();
                            startAnimation(giftOiginMarginBottom - 10, 100, new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator animation) {
                                    params.bottomMargin = 10 + (int) animation.getAnimatedValue();
                                    child.setLayoutParams(params);
                                }
                            });
                        }
                    }
                    setShowInputView(false);
                    if(messageViewListener != null){
                        messageViewListener.onHiderBottomBar();
                    }
                }
            });
        }

    }

    /**
     * 开始动画
     * @param values
     * @param listener
     */
    private void startAnimation(int values, int duration, ValueAnimator.AnimatorUpdateListener listener) {
        ValueAnimator animator = ValueAnimator.ofInt(values);
        animator.addUpdateListener(listener);
        animator.setDuration(duration);
        animator.start();
    }

    public void hideKeyboard() {
        Utils.hideKeyboard(this);
    }

    public void setShowInputView(boolean showInputView){
        if(showInputView){
            sendContainer.setVisibility(View.VISIBLE);
        }else{
            sendContainer.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏输入框及软键盘
     */
    public void hideSoftKeyBoard() {
        hideKeyboard();
        setShowInputView(false);
    }

    private MessageViewListener messageViewListener;
    public interface MessageViewListener{
        void onMessageSend(String content, boolean isBarrageMsg);
        void onItemClickListener(EMMessage message);
        void onHiderBottomBar();
    }

    public void setMessageViewListener(MessageViewListener messageViewListener){
        this.messageViewListener = messageViewListener;
    }

    public void refresh(){
//        if(adapter != null){
//            adapter.refresh();
//        }
    }

    public void refreshSelectLast(){

    }

}
