package com.example.freightmanagement.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.freightmanagement.Adapter.SelectCarAdapter;
import com.example.freightmanagement.Base.BaseActivity;
import com.example.freightmanagement.Base.BaseResponse;
import com.example.freightmanagement.Bean.SelectCarBean;
import com.example.freightmanagement.R;
import com.example.freightmanagement.Utils.StringUtil;
import com.example.freightmanagement.Utils.ToastUtils;
import com.example.freightmanagement.View.PullListener;
import com.example.freightmanagement.View.PullRecyclerView;
import com.example.freightmanagement.View.SimpleRefreshHeadView;
import com.example.freightmanagement.View.SimpleRefreshMoreView;
import com.example.freightmanagement.presenter.SelectCarPresenter;
import com.google.gson.Gson;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;

public class SelectCarActivity extends BaseActivity<SelectCarPresenter> implements SelectCarPresenter.View, View.OnClickListener, PullListener {
    private PullRecyclerView mRecyclerView;
    private TextView mTvSrue;
    //    private DriverInfoSubmitParam submitParam;
    private SelectCarAdapter selectCarAdapter;
    private List<SelectCarBean.DataBean.ContentBean> data;
    private Integer id;
    private Integer enterpriseId;
    private String sealUrl;
    private String address;
    private String legalPerson;
    private String signature;
    private String companyName;
    private TextView et_search;
    private ImageButton ib_search;
    private int page = 1;
    private int tiao = 6;

    @Override
    public int setLayoutResource() {
        return R.layout.activity_select_car;
    }

    @Override
    protected void onInitView() {
        setDefaultTitle("车辆选择");
        et_search = findViewById(R.id.et_search);
        ib_search = findViewById(R.id.ib_search);
        ib_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCar();
                et_search.clearFocus();

            }
        });
        mRecyclerView = findViewById(R.id.recyclerView);
        mTvSrue = findViewById(R.id.tv_srue);
        mTvSrue.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        selectCarAdapter = new SelectCarAdapter(this);

        mRecyclerView.setHeadRefreshView(new SimpleRefreshHeadView(this))
                .setMoreRefreshView(new SimpleRefreshMoreView(this))
                .setUseLoadMore(true)
                .setUseRefresh(true)
                .setPullLayoutManager(linearLayoutManager)
                .setPullListener(this)
                .setPullItemAnimator(null)
                .build(selectCarAdapter);

        selectCarAdapter.setOnItemClickListener(new SelectCarAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                id = data.get(position).getId();
                enterpriseId = data.get(position).getEnterpriseId();
                SelectCarBean.DataBean.ContentBean.EnterpriseBoBean enterpriseBo = data.get(position).getEnterpriseBo();
                if (enterpriseBo != null) {
                    sealUrl = enterpriseBo.getSealUrl();
                    SelectCarBean.DataBean.ContentBean.EnterpriseBoBean.CertificateBusinessBoBean certificateBusinessBo = enterpriseBo.getCertificateBusinessBo();
                    address = certificateBusinessBo.getRegistrationAuthority();
                    legalPerson = certificateBusinessBo.getLegalPerson();
                    signature = enterpriseBo.getSignature();
                    companyName = certificateBusinessBo.getName();
                }


            }
        });
        et_search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager manager = ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE));
                    if (manager != null)
                        manager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });
    }

    private void searchCar() {
        String serNo = et_search.getText().toString();
        if (serNo.isEmpty()) {
            mPresenter.getCar(page,tiao);
        } else {
            mPresenter.searchCar(serNo);
        }
    }

    @Override
    protected void onLoadData2Remote() {
//        submitParam = (DriverInfoSubmitParam) getIntent().getSerializableExtra("driverInfo");
        loadData(0);
    }

    @Override
    public void carList(String msg) {
        SelectCarBean selectCarBean = new Gson().fromJson(msg, SelectCarBean.class);
        data = selectCarBean.getData().getContent();
        selectCarAdapter.setData(data);
        mRecyclerView.onComplete(true);
    }

    @Override
    public void success(String json) {
        BaseResponse baseResponse = new Gson().fromJson(json, BaseResponse.class);
        int code = baseResponse.getCode();
        if (code == 0) {
            ToastUtils.popUpToast("提交成功");
            Intent intent = new Intent(this, MainActivity.class);
//            intent.putExtra("name",submitParam.getCertificateIDBo().getName());
//            intent.putExtra("certificateNo",submitParam.getCertificateDriverBo().getFileNumber());
//            intent.putExtra("tel", PrefUtilsData.getMobile());
//            intent.putExtra("carId",id);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected SelectCarPresenter onInitLogicImpl() {
        return new SelectCarPresenter();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_srue:
                if (StringUtil.isEmpty(id)) {
                    ToastUtils.popUpToast("请选择一辆车");
                    return;
                }
                Intent intent = new Intent(this, EmploymentContractActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("enterpriseId", enterpriseId);
                intent.putExtra("address", address);
                intent.putExtra("legalPerson", legalPerson);
                intent.putExtra("sealUrl", sealUrl);
                intent.putExtra("signature", signature);
                intent.putExtra("companyName", companyName);

                startActivity(intent);
                break;
        }
    }

    @Override
    public void onRefresh() {
        page=1;
        loadData(1000L);
    }

    @Override
    public void onLoadMore() {
       page=page+1;
        loadData(1000L);
    }

    private void loadData(final long sleep) {
        Observable.create(new ObservableOnSubscribe<String[]>() {
            @Override
            public void subscribe(ObservableEmitter<String[]> e) throws Exception {
                SystemClock.sleep(sleep);
                mPresenter.getCar(page,tiao);

            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subject<String[]>() {
                    @Override
                    public boolean hasObservers() {
                        return false;
                    }

                    @Override
                    public boolean hasThrowable() {
                        return false;
                    }

                    @Override
                    public boolean hasComplete() {
                        return false;
                    }

                    @Override
                    public Throwable getThrowable() {
                        return null;
                    }

                    @Override
                    protected void subscribeActual(Observer<? super String[]> observer) {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String[] value) {
                        mRecyclerView.onComplete(value.length > 0);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
