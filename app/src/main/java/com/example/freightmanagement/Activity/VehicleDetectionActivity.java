package com.example.freightmanagement.Activity;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freightmanagement.Adapter.GridAdapter;
import com.example.freightmanagement.Adapter.VehicleFetectionXx2Adapter;
import com.example.freightmanagement.Adapter.VehicleFetectionXx3Adapter;
import com.example.freightmanagement.Adapter.VehicleFetectionXxAdapter;
import com.example.freightmanagement.Base.BaseActivity;
import com.example.freightmanagement.Bean.ImageUploadBean;
import com.example.freightmanagement.Bean.VehicleDetectionBean;
import com.example.freightmanagement.Bean.VerAddBean;
import com.example.freightmanagement.R;
import com.example.freightmanagement.Utils.DatePickerDialog;
import com.example.freightmanagement.Utils.OnItemClickListener2;
import com.example.freightmanagement.Utils.PrefUtilsData;
import com.example.freightmanagement.Utils.StringUtil;
import com.example.freightmanagement.Utils.ToastUtils;
import com.example.freightmanagement.listener.OnPicturesClickListener;
import com.example.freightmanagement.presenter.VehicleDetectionPresenter;
import com.giftedcat.picture.lib.PictureUseHelpr;
import com.giftedcat.picture.lib.selector.MultiImageSelector;
import com.google.gson.Gson;
import static com.example.freightmanagement.common.ImageUploadConstants.UPLOAD_VEHICLE;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class VehicleDetectionActivity extends BaseActivity<VehicleDetectionPresenter> implements VehicleDetectionPresenter.View, View.OnClickListener {


    private RecyclerView recyclerView1;
    private TextView et_real_name,et_siji_name;
    private RecyclerView recycler2;
    private RecyclerView recycler3;
    private RecyclerView recycler1;
    private VehicleFetectionXxAdapter adapter1;
    private VehicleFetectionXx2Adapter adapter2;
    private VehicleFetectionXx3Adapter adapter3;
    private  int type = 1;

    /**
     * z
     *  上传检查照片
     */
    /**
     * 添加照片
     */
    public static final int UPLOAD_VEHICLE1 =1001;
    public static final int UPLOAD_VEHICLE2 =1002;
    public static final int UPLOAD_VEHICLE3 =1003;
    private List<String> mSelect1;
    private List<String> mSelect2;
    private List<String> mSelect3;
    private RecyclerView mRvImages1;
    private RecyclerView mRvImages2;
    private RecyclerView mRvImages3;
    private GridAdapter gridAdapter1;
    private GridAdapter gridAdapter2;
    private GridAdapter gridAdapter3;
    private PictureUseHelpr helpr1;
    private PictureUseHelpr helpr2;
    private PictureUseHelpr helpr3;
    private static final int REQUEST_IMAGE1 = 101;
    private static final int REQUEST_IMAGE2 = 102;
    private static final int REQUEST_IMAGE3 = 103;
    private String url1 = "";
    private String url2 = "";
    private String url3 = "";
    private List<String> imageList1 = new ArrayList<>();
    private List<String> imageList2 = new ArrayList<>();
    private List<String> imageList3 = new ArrayList<>();


    @Override
    public int setLayoutResource() {
        return R.layout.activity_vehicle_detection;
    }

    @Override
    protected void onInitView() {
        setDefaultTitle("车辆检查情况");
        bindView(R.id.tv_srue).setOnClickListener(this);
        et_real_name = bindView(R.id.et_real_name);
        et_siji_name = bindView(R.id.et_siji_name);

        TextView textView1 = findViewById(R.id.text1);
        TextView textView2 = findViewById(R.id.text2);
        TextView textView3 = findViewById(R.id.text3);
        recycler1 = findViewById(R.id.recycler1);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler1.setLayoutManager(gridLayoutManager);
        adapter1 = new VehicleFetectionXxAdapter(this);
        recycler1.setAdapter(adapter1);

        mRvImages1 = findViewById(R.id.rv_images1);
        mSelect1 = new ArrayList<>();
        helpr1 = PictureUseHelpr.init(this).
                setMaxNum(9).
                origin(mSelect1).
                bindRecyclerView(mRvImages1, R.id.iv_thum);
        initGridAdapter1();

        recycler2 = findViewById(R.id.recycler2);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler2.setLayoutManager(gridLayoutManager2);
        adapter2 = new VehicleFetectionXx2Adapter(this);
        recycler2.setAdapter(adapter2);

        mRvImages2 = findViewById(R.id.rv_images2);
        mSelect2 = new ArrayList<>();
        helpr2 = PictureUseHelpr.init(this).
                setMaxNum(9).
                origin(mSelect2).
                bindRecyclerView(mRvImages2, R.id.iv_thum);
        initGridAdapter2();

        recycler3 = findViewById(R.id.recycler3);
        GridLayoutManager gridLayoutManager3 = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler3.setLayoutManager(gridLayoutManager3);
        adapter3 = new VehicleFetectionXx3Adapter(this);
        recycler3.setAdapter(adapter3);

        mRvImages3 = findViewById(R.id.rv_images3);
        mSelect3 = new ArrayList<>();
        helpr3 = PictureUseHelpr.init(this).
                setMaxNum(9).
                origin(mSelect3).
                bindRecyclerView(mRvImages3, R.id.iv_thum);
        initGridAdapter3();

        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
    }

    private void initGridAdapter1() {
        gridAdapter1 = new GridAdapter(this, mSelect1);

        mRvImages1.setLayoutManager(new GridLayoutManager(this, 3));
        mRvImages1.setAdapter(gridAdapter1);
        gridAdapter1.setOnAddPicturesListener(new OnPicturesClickListener() {
            @Override
            public void onClick(int position) {
                helpr1.show(position);
            }

            @Override
            public void onAdd() {
                helpr1.pickImage(REQUEST_IMAGE1);
            }
        });
    }

    private void initGridAdapter2() {
        gridAdapter2 = new GridAdapter(this, mSelect2);

        mRvImages2.setLayoutManager(new GridLayoutManager(this, 3));
        mRvImages2.setAdapter(gridAdapter2);
        gridAdapter2.setOnAddPicturesListener(new OnPicturesClickListener() {
            @Override
            public void onClick(int position) {
                helpr2.show(position);
            }

            @Override
            public void onAdd() {
                helpr2.pickImage(REQUEST_IMAGE2);
            }
        });
    }

    private void initGridAdapter3() {
        gridAdapter3 = new GridAdapter(this, mSelect3);

        mRvImages3.setLayoutManager(new GridLayoutManager(this, 3));
        mRvImages3.setAdapter(gridAdapter3);
        gridAdapter3.setOnAddPicturesListener(new OnPicturesClickListener() {
            @Override
            public void onClick(int position) {
                helpr3.show(position);
            }

            @Override
            public void onAdd() {
                helpr3.pickImage(REQUEST_IMAGE3);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE1) {
            if (resultCode == RESULT_OK) {
                List<String> select = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                mSelect1.clear();
                mSelect1.addAll(select);
                Luban.with(this)
                        .load(mSelect1)
                        .ignoreBy(100)
//                        .setTargetDir(getPath())
                        .filter(new CompressionPredicate() {
                            @Override
                            public boolean apply(String path) {
                                return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".png") || path.toLowerCase().endsWith(".jpg"));
                            }
                        })
                        .setCompressListener(new OnCompressListener() {
                            @Override
                            public void onStart() {
                                // TODO 压缩开始前调用，可以在方法内启动 loading UI
                            }

                            @Override
                            public void onSuccess(File file) {
                                if(StringUtil.isNotEmpty(file.getAbsolutePath())){
                                    // TODO 压缩成功后调用，返回压缩后的图片文件
                                    imageList1.add(file.getAbsolutePath());
                                }

                            }

                            @Override
                            public void onError(Throwable e) {
                                // TODO 当压缩过程出现问题时调用
                            }
                        }).launch();
                gridAdapter1.notifyDataSetChanged();
            }
        }
        if (requestCode == REQUEST_IMAGE2) {
            if (resultCode == RESULT_OK) {
                List<String> select = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                mSelect2.clear();
                mSelect2.addAll(select);
                Luban.with(this)
                        .load(mSelect2)
                        .ignoreBy(100)
//                        .setTargetDir(getPath())
                        .filter(new CompressionPredicate() {
                            @Override
                            public boolean apply(String path) {
                                return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".png") || path.toLowerCase().endsWith(".jpg"));
                            }
                        })
                        .setCompressListener(new OnCompressListener() {
                            @Override
                            public void onStart() {
                                // TODO 压缩开始前调用，可以在方法内启动 loading UI
                            }

                            @Override
                            public void onSuccess(File file) {
                                if(StringUtil.isNotEmpty(file.getAbsolutePath())){
                                    // TODO 压缩成功后调用，返回压缩后的图片文件
                                    imageList2.add(file.getAbsolutePath());
                                }

                            }

                            @Override
                            public void onError(Throwable e) {
                                // TODO 当压缩过程出现问题时调用
                            }
                        }).launch();
                gridAdapter2.notifyDataSetChanged();
            }
        }
        if (requestCode == REQUEST_IMAGE3) {
            if (resultCode == RESULT_OK) {
                List<String> select = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                mSelect3.clear();
                mSelect3.addAll(select);
                Luban.with(this)
                        .load(mSelect3)
                        .ignoreBy(100)
//                        .setTargetDir(getPath())
                        .filter(new CompressionPredicate() {
                            @Override
                            public boolean apply(String path) {
                                return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".png") || path.toLowerCase().endsWith(".jpg"));
                            }
                        })
                        .setCompressListener(new OnCompressListener() {
                            @Override
                            public void onStart() {
                                // TODO 压缩开始前调用，可以在方法内启动 loading UI
                            }

                            @Override
                            public void onSuccess(File file) {
                                if(StringUtil.isNotEmpty(file.getAbsolutePath())){
                                    // TODO 压缩成功后调用，返回压缩后的图片文件
                                    imageList3.add(file.getAbsolutePath());
                                }

                            }

                            @Override
                            public void onError(Throwable e) {
                                // TODO 当压缩过程出现问题时调用
                            }
                        }).launch();
                gridAdapter3.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onLoadData2Remote() {
        et_real_name.setText(getIntent().getStringExtra("name1"));
        et_siji_name.setText(getIntent().getStringExtra("name2"));
        mPresenter.VehicleInformationData();
    }



    @Override
    protected VehicleDetectionPresenter onInitLogicImpl() {
        return new VehicleDetectionPresenter();
    }
    private VerAddBean verAddBean=new VerAddBean();
    private VerAddBean.CompleteBosBean completeBosBean;
    private List<VerAddBean.CompleteBosBean> lisBean1=new ArrayList<>();
    private List<VerAddBean.CompleteBosBean> lisBean2=new ArrayList<>();
    private List<VerAddBean.CompleteBosBean> lisBean3=new ArrayList<>();

    @Override
    public void trainingList(final VehicleDetectionBean vehicleDetectionBean) {
        adapter1.setPages(vehicleDetectionBean.getData().getType1());
        final List<VehicleDetectionBean.DataBean.Type1Bean> type1Beans = vehicleDetectionBean.getData().getType1();
        List<VehicleDetectionBean.DataBean.Type2Bean> type2Beans = vehicleDetectionBean.getData().getType2();
        List<VehicleDetectionBean.DataBean.Type3Bean> type3Beans = vehicleDetectionBean.getData().getType3();
        for (VehicleDetectionBean.DataBean.Type1Bean type1Bean : type1Beans) {
            completeBosBean = new VerAddBean.CompleteBosBean();
            completeBosBean.setChechDataId(type1Bean.getId());
//            completeBosBean.setReslut(type1Bean.getItem());
            completeBosBean.setState(0);
            lisBean1.add(completeBosBean);
        }
        for (VehicleDetectionBean.DataBean.Type2Bean type2Bean : type2Beans) {
            completeBosBean = new VerAddBean.CompleteBosBean();

            completeBosBean.setChechDataId(type2Bean.getId());
//            completeBosBean.setReslut(type2Bean.getItem());
            completeBosBean.setState(0);

            lisBean2.add(completeBosBean);
        }
        for (VehicleDetectionBean.DataBean.Type3Bean type3Bean : type3Beans) {
            completeBosBean = new VerAddBean.CompleteBosBean();

            completeBosBean.setChechDataId(type3Bean.getId());
//            completeBosBean.setReslut(type3Bean.getItem());
            completeBosBean.setState(0);
            lisBean3.add(completeBosBean);
        }
        adapter1.setOnItemClickListener(new OnItemClickListener2() {
            @Override
            public void onItemClick(int position, String edte, boolean isCheck) {
                type = 1;
                for (int i = 0; i < lisBean1.size(); i++) {
                    VerAddBean.CompleteBosBean bosBean = lisBean1.get(position);
                    if(position == i){
                        if(isCheck){
                            bosBean.setState(1);
                            if(position == lisBean1.size() -1){
                                bosBean.setReslut(edte);
                            }
                        }else {
                            bosBean.setState(0);
                        }
                        break;
                    }
                }
                verAddBean.setCompleteBos(lisBean1);
            }
        });
        adapter2.setPages(vehicleDetectionBean.getData().getType2());
        adapter2.setOnItemClickListener(new OnItemClickListener2() {
            @Override
            public void onItemClick(int position, String edte, boolean isCheck) {
                type  = 2;
//                completeBosBean.setReslut(edte);
//                completeBosBean.setChechDataId(lisBean1.get(position).getChechDataId());
                for (int i = 0; i < lisBean2.size(); i++) {
                    VerAddBean.CompleteBosBean bosBean = lisBean2.get(position);
                    if(position == i){
                        if(isCheck){
                            bosBean.setState(1);
//                            bosBean.setReslut(edte);
                            if(position == lisBean2.size() -1){
                                bosBean.setReslut(edte);
                            }
//                            lisBean1.set(position, VehicleDetectionActivity.this.completeBosBean);
                        }else {
                            bosBean.setState(0);
//                            lisBean1.(position, VehicleDetectionActivity.this.completeBosBean);
                        }
                        break;
                    }
                }
                verAddBean.setCompleteBos(lisBean2);


//                completeBosBean.setChechDataId(vehicleDetectionBean.getData().getType2().get(position).getId());
//                completeBosBean.setState(1);
//                completeBosBean.setReslut(edte);
//                lisBean.add(completeBosBean);
//                verAddBean.setCompleteBos(lisBean);
            }
        });
        adapter3.setPages(vehicleDetectionBean.getData().getType3());
        adapter3.setOnItemClickListener(new OnItemClickListener2() {
            @Override
            public void onItemClick(int position,String edte, boolean isCheck) {
                type = 3;
//                completeBosBean.setReslut(edte);
//                completeBosBean.setChechDataId(lisBean1.get(position).getChechDataId());
//                if(!isCheck){
//                    completeBosBean.setState(0);
//                    lisBean3.set(position,completeBosBean);
//                }else {
//                    completeBosBean.setState(1);
//                    lisBean3.set(position,completeBosBean);
//                }
//                verAddBean.setCompleteBos(lisBean3);
                for (int i = 0; i < lisBean3.size(); i++) {
                    VerAddBean.CompleteBosBean bosBean = lisBean3.get(position);
                    if(position == i){
                        if(isCheck){
                            bosBean.setState(1);
                            if(position == lisBean3.size() -1){
                                bosBean.setReslut(edte);
                            }
//                            lisBean1.set(position, VehicleDetectionActivity.this.completeBosBean);
                        }else {
                            bosBean.setState(0);
//                            lisBean1.(position, VehicleDetectionActivity.this.completeBosBean);
                        }
                        break;
                    }
                }
                verAddBean.setCompleteBos(lisBean3);
            }
        });
    }

    @Override
    public void mSuc() {
        ToastUtils.popUpToast("提交成功");
        finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_srue:
                if (verAddBean==null||verAddBean.getCompleteBos().size() == 0) {
                    ToastUtils.popUpToast("提交检测结果为空");
                } else {
                    verAddBean.setDriverId(Integer.parseInt(PrefUtilsData.getUserId()));
                    verAddBean.setType(type);

                    if (imageList1.size() > 1) {
                        imageList1.remove(imageList1.size()-1);
                        mPresenter.upload(imageList1, UPLOAD_VEHICLE1);
                    }

                    if (imageList2.size() > 1) {
                        imageList2.remove(imageList2.size()-1);
                        mPresenter.upload(imageList2, UPLOAD_VEHICLE2);
                    }

                    if (imageList3.size() > 1) {
                        imageList3.remove(imageList3.size()-1);
                        mPresenter.upload(imageList3, UPLOAD_VEHICLE3);
                    }


                }

                break;
            case R.id.text1:
                recycler1.setVisibility(View.VISIBLE);
                recycler2.setVisibility(View.GONE);
                recycler3.setVisibility(View.GONE);
                mRvImages1.setVisibility(View.VISIBLE);
                mRvImages2.setVisibility(View.GONE);
                mRvImages3.setVisibility(View.GONE);
                break;
            case R.id.text2:
                recycler2.setVisibility(View.VISIBLE);
                recycler1.setVisibility(View.GONE);
                recycler3.setVisibility(View.GONE);
                mRvImages2.setVisibility(View.VISIBLE);
                mRvImages1.setVisibility(View.GONE);
                mRvImages3.setVisibility(View.GONE);
                break;
            case R.id.text3:
                recycler3.setVisibility(View.VISIBLE);
                recycler1.setVisibility(View.GONE);
                recycler2.setVisibility(View.GONE);
                mRvImages3.setVisibility(View.VISIBLE);
                mRvImages1.setVisibility(View.GONE);
                mRvImages2.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void imageUrl(ImageUploadBean imageUploadBean,int type) {
        switch (type){
            case UPLOAD_VEHICLE1:
                url1 = imageUploadBean.getData();
                 verAddBean.setPicUrl(url1);

                String json = new Gson().toJson(verAddBean);
                mPresenter.addVehicleData(json);
                break;
            case  UPLOAD_VEHICLE2:
                url2 = imageUploadBean.getData();
               verAddBean.setPicUrl(url2);
                mPresenter.addVehicleData(new Gson().toJson(verAddBean));
                break;
            case  UPLOAD_VEHICLE3:
                url3 = imageUploadBean.getData();
                verAddBean.setPicUrl(url3);
                mPresenter.addVehicleData(new Gson().toJson(verAddBean));
                break;
        }

    }
}