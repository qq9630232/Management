package com.example.freightmanagement.presenter.constract;

import com.example.freightmanagement.Base.BaseView;

import java.util.List;

/**
 * Created by songdechuan on 2020/8/13.
 */

public interface CarListManagerConstact {

    void getList();

    void searchCar(String key);

    void delete(List<Integer> ids);

    interface View extends BaseView {
        void carListResult(String msg);

        void delResult(String json);

    }
}
