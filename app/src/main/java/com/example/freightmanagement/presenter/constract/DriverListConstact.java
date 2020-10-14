package com.example.freightmanagement.presenter.constract;

import com.example.freightmanagement.Base.BaseView;
import com.example.freightmanagement.model.CarExecuteParam;

/**
 * Created by songdechuan on 2020/8/13.
 */

public interface DriverListConstact {

    void getList();

    void searchDriver(String searchKey);

    void delete(CarExecuteParam param);

    void getContractByDriverId(int driverId);

    interface View extends BaseView {
        void success(String msg);

 		void delResult(String json);

        void getContractResult(String json);

        void resloveSuccess(String json);
    }
}
