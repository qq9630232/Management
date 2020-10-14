package com.example.freightmanagement.presenter.constract;

import com.example.freightmanagement.Base.BaseView;
import com.example.freightmanagement.Bean.ImageUploadBean;
import com.example.freightmanagement.Bean.VehicleDetectionBean;

import java.util.List;


public interface VehicleDetectionConstact {

    void VehicleInformationData();

    void  addVehicleData(String json);

    void upload(List<String> file, final int type);

    interface View extends BaseView{

        void trainingList(VehicleDetectionBean vehicleDetectionBean);

        void mSuc();

        void imageUrl(ImageUploadBean imageUploadBean,int type);
    }
}
