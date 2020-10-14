package com.example.freightmanagement.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.freightmanagement.Base.BaseActivity;
import com.example.freightmanagement.Base.BaseApiConstants;
import com.example.freightmanagement.Bean.ContractBean;
import com.example.freightmanagement.Bean.WrodIdBean;
import com.example.freightmanagement.R;
import com.example.freightmanagement.Utils.Network.OnRequestResultForCommon;
import com.example.freightmanagement.Utils.Network.RestApi;
import com.example.freightmanagement.Utils.StringUtil;
import com.example.freightmanagement.Utils.ToastUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DrivierxiangqingActivity extends BaseActivity implements  View.OnClickListener {

    private TextView name, tv_card,et_six,tv_current_address,
            et_permit_type, tv_start_date, et_end_date,
            et_post_card, tv_first_receive, tv_you_xiao_qi,et_category;
    private ImageView iv_card_front1, iv_card_front2, iv_business_front, iv_driver_front,
            iv_work_front,mIvDriverReverse;
    private TextView mTvSrue;
    private AlertDialog.Builder builder;
    private int contractId;
    private int driverId;


    @Override
    public int setLayoutResource() {
        return R.layout.activity_driver_information;
    }

    @Override
    protected void onInitView() {
        setDefaultTitle("驾驶员信息");

        name = (TextView) bindView(R.id.tv_real_name);
        tv_card = (TextView) bindView(R.id.et_card_num);
        et_six = (TextView) bindView(R.id.et_six);
        tv_current_address = (TextView) bindView(R.id.tv_current_address);
        iv_card_front1 = (ImageView) bindView(R.id.iv_card_front);
        iv_card_front2 = (ImageView) bindView(R.id.iv_card_revers);
        iv_business_front = (ImageView) bindView(R.id.iv_business_front);
        iv_driver_front = (ImageView) bindView(R.id.iv_driver_front);
        et_permit_type = (TextView) bindView(R.id.et_permit_type);
        tv_start_date = (TextView) bindView(R.id.tv_start_date);
        et_end_date = (TextView) bindView(R.id.et_end_date);
        iv_work_front = (ImageView) bindView(R.id.iv_work_front);
        et_post_card = (TextView) bindView(R.id.et_post_card);
        tv_first_receive = (TextView) bindView(R.id.tv_first_receive);
        tv_you_xiao_qi = (TextView) bindView(R.id.tv_you_xiao_qi);
        mIvDriverReverse = (ImageView) bindView(R.id.iv_driver_reverse);
        et_category = (TextView) bindView(R.id.et_category);
        mTvSrue = (TextView) bindView(R.id.tv_srue);
        mTvSrue.setVisibility(View.VISIBLE);
        mTvSrue.setOnClickListener(this);
    }

    @Override
    protected void onLoadData2Remote() {
        driverId = getIntent().getIntExtra("Id", 0);
        RestApi.getInstance().get(BaseApiConstants.API_JIASHIYUAN + driverId, new OnRequestResultForCommon() {
            @Override
            public void onSuccess(String msg) {
                WrodIdBean wrodIdBean = new Gson().fromJson(msg, WrodIdBean.class);
                WrodIdBean.DataBean.CertificateDriverBoBean certificateDriverBo = wrodIdBean.getData().getCertificateDriverBo();
                WrodIdBean.DataBean.CertificateIDBoBean certificateIDBo = wrodIdBean.getData().getCertificateIDBo();
                WrodIdBean.DataBean.CertificateWorkBoBean certificateWorkBo = wrodIdBean.getData().getCertificateWorkBo();
                name.setText(certificateIDBo.getName());
                tv_card.setText(certificateIDBo.getIdno());
                et_six.setText(certificateIDBo.getSix());
                tv_current_address.setText(certificateIDBo.getAddress());
                et_permit_type.setText(certificateDriverBo.getClasss() + "");
                tv_start_date.setText(certificateDriverBo.getStartTime());
                et_end_date.setText(certificateDriverBo.getTerm());
                if (!TextUtils.isEmpty(certificateIDBo.getPicUrl())) {
                    Glide.with(getContext()).load(certificateIDBo.getPicUrl()).into(iv_card_front1);
                }
                if (!TextUtils.isEmpty(certificateIDBo.getPicUrl2())) {
                    Glide.with(getContext()).load(certificateIDBo.getPicUrl2()).into(iv_card_front2);
                }
                Glide.with(getContext()).load(certificateDriverBo.getPicUrl()).into(iv_driver_front);
                Glide.with(getContext()).load(certificateWorkBo.getPicUrl()).into(iv_work_front);
                Glide.with(getContext()).load(certificateDriverBo.getPicUrl2()).into(mIvDriverReverse);
                et_category.setText(certificateWorkBo.getCategory());
                et_post_card.setText(certificateWorkBo.getGrantNo());
                tv_first_receive.setText(StringUtil.isNotEmpty(certificateWorkBo.getFirstTime())?certificateWorkBo.getFirstTime():"");
                tv_you_xiao_qi.setText(StringUtil.isNotEmpty(certificateWorkBo.getValidityEndTime())?certificateWorkBo.getValidityEndTime():"");

            }
            @Override
            public void onFail() {
                super.onFail();
            }

            @Override
            public void netUnlink() {
                super.netUnlink();
            }
        });
    }


    private String timeStampToDate(long tsp, String... format) {
        SimpleDateFormat sdf;
        if (format.length < 1) {
            sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        } else {
            sdf = new SimpleDateFormat(format[0], Locale.getDefault());
        }
        return sdf.format(tsp);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_srue:
                showTwo();
                //finish();
                break;
        }
    }


    /**
     * 两个按钮的 dialog
     *
     * @param
     */
    private void showTwo() {
        builder = new AlertDialog.Builder(this).setIcon(R.mipmap.icon_oreder_remark_delete).setTitle("解除合同")
                .setMessage("是否解除此合同？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //ToDo: 你想做的事情
//                        Toast.makeText(CarListManageActivity.this, "确定按钮", Toast.LENGTH_LONG).show();
//                        CarExecuteParam carExecuteParam = new CarExecuteParam();
//                        carExecuteParam.setCarId();

                        String json = new Gson().toJson(driverId);
                        RestApi.getInstance().get(BaseApiConstants.API_CONTRACT_GET_BY_DRIVER_ID + driverId, new OnRequestResultForCommon() {
                                    @Override
                                    public void onSuccess(String json) {
                                        //ToastUtils.popUpToast("解约成功");
                                        ContractBean contractBean = new Gson().fromJson(json,ContractBean.class);
                                        contractId =  contractBean.getData().getId();
                                        RestApi.getInstance().get(BaseApiConstants.API_RESLOVE_CONTRACT + contractId, new OnRequestResultForCommon() {
                                            @Override
                                            public void onSuccess(String json) {
                                                //ToastUtils.popUpToast("解约成功");
                                                super.onSuccess(json);

                                            }
                                            @Override
                                            public void onFail() {
                                                super.onFail();
                                            }

                                            @Override
                                            public void netUnlink() {
                                                super.netUnlink();
                                            }
                                        });

                                        editConstract(0);
                                    }
                                    @Override
                                    public void onFail() {
                                        super.onFail();
                                    }

                                    @Override
                                    public void netUnlink() {
                                        super.netUnlink();
                                    }
                        });

                       // editConstract(contractId);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //ToDo: 你想做的事情
//                        Toast.makeText(CarListManageActivity.this, "关闭按钮", Toast.LENGTH_LONG).show();
                        dialogInterface.dismiss();
                    }
                });
        builder.create().show();
    }



    public void editConstract(int contractId) {
        ToastUtils.popUpToast("提交成功");

        EventBus.getDefault().post("resloveConstractOk");
        finish();

    }
}