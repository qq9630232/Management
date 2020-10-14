package com.example.freightmanagement.presenter.constract;

import com.example.freightmanagement.Base.BaseView;
import com.example.freightmanagement.Bean.ImageUploadBean;

import java.io.File;
import java.util.List;


/**
 * Created by songdechuan on 2020/8/6.
 */

public interface BaoYangConstact {

    void getTrainingList(String carOwnerId,String content,double mileage,String picUrl,String time);

    void upload(List<String> file, final int type);
    interface View extends BaseView{

        void  mSuc();

        void imageUrl(ImageUploadBean imageUploadBean);

    }
}
