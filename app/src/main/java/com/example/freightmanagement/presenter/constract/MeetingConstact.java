package com.example.freightmanagement.presenter.constract;


import com.example.freightmanagement.Base.BaseView;
import com.example.freightmanagement.Bean.CheliangByBean;
import com.example.freightmanagement.Bean.CheliangJcBean;
import com.example.freightmanagement.Bean.CheliangWeiXiuBean;

import java.util.List;

public interface MeetingConstact {

    /**
     * 获取会议室
     *
     * @param
     */
    void getAllMeetings();

    /**
     * 获取拉流地址
     */
    void getPullUrl(String key);

    interface View extends BaseView {
        void result(String json);

        void pullUrl(String json);

    }
}
