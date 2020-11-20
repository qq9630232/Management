package com.example.freightmanagement.presenter.constract;

import com.example.freightmanagement.Base.BaseView;
import com.example.freightmanagement.model.DriverInfoSubmitParam;

public interface SelectCarConstact {

    void getCar(int pageNum,int pageSize);

    void searchCar(String key);

    void submit(DriverInfoSubmitParam submitParam);


    interface View extends BaseView {
        void carList(String msg);

        void success(String json);

    }
}
