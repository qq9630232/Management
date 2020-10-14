package com.example.freightmanagement.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freightmanagement.Bean.GiftBean;
import com.example.freightmanagement.R;
import com.example.freightmanagement.View.DemoConstants;
import com.example.freightmanagement.View.DemoHelper;
import com.example.freightmanagement.View.DemoMsgHelper;
import com.example.freightmanagement.View.MsgConstant;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMCustomMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessageBody;
import com.hyphenate.chat.EMTextMessageBody;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class IMListAdapter extends RecyclerView.Adapter <IMListAdapter.ViewHolder> {

    private final Context context;
//    EMMessage[] messages;
    private List<EMMessage> messages;
    private EMConversation conversation;

    public IMListAdapter(Context context, EMConversation conversation) {
        this.context = context;
        this.conversation = conversation;
//        messages = ;
        messages = Arrays.asList(conversation.getAllMessages().toArray(new EMMessage[0]));

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_room_msgs_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final EMMessage message = messages.get(position);
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
        span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.white)), 0, nickName.length() + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.gray_background)),
                nickName.length() + 1, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        name.setText(span);
    }

    private void showText(TextView name, String nickName, boolean isSelf, String content) {
        StringBuilder builder = new StringBuilder();
        builder.append(nickName).append(":").append(content);
        SpannableString span = new SpannableString(builder.toString());
        span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.white)), 0, nickName.length() + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(isSelf ? ContextCompat.getColor(context, R.color.white) : Color.parseColor("#FFC700")),
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
        span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.white)), 0, nickName.length() + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.gray_background)),
                nickName.length() + 1, nickName.length() + 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(Color.parseColor("#ff68ff95")),
                nickName.length() + 4, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        name.setText(span);
    }

    private void showPraiseMessage(TextView name, String nickName, boolean isSelf, EMMessage message) {
        String content = context.getString(R.string.em_live_msg_like, nickName, DemoMsgHelper.getInstance().getMsgPraiseNum(message));
        SpannableString span = new SpannableString(content);
        span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.white)), 0, nickName.length() + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.gray_background)),
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
        return messages.size() == 0?0 : messages.size();
    }

    public void refresh() {
        messages = Arrays.asList(conversation.getAllMessages().toArray(new EMMessage[0]));

        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }
    /**
     * 获取弹幕消息中的文本
     * @param msg
     * @return
     */
    public String getMsgBarrageTxt(EMMessage msg) {
//        if(!isBarrageMsg(msg)) {
//            return null;
//        }
        Map<String, String> params = getCustomMsgParams(msg);
        if(params.containsKey(MsgConstant.CUSTOM_BARRAGE_KEY_TXT)) {
            return params.get(MsgConstant.CUSTOM_BARRAGE_KEY_TXT);
        }
        return null;
    }
    /**
     * 获取自定义消息中的参数
     * @param message
     * @return
     */
    public Map<String, String> getCustomMsgParams(EMMessage message) {
        if(message == null) {
            return null;
        }
        EMMessageBody body = message.getBody();
        if(!(body instanceof EMCustomMessageBody)) {
            return null;
        }
        return ((EMCustomMessageBody) body).getParams();
    }
    public void refresh(List<EMMessage> messages) {
        EMMessage emMessage = messages.get(0);
        messages.add(emMessage);

//        String msgBarrageTxt = getMsgBarrageTxt(emMessage);
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView content;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            content = (TextView) itemView.findViewById(R.id.content);
        }
    }
}
