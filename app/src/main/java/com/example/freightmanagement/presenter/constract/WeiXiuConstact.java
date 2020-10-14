package com.example.freightmanagement.presenter.constract;

import com.example.freightmanagement.Base.BaseView;
import com.example.freightmanagement.Bean.ImageUploadBean;

import java.io.File;
import java.util.List;


/**
 * Created by songdechuan on 2020/8/6.
 */

public interface WeiXiuConstact {

    void addWeiXiu(String json);

    void getTestList(int id);

    void upload(List<String> file, int type);
    interface View extends BaseView{

        void mSuc();

        void testResult(String msg);

        void imageUrl(ImageUploadBean imageUploadBean);
    }
}
