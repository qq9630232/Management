package com.example.freightmanagement.Activity;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.freightmanagement.Base.BaseActivity;
import com.example.freightmanagement.Bean.CarInfoBean;
import com.example.freightmanagement.R;
import com.example.freightmanagement.presenter.CarInformationPresenter;

public class CarInformationActivity extends BaseActivity<CarInformationPresenter> implements CarInformationPresenter.View {

    private ImageView mIvCardFront;
    private ImageView mIvCardRevers, mIvCardYunShu, mIvCardJiDong;
    private TextView mEtCarNum, mEtDengJiCarEngine, mEtDengJiRanLiao, mEtDengJiPaiLiang, mEtDengJiBuildName, et_deng_ji_gonglv;
    private TextView mEtXingZhi, mEtJingYingNum, mEtYunYingFanWei, mTvSendDate, mEtOwner, mEtDengJiNum, mEtDengJiCarModel;
    private TextView mEtBrandXingHao, mEtYunYingNum, mEtYeHuName, mEtCheliangNum, mEtJiGuan, mEtDengJiCarType, mEtDengJiCarCode;
    private TextView mTvDateZhuce, mTvDateSend, mEtDangAn, mEtZongZhi, mEtHeDing, et_car_type, mTvDengJiDate, mEtDengJiCarBrand, mEtDengJiTires,
            et_deng_ji_chang, et_deng_ji_kuan, et_deng_ji_gao, mEtDengJiQianYin, mEtDengJiJiaShiShi, mEtDengJiUseXingZhi, mEtDengJiSendDate;
    private EditText mEtDengJiGongLv;
    private TextView mTvCarOwner;

    @Override
    public int setLayoutResource() {
        return R.layout.activity_car_information;
    }

    @Override
    protected void onInitView() {
        setDefaultTitle("车辆详情");
        mIvCardFront = findViewById(R.id.iv_card_front);//行车证正面
        mIvCardRevers = findViewById(R.id.iv_card_revers);//行车证正面
        mEtCarNum = findViewById(R.id.et_car_num);//号牌号码
        mEtXingZhi = findViewById(R.id.et_xing_zhi);
        et_car_type = findViewById(R.id.et_car_type);
        mEtBrandXingHao = findViewById(R.id.et_brand_xing_hao);
        mTvDateZhuce = findViewById(R.id.tv_date_zhuce);
        mTvDateSend = findViewById(R.id.tv_date_send);
        mEtDangAn = findViewById(R.id.et_dang_an);
        mEtZongZhi = findViewById(R.id.et_zong_zhi);
        mEtHeDing = findViewById(R.id.et_he_ding);
        mIvCardYunShu = findViewById(R.id.iv_card_yun_shu);
        mEtYunYingNum = findViewById(R.id.et_yun_ying_num);
        mEtYeHuName = findViewById(R.id.et_ye_hu_name);
        mEtJingYingNum = findViewById(R.id.et_jing_ying_num);
        mEtYunYingFanWei = findViewById(R.id.et_yun_ying_fan_wei);
        et_deng_ji_gonglv = findViewById(R.id.et_deng_ji_gonglv);
        mIvCardJiDong = findViewById(R.id.iv_card_ji_dong);
        et_deng_ji_chang = findViewById(R.id.et_deng_ji_chang);
        et_deng_ji_kuan = findViewById(R.id.et_deng_ji_kuan);
        et_deng_ji_gao = findViewById(R.id.et_deng_ji_gao);
        mEtOwner = findViewById(R.id.et_owner);
        mEtJiGuan = findViewById(R.id.et_ji_guan);
        mTvDengJiDate = findViewById(R.id.tv_deng_ji_date);
        mEtDengJiNum = findViewById(R.id.et_deng_ji_num);
        mEtDengJiCarType = findViewById(R.id.et_deng_ji_car_type);
        mEtDengJiCarBrand = findViewById(R.id.et_deng_ji_car_brand);
        mEtDengJiCarModel = findViewById(R.id.et_deng_ji_car_model);
        mEtDengJiCarCode = findViewById(R.id.et_deng_ji_car_code);
        mEtDengJiCarEngine = findViewById(R.id.et_deng_ji_car_engine);
        mEtDengJiRanLiao = findViewById(R.id.et_deng_ji_ran_liao);
        mEtDengJiPaiLiang = findViewById(R.id.et_deng_ji_pai_liang);
        mEtDengJiGongLv = findViewById(R.id.et_deng_ji_gong_lv);
        mEtDengJiBuildName = findViewById(R.id.et_deng_ji_build_name);
        mEtDengJiTires = findViewById(R.id.et_deng_ji_tires);
        mEtDengJiQianYin = findViewById(R.id.et_deng_ji_qian_yin);
        mEtDengJiJiaShiShi = findViewById(R.id.et_deng_ji_jia_shi_shi);
        mEtDengJiUseXingZhi = findViewById(R.id.et_deng_ji_use_xing_zhi);
        mEtDengJiSendDate = findViewById(R.id.et_deng_ji_send_date);
        mEtCheliangNum = findViewById(R.id.et_cheliang_num);
        mTvSendDate = findViewById(R.id.tv_send_date);
        mTvCarOwner = findViewById(R.id.tv_car_owner);
    }

    @Override
    protected void onLoadData2Remote() {
        mPresenter.getTestList(getIntent().getIntExtra("carId", 0));
    }

    @Override
    protected CarInformationPresenter onInitLogicImpl() {
        return new CarInformationPresenter();
    }


    @Override
    public void testResult(CarInfoBean carInfoBean) {
        CarInfoBean.DataBean.CertificateDrivingBoBean certificateDrivingBo = carInfoBean.getData().getCertificateDrivingBo();
        CarInfoBean.DataBean.CertificateRegistrationBoBean certificateRegistrationBo = carInfoBean.getData().getCertificateRegistrationBo();
        CarInfoBean.DataBean.CertificateTransportBoBean certificateTransportBo = carInfoBean.getData().getCertificateTransportBo();


        Glide.with(getContext()).load(certificateDrivingBo.getPicUrl()).into(mIvCardFront);
        Glide.with(getContext()).load(certificateDrivingBo.getPicUrl1()).into(mIvCardRevers);
        mTvCarOwner.setText(certificateDrivingBo.getOwner());
        mEtCarNum.setText(certificateDrivingBo.getPlateNo() + "");
        mEtXingZhi.setText(certificateDrivingBo.getUseCharacter() + "");
        mEtBrandXingHao.setText(certificateDrivingBo.getModel() + "");
        mTvDateZhuce.setText(certificateDrivingBo.getRegistrationDate() + "");
        mTvDateSend.setText(certificateDrivingBo.getIssueDate() + "");
        mEtDangAn.setText(certificateDrivingBo.getFileNo() + "");
        mEtZongZhi.setText(certificateDrivingBo.getAllWeight() + "");
        mEtHeDing.setText(certificateDrivingBo.getApprovedWeight() + "");
        et_car_type.setText(certificateDrivingBo.getWchicheType() + "");

        Glide.with(getContext()).load(certificateTransportBo.getPicUrl()).into(mIvCardYunShu);
        mEtYunYingNum.setText(certificateTransportBo.getPlateNo() + "");
        mEtYeHuName.setText(certificateTransportBo.getName() + "");
        mEtCheliangNum.setText(certificateTransportBo.getPlateNo() + "");
        mEtJingYingNum.setText(certificateTransportBo.getLicence() + "");
        mEtYunYingFanWei.setText(certificateTransportBo.getScope() + "");
        mTvSendDate.setText(certificateTransportBo.getGrantDate() + "");

        Glide.with(getContext()).load(certificateRegistrationBo.getPicUrl()).into(mIvCardJiDong);
        mEtOwner.setText(certificateRegistrationBo.getOwner() + "");
        mEtJiGuan.setText(certificateRegistrationBo.getDepartments());
        mTvDengJiDate.setText(certificateRegistrationBo.getRegistrationDate());
        mEtDengJiNum.setText(certificateRegistrationBo.getGrantNo() + "");
        mEtDengJiCarType.setText(certificateRegistrationBo.getCarType() + "");
        mEtDengJiCarBrand.setText(certificateRegistrationBo.getCarBrand() + "");
        mEtDengJiCarModel.setText(certificateRegistrationBo.getCarModel() + "");
        mEtDengJiCarCode.setText(certificateRegistrationBo.getCarNo() + "");
        mEtDengJiCarEngine.setText(certificateRegistrationBo.getEngineNo() + "");
        mEtDengJiRanLiao.setText(certificateRegistrationBo.getFuelType() + "");
        mEtDengJiPaiLiang.setText(certificateRegistrationBo.getDisplacement() + "ml" + certificateRegistrationBo.getPower() + "kw");
        et_deng_ji_gonglv.setText(certificateRegistrationBo.getPower() + "");
        String outline = certificateRegistrationBo.getOutline();
        String[] split = outline.split(",");
        mEtDengJiBuildName.setText(certificateRegistrationBo.getMaker() + "");
        mEtDengJiTires.setText(certificateRegistrationBo.getTireCount() + "");
        et_deng_ji_chang.setText(split[0]);
        et_deng_ji_kuan.setText(split[1]);
        et_deng_ji_gao.setText(split[2]);
        mEtDengJiQianYin.setText(certificateRegistrationBo.getTractionWeight() + "");
        mEtDengJiJiaShiShi.setText(certificateRegistrationBo.getPassengersCount() + "");
        mEtDengJiUseXingZhi.setText(certificateRegistrationBo.getUseNature() + "");
        mEtDengJiSendDate.setText(certificateRegistrationBo.getIssueDate() + "");

    }

}
