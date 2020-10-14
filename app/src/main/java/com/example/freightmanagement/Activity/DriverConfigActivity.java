package com.example.freightmanagement.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.IDCardParams;
import com.baidu.ocr.sdk.model.IDCardResult;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.baidu.ocr.ui.camera.CameraNativeHelper;
import com.baidu.ocr.ui.camera.CameraView;
import com.bumptech.glide.Glide;
import com.example.freightmanagement.Base.BaseActivity;
import com.example.freightmanagement.Base.BaseResponse;
import com.example.freightmanagement.Bean.DriverLicenseBean;
import com.example.freightmanagement.Bean.WorkBean;
import com.example.freightmanagement.Bean.WrodIdBean;
import com.example.freightmanagement.R;
import com.example.freightmanagement.Utils.DateUtil;
import com.example.freightmanagement.Utils.DialogUtils;
import com.example.freightmanagement.Utils.FileUtil;
import com.example.freightmanagement.Utils.IDCardUtils;
import com.example.freightmanagement.Utils.PrefUtilsData;
import com.example.freightmanagement.Utils.StringUtil;
import com.example.freightmanagement.Utils.StringUtils;
import com.example.freightmanagement.Utils.ToastUtils;
import com.example.freightmanagement.View.ElectronicSignature;
import com.example.freightmanagement.model.CertificateDriverParam;
import com.example.freightmanagement.model.CertificateWorkParam;
import com.example.freightmanagement.model.DriverInfoSubmitParam;
import com.example.freightmanagement.model.IDCardInfoFrontBean;
import com.example.freightmanagement.model.IDCardParam;
import com.example.freightmanagement.presenter.DriverConfigPresenter;
import com.example.freightmanagement.presenter.constract.DriverConfigConstact;
import com.google.gson.Gson;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.util.ConvertUtils;
import cn.qqtheme.framework.widget.WheelView;

import static com.example.freightmanagement.Base.BaseApiConstants.IMAGE_BASE_URL;
import static com.example.freightmanagement.common.ImageUploadConstants.UPLOAD_DRIVER;
import static com.example.freightmanagement.common.ImageUploadConstants.UPLOAD_DRIVER_REVERSE;
import static com.example.freightmanagement.common.ImageUploadConstants.UPLOAD_ID_CARD_BACK;
import static com.example.freightmanagement.common.ImageUploadConstants.UPLOAD_ID_CARD_FRONT;
import static com.example.freightmanagement.common.ImageUploadConstants.UPLOAD_SIGN;
import static com.example.freightmanagement.common.ImageUploadConstants.UPLOAD_WORK;

/**
 * Created by songdechuan on 2020/8/6.
 */

public class DriverConfigActivity extends BaseActivity<DriverConfigPresenter> implements DriverConfigConstact.View, View.OnClickListener {
    private static final int REQUEST_CODE_PICK_IMAGE_FRONT = 201;
    private static final int REQUEST_CODE_PICK_IMAGE_BACK = 202;
    private static final int REQUEST_CODE_CAMERA = 102;
    private static final int REQUEST_CODE_DRIVING_LICENSE = 121;
    private static final int REQUEST_CODE_WORK_LICENSE = 122;
    private static final int REQUEST_CODE_DRIVING_REVERSE_LICENSE = 123;

    private static final int DATE_TYPE_START_DATE = 300;
    private static final int DATE_TYPE_END_DATE = 301;
    private static final int DATE_TYPE_FIRST_RECEIVE_DATE = 302;
    private static final int DATE_TYPE_EFFECTIVE_DATE = 303;
    private static final int DATE_TYPE_VALID_DATE = 304;
    private static final String TEMPLATE_WORK_SIGN = "be14fc9fd2d9a508fc95a3f8fbbe37d9";

    /**
     * 上传身份证正面照片
     */
    private TextView mTvCard1;
    private ImageView mIvCardFront;
    private ImageView mCloseImageViewFont;
    private RelativeLayout mRePic;
    /**
     * 上传身份证反面照片
     */
    private TextView mTvCard2;
    private ImageView mIvCardRevers;
    private ImageView mCloseImageViewReverse;
    private RelativeLayout mRePicReverse;
    /**
     * 请填写您的真实姓名
     */
    private EditText mEtRealName;
    /**
     * 请填写您的性别
     */
    private EditText mEtDetailAddress;
    /**
     * 请填写您的年龄
     */
    private EditText mTvCurrentAddress;
    private LinearLayout mLlCurrentAddress;
    /**
     * 请填写您的身份证号
     */
    private EditText mEtCardNum;
    /**
     * 请填写上岗证号码
     */
    private EditText mEtPostCard;
    private EditText meTCategory;
    private ImageView mImgSign;
    private LinearLayout mLinSign;
    /**
     * 有效期
     */
//    private TextView mTvMobile;
    /**
     * 2020/08/4
     */
    private TextView mTvSignDate;
    /**
     * 60分
     */
//    private TextView mTvSignFen;
    /**
     * KE8581
     */
//    private TextView mTvSignCph;
    /**
     * Jeep
     */
//    private TextView mTvSignPp;
    /**
     * 45sa
     */
//    private TextView mTvSignXh;
    private RelativeLayout mActivityNewDoctorSignProtocol;
    private final String FRONT = "front";
    private final String BACK = "back";
    private TextView mTvSure;
    private RelativeLayout mRlSign;
    private TextView mTvSign;
    private ImageView mIvSign;
    private Dialog bottomDialog;
    private View bottomView;
    private ElectronicSignature vSignView;
    /**
     * 上传驾驶证正页照片
     */
    private TextView mTvDriver;
    private ImageView mIvDriverFront;
    private RelativeLayout mReDriverPic;
    /**
     * 上传驾驶证副页照片
     */
    private ImageView mIvDriverReverse;
    private RelativeLayout mReDriverReverse;
    /**
     * 请选择您的准驾类型
     */
    private TextView mEtPermitType;
    /**
     * 开始日期
     */
    private TextView mTvStartDate;
    /**
     * 结束日期
     */
    private TextView mEtEndDate;


    /**
     * 选择日期
     */
    private TextView mTvFirstReceive;
    /**
     * 选择日期
     */

    private String idCardFrontUrl = "";
    private String idCardBackUrl = "";
    private String driverUrl = "";
    private String signUrl = "";
    private String permitType;
    private String startTime;
    private String endTime;
    private String firstReceiveTime;
    private String frontPath;
    private String backPath;
    private String driverPath;
    private String driverNum;
    private TextView mTvWork;
    private ImageView mIvWorkFront;
    private RelativeLayout mReWorkPic;
    private String workPath;
    private TextView mTvYouXiaoQi;
    private String workUrl;
    private String youxiaoqiTime;
    private String driverReversePath;
    private String driverReverseUrl;

    @Override
    public int setLayoutResource() {
        return R.layout.activity_driver_config;

    }

    @Override
    protected void onInitView() {
        initView();
        String flag = getIntent().getStringExtra("flag");//0是提交  1是修改
        if (flag.equals("0")) {
            setDefaultTitle("驾驶员注册");
            mRlSign.setVisibility(View.VISIBLE);
        } else {
            setDefaultTitle("驾驶员信息修改");
            mRlSign.setVisibility(View.VISIBLE);
            mPresenter.getPeixunData();
        }

        checkGalleryPermission();
        initView();
    }

    @Override
    protected void onLoadData2Remote() {
        //  初始化本地质量控制模型,释放代码在onDestory中
        //  调用身份证扫描必须加上 intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL, true); 关闭自动初始化和释放本地模型
        CameraNativeHelper.init(this, OCR.getInstance(this).getLicense(),
                new CameraNativeHelper.CameraNativeInitCallback() {
                    @Override
                    public void onError(int errorCode, Throwable e) {
                        String msg;
                        switch (errorCode) {
                            case CameraView.NATIVE_SOLOAD_FAIL:
                                msg = "加载so失败，请确保apk中存在ui部分的so";
                                break;
                            case CameraView.NATIVE_AUTH_FAIL:
                                msg = "授权本地质量控制token获取失败";
                                break;
                            case CameraView.NATIVE_INIT_FAIL:
                                msg = "本地质量控制";
                                break;
                            default:
                                msg = String.valueOf(errorCode);
                        }
//                        infoTextView.setText("本地质量控制初始化错误，错误原因： " + msg);
                    }
                });
    }

    private boolean checkGalleryPermission() {
        int ret = ActivityCompat.checkSelfPermission(this, Manifest.permission
                .READ_EXTERNAL_STORAGE);
        int wet = ActivityCompat.checkSelfPermission(this, Manifest.permission
                .WRITE_EXTERNAL_STORAGE);
        int camera = ActivityCompat.checkSelfPermission(this, Manifest.permission
                .CAMERA);
        if (ret != PackageManager.PERMISSION_GRANTED && wet != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1000);
            return false;
        }
        return true;
    }

    public void initView() {
        mTvCard1 = (TextView) findViewById(R.id.tv_card1);
        mIvCardFront = (ImageView) findViewById(R.id.iv_card_front);
        mCloseImageViewFont = (ImageView) findViewById(R.id.closeImageViewFont);
        mRePic = (RelativeLayout) findViewById(R.id.re_pic);
        mRePic.setOnClickListener(this);
        mTvCard2 = (TextView) findViewById(R.id.tv_card2);
        mIvCardRevers = (ImageView) findViewById(R.id.iv_card_revers);
        mCloseImageViewReverse = (ImageView) findViewById(R.id.closeImageViewReverse);
        mRePicReverse = (RelativeLayout) findViewById(R.id.re_pic_reverse);
        mRePicReverse.setOnClickListener(this);
        mEtRealName = (EditText) findViewById(R.id.et_real_name);
        mEtDetailAddress = (EditText) findViewById(R.id.et_detail_address);
        mTvCurrentAddress = (EditText) findViewById(R.id.tv_current_address);
        mLlCurrentAddress = (LinearLayout) findViewById(R.id.ll_current_address);
        mEtCardNum = (EditText) findViewById(R.id.et_card_num);
        mEtPostCard = (EditText) findViewById(R.id.et_post_card);
        mImgSign = (ImageView) findViewById(R.id.img_sign);
        mLinSign = (LinearLayout) findViewById(R.id.lin_sign);
        mTvSure = (TextView) findViewById(R.id.tv_srue);
        mTvSure.setOnClickListener(this);
        mTvSign = (TextView) findViewById(R.id.tv_sign);
        mIvSign = (ImageView) findViewById(R.id.iv_sign);
        mRlSign = (RelativeLayout) findViewById(R.id.rl_sign);
        mRlSign.setOnClickListener(this);
        mTvSign.setOnClickListener(this);
        mActivityNewDoctorSignProtocol = (RelativeLayout) findViewById(R.id.activity_new_doctor_sign_protocol);
        bottomView = LayoutInflater.from(this).inflate(R.layout.inflate_pop_item, null);
        bottomView.findViewById(R.id.btn_no).setOnClickListener(this);
        bottomView.findViewById(R.id.btn_yes).setOnClickListener(this);
        vSignView = (ElectronicSignature) bottomView.findViewById(R.id.sign_view);
        mTvDriver = (TextView) findViewById(R.id.tv_driver);
        mIvDriverFront = (ImageView) findViewById(R.id.iv_driver_front);
        mReDriverPic = (RelativeLayout) findViewById(R.id.re_driver_pic);
        mReDriverPic.setOnClickListener(this);
        mIvDriverReverse = (ImageView) findViewById(R.id.iv_driver_reverse);
        mReDriverReverse = (RelativeLayout) findViewById(R.id.re_driver_reverse);
        mReDriverReverse.setOnClickListener(this);
        mEtPermitType = (TextView) findViewById(R.id.et_permit_type);
        mTvStartDate = (TextView) findViewById(R.id.tv_start_date);
        mEtEndDate = (TextView) findViewById(R.id.et_end_date);
        mTvYouXiaoQi = findViewById(R.id.tv_you_xiao_qi);
        mTvYouXiaoQi.setOnClickListener(this);
        mTvFirstReceive = (TextView) findViewById(R.id.tv_first_receive);
        mTvWork = findViewById(R.id.tv_work);
        mIvWorkFront = findViewById(R.id.iv_work_front);
        mReWorkPic = findViewById(R.id.re_work_pic);
        mReWorkPic.setOnClickListener(this);
        meTCategory = findViewById(R.id.et_category);
        mTvFirstReceive.setOnClickListener(this);

        mEtPermitType.setOnClickListener(this);
        mTvStartDate.setOnClickListener(this);
        mEtEndDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.re_pic:
                takeIDCard();
                break;
            case R.id.re_pic_reverse:
                takeIDCardReverse();
                break;
            case R.id.rl_sign:
                bottomDialog = DialogUtils.showBottomWindowDialog(this, bottomDialog, bottomView);
                break;
            case R.id.tv_sign:
                bottomDialog = DialogUtils.showBottomWindowDialog(this, bottomDialog, bottomView);
                break;
            case R.id.btn_no:
                vSignView.clearCanvas();
                break;
            case R.id.btn_yes:
                if (bottomDialog != null) {
                    bottomDialog.dismiss();
                }
                Bitmap bitmap = vSignView.save();
                mTvSign.setVisibility(View.GONE);
                mIvSign.setImageBitmap(bitmap);
                File file = FileUtil.saveImage(bitmap);
                if (file == null) {
                    return;
                }
                mPresenter.upload(new File(file.getAbsolutePath()), UPLOAD_SIGN);
                break;
            case R.id.tv_you_xiao_qi:
                onYearMonthDayPicker(DATE_TYPE_VALID_DATE);
                break;
            case R.id.re_driver_pic:
                takeDriverPhoto();
                break;
            case R.id.re_driver_reverse:
                takeDriverReversePhoto();
                break;
            case R.id.tv_first_receive:
                onYearMonthDayPicker(DATE_TYPE_FIRST_RECEIVE_DATE);
                break;
//            case R.id.tv_effective_date:
//                onYearMonthDayPicker(DATE_TYPE_EFFECTIVE_DATE);
//                break;
            case R.id.et_permit_type:
                onOptionPicker();
                break;
            case R.id.tv_start_date:
                onYearMonthDayPicker(DATE_TYPE_START_DATE);
                break;
//            case R.id.et_end_date:
//                onYearMonthDayPicker(DATE_TYPE_END_DATE);
//                break;
            case R.id.re_work_pic:
                workPath = "work_" + System.currentTimeMillis();
                takeWorkPhoto(workPath);
                break;
            case R.id.tv_srue:
                if (StringUtils.isEmpty(idCardFrontUrl)) {
                    ToastUtils.popUpToast("请选择身份证正面照片");
                    return;
                }
                if (StringUtils.isEmpty(idCardBackUrl)) {
                    ToastUtils.popUpToast("请选择身份证反面照片");
                    return;
                }
                String userName = mEtRealName.getText().toString();
                if (StringUtils.isEmpty(userName)) {
                    ToastUtils.popUpToast("姓名不得为空");
                    return;
                }
                String idCardNum = mEtCardNum.getText().toString();
                if (StringUtils.isEmpty(idCardNum)) {
                    ToastUtils.popUpToast("身份证号不得为空");
                    return;
                }
                String postCardNum = mEtPostCard.getText().toString();
                if (StringUtils.isEmpty(postCardNum)) {
                    ToastUtils.popUpToast("上岗证号不得为空");
                    return;
                }
                if (StringUtils.isEmpty(mEtPermitType.getText().toString())) {
                    ToastUtils.popUpToast("准驾车型不得为空");
                    return;
                }
                if (StringUtils.isEmpty(mTvStartDate.getText().toString())) {
                    ToastUtils.popUpToast("开始日期不得为空");
                    return;
                }
                if (StringUtils.isEmpty(mEtEndDate.getText().toString())) {
                    ToastUtils.popUpToast("结束日期不得为空");
                    return;
                }
                if (StringUtils.isEmpty(mTvFirstReceive.getText().toString())) {
                    ToastUtils.popUpToast("初次领证日期不得为空");
                    return;
                }
                DriverInfoSubmitParam driverInfoSubmitParam = new DriverInfoSubmitParam();
                driverInfoSubmitParam.setName(userName);
                driverInfoSubmitParam.setTel(PrefUtilsData.getMobile());
                IDCardParam idCardParam = new IDCardParam();
                idCardParam.setIDNo(idCardNum);
                idCardParam.setName(userName);
                idCardParam.setSix(mEtDetailAddress.getText().toString());
                idCardParam.setAddress(mTvCurrentAddress.getText().toString());
                idCardParam.setBirthDay(idCardNum.substring(6,10)+"-"+idCardNum.substring(10,12)+"-"+idCardNum.substring(12,14));
                idCardParam.setPicUrl(idCardFrontUrl);
                idCardParam.setPicUrl2(idCardBackUrl);
                driverInfoSubmitParam.setCertificateIDBo(idCardParam);

                CertificateDriverParam certificateDriverParam = new CertificateDriverParam();
                certificateDriverParam.setClasss(permitType);
                startTime = mTvStartDate.getText().toString();
                boolean isStartTime = DateUtil.isValidDate(startTime);

                if (!isStartTime) {
                    ToastUtils.popUpToast("驾驶证有效起始日期错误，请重新选择");
                    return;
                }
                certificateDriverParam.setStartTime(startTime);
                certificateDriverParam.setTerm (mEtEndDate.getText().toString());
                certificateDriverParam.setPicUrl(driverUrl);
                certificateDriverParam.setPicUrl2(driverReverseUrl);
                certificateDriverParam.setFileNumber(String.valueOf(driverNum));
                driverInfoSubmitParam.setCertificateDriverBo(certificateDriverParam);

                CertificateWorkParam certificateWorkParam = new CertificateWorkParam();
                certificateWorkParam.setGrantNo(mEtPostCard.getText().toString());
                certificateWorkParam.setFileNumber(postCardNum);

                String firstTimeStr = mTvFirstReceive.getText().toString();
                String yxqTimeStr = mTvYouXiaoQi.getText().toString();
                if (!DateUtil.isValidDate(firstTimeStr)) {
                    ToastUtils.popUpToast("上岗证初次领证时间错误，请重新选择");
                    return;
                }
                if (!DateUtil.isValidDate(yxqTimeStr)) {
                    ToastUtils.popUpToast("上岗证有效期限时间错误，请重新选择");
                    return;
                }
                certificateWorkParam.setFirstTime(firstTimeStr);
                certificateWorkParam.setValidityEndTime(yxqTimeStr);

                certificateWorkParam.setPicUrl(workUrl);
                certificateWorkParam.setCategory(meTCategory.getText().toString());
                driverInfoSubmitParam.setCertificateWorkBo(certificateWorkParam);
                driverInfoSubmitParam.setSignUrl(signUrl);

                if (getIntent().getStringExtra("flag").equals("0")) {
                    mPresenter.submit(driverInfoSubmitParam);
                } else {
                    driverInfoSubmitParam.setId(PrefUtilsData.getUserId());
                    mPresenter.updata2(driverInfoSubmitParam);
                }

                finish();
                break;
        }
    }

    private void takeWorkPhoto(String workPath) {
        Intent intent = new Intent(this, CameraActivity.class);
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                FileUtil.getSaveFile(getApplication(), workPath).getAbsolutePath());
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE,
                CameraActivity.CONTENT_TYPE_GENERAL);
        startActivityForResult(intent, REQUEST_CODE_WORK_LICENSE);
    }

    /**
     * 驾驶证正页照片
     * @param
     */
    private void takeDriverPhoto() {
        driverPath = "driver_" + System.currentTimeMillis();
        Intent intent = new Intent(this, CameraActivity.class);
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                FileUtil.getSaveFile(getApplication(), driverPath).getAbsolutePath());
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE,
                CameraActivity.CONTENT_TYPE_GENERAL);
        startActivityForResult(intent, REQUEST_CODE_DRIVING_LICENSE);
    }

    /**
     * 驾驶证副页照片
     * @param
     */
    private void takeDriverReversePhoto() {
        driverReversePath = "driver_" + System.currentTimeMillis();
        Intent intent = new Intent(this, CameraActivity.class);
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                FileUtil.getSaveFile(getApplication(), driverReversePath).getAbsolutePath());
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE,
                CameraActivity.CONTENT_TYPE_GENERAL);
        startActivityForResult(intent, REQUEST_CODE_DRIVING_REVERSE_LICENSE);
    }
    /**
     * 正面身份证拍照
     */
    private void takeIDCard() {
        frontPath = FRONT + "_" + System.currentTimeMillis();
        Intent intent = new Intent(DriverConfigActivity.this, CameraActivity.class);
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                FileUtil.getSaveFile(getApplication(), frontPath).getAbsolutePath());
        intent.putExtra(CameraActivity.KEY_NATIVE_ENABLE,
                true);
        // KEY_NATIVE_MANUAL设置了之后CameraActivity中不再自动初始化和释放模型
        // 请手动使用CameraNativeHelper初始化和释放模型
        // 推荐这样做，可以避免一些activity切换导致的不必要的异常
        intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL,
                true);
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    private void recIDCard(final String idCardSide, final String filePath) {
        IDCardParams param = new IDCardParams();
        param.setImageFile(new File(filePath));
        // 设置身份证正反面
        param.setIdCardSide(idCardSide);
        // 设置方向检测
        param.setDetectDirection(true);
        // 设置图像参数压缩质量0-100, 越大图像质量越好但是请求时间越长。 不设置则默认值为20
        param.setImageQuality(20);

        OCR.getInstance(this).recognizeIDCard(param, new OnResultListener<IDCardResult>() {
            @Override
            public void onResult(IDCardResult result) {
                if (result != null) {
                    result.getAddress();
//                    alertText(idCardSide, new Gson().toJson(result));
                    setIDCardInfo(idCardSide, new Gson().toJson(result), filePath);
                }
            }

            @Override
            public void onError(OCRError error) {
                alertText("", error.getMessage());
            }
        });
    }

    private void setIDCardInfo(final String idCardSide, final String result, final String filePath) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (idCardSide) {
                    case FRONT:
//                        Glide.
                        IDCardInfoFrontBean idCardInfoFrontBean = new Gson().fromJson(result, IDCardInfoFrontBean.class);
                        if (idCardInfoFrontBean == null) {
                            ToastUtils.popUpToast("身份证选择失败，请重新选择");
                            break;

                        }
                        if (!idCardInfoFrontBean.getImageStatus().equals("normal")) {
                            ToastUtils.popUpToast("身份证照片不正常，请重新选择");
                            break;
                        }
                        mEtRealName.setText(idCardInfoFrontBean.getName().getWords());
                        mEtDetailAddress.setText(idCardInfoFrontBean.getGender().getWords());
                        String number = idCardInfoFrontBean.getIdNumber().getWords();
                        int age = IDCardUtils.IdNOToAge(number);
                        mTvCurrentAddress.setText(idCardInfoFrontBean.getAddress().getWords());
                        mEtCardNum.setText(number);
                        mPresenter.upload(new File(filePath), UPLOAD_ID_CARD_FRONT);
                        break;
                    case BACK:
                        mPresenter.upload(new File(filePath), UPLOAD_ID_CARD_BACK);
                        break;
                }

            }
        });

    }

    /**
     * 反面身份证扫描
     */
    private void takeIDCardReverse() {
        backPath = BACK + "_" + System.currentTimeMillis();
        Intent intent = new Intent(DriverConfigActivity.this, CameraActivity.class);
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                FileUtil.getSaveFile(getApplication(), backPath).getAbsolutePath());
        intent.putExtra(CameraActivity.KEY_NATIVE_ENABLE,
                true);
        // KEY_NATIVE_MANUAL设置了之后CameraActivity中不再自动初始化和释放模型
        // 请手动使用CameraNativeHelper初始化和释放模型
        // 推荐这样做，可以避免一些activity切换导致的不必要的异常
        intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL,
                true);
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_BACK);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //身份证正面返回
        if (requestCode == REQUEST_CODE_PICK_IMAGE_FRONT && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            String filePath = getRealPathFromURI(uri);
            mIvCardFront.setVisibility(View.VISIBLE);
            Glide.with(this).load(filePath).into(mIvCardFront);
            mTvCard1.setVisibility(View.VISIBLE);
            recIDCard(IDCardParams.ID_CARD_SIDE_FRONT, filePath);
        }
        //身份证反面返回
        if (requestCode == REQUEST_CODE_PICK_IMAGE_BACK && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            String filePath = getRealPathFromURI(uri);
            recIDCard(IDCardParams.ID_CARD_SIDE_BACK, filePath);
            Glide.with(this).load(filePath).into(mIvCardRevers);
            mIvCardRevers.setVisibility(View.VISIBLE);
            mTvCard2.setVisibility(View.VISIBLE);
        }
        //
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                String contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
                String filePath = "";
                if (!TextUtils.isEmpty(contentType)) {
                    if (CameraActivity.CONTENT_TYPE_ID_CARD_FRONT.equals(contentType)) {
                        filePath = FileUtil.getSaveFile(getApplicationContext(), frontPath).getAbsolutePath();
                        recIDCard(IDCardParams.ID_CARD_SIDE_FRONT, filePath);
                        Glide.with(this).load(filePath).into(mIvCardFront);
                        mTvCard1.setVisibility(View.VISIBLE);
                        mIvCardFront.setVisibility(View.VISIBLE);

                    } else if (CameraActivity.CONTENT_TYPE_ID_CARD_BACK.equals(contentType)) {
                        filePath = FileUtil.getSaveFile(getApplicationContext(), backPath).getAbsolutePath();

                        Glide.with(this).load(filePath).into(mIvCardRevers);
                        mTvCard2.setVisibility(View.VISIBLE);
                        mIvCardRevers.setVisibility(View.VISIBLE);
                        recIDCard(IDCardParams.ID_CARD_SIDE_BACK, filePath);
                    }
                }
            }
        }
        // 识别成功回调，驾驶证识别
        if (requestCode == REQUEST_CODE_DRIVING_LICENSE && resultCode == Activity.RESULT_OK) {
            final String filePath = FileUtil.getSaveFile(getApplicationContext(), driverPath).getAbsolutePath();

            RecognizeService.recDrivingLicense(this, FileUtil.getSaveFile(getApplicationContext(), driverPath).getAbsolutePath(),
                    new RecognizeService.ServiceListener() {
                        @Override
                        public void onResult(String result) {
                            DriverLicenseBean driverLicenseBean = new Gson().fromJson(result, DriverLicenseBean.class);
                            DriverLicenseBean.WordsResultBean words_result = driverLicenseBean.getWords_result();
                            DriverLicenseBean.WordsResultBean.准驾车型Bean 准驾车型 = words_result.get准驾车型();
                            DriverLicenseBean.WordsResultBean.有效期限Bean 有效期限 = words_result.get有效期限();
                            DriverLicenseBean.WordsResultBean.至Bean 至 = words_result.get至();
                            DriverLicenseBean.WordsResultBean.证号Bean 证号 = words_result.get证号();
                            DriverLicenseBean.WordsResultBean.有效起始日期Bean 有效起始日期 = words_result.get有效起始日期();
                            driverNum = 证号.getWords();
                            permitType = 准驾车型.getWords();
                            mPresenter.upload(new File(filePath), UPLOAD_DRIVER);
                            mEtPermitType.setText(准驾车型.getWords());

                            String yxqsWords = 有效起始日期.getWords();
                            if(yxqsWords.length() == 8){
                                mTvStartDate.setText(yxqsWords.substring(0,4)+"-"+yxqsWords.substring(4,6)+"-"+yxqsWords.substring(6,yxqsWords.length()));

                                mEtEndDate.setText(有效期限.getWords());
                            }

                            if(yxqsWords == null && 至 != null && StringUtil.isNotEmpty(至.getWords()) && 至.getWords().length() ==8) {
                                String yxqxqiWords = 有效期限.getWords();
                                String yxqxjzWords = 至.getWords();
                                mTvStartDate.setText(yxqxqiWords.substring(0, 4) + "-" + yxqxqiWords.substring(4, 6) + "-" + yxqxqiWords.substring(6, yxqxqiWords.length()));
                                int startYear =  Integer.parseInt (yxqxqiWords.substring(0,4));
                                int endYear =  Integer.parseInt (yxqxjzWords.substring(0,4));
                                int trem = startYear - endYear;
                                mEtEndDate.setText(Integer.toString(trem)+'年');
                            }
//                            if(至 != null && StringUtil.isNotEmpty(至.getWords()) && 至.getWords().length() ==8){
//                                mEtEndDate.setText(至.getWords().substring(0,4)+"-"+yxWords.substring(4,6)+"-"+yxWords.substring(6,yxWords.length()));
//                                endTime = 至.getWords().substring(0,4)+"-"+yxWords.substring(4,6)+"-"+yxWords.substring(6,yxWords.length());
//                            }
//                            startTime = 有效期限.getWords();

                            mIvDriverFront.setVisibility(View.VISIBLE);
                            mTvDriver.setVisibility(View.VISIBLE);
                            Glide.with(getContext()).load(filePath).into(mIvDriverFront);

                        }
                    });
        }
        // 识别成功回调，驾驶证副页识别
        if (requestCode == REQUEST_CODE_DRIVING_REVERSE_LICENSE && resultCode == Activity.RESULT_OK) {
            final String filePath = FileUtil.getSaveFile(getApplicationContext(), driverReversePath).getAbsolutePath();

            RecognizeService.recDrivingLicense(this, filePath,
                    new RecognizeService.ServiceListener() {
                        @Override
                        public void onResult(String result) {
                            mIvDriverReverse.setVisibility(View.VISIBLE);
                            Glide.with(getContext()).load(filePath).into(mIvDriverReverse);
                            mPresenter.upload(new File(filePath),UPLOAD_DRIVER_REVERSE);
                        }
                    });
        }
        // 识别成功回调，自定义模板上岗证回调
        if (requestCode == REQUEST_CODE_WORK_LICENSE && resultCode == Activity.RESULT_OK) {
            final String filePath = FileUtil.getSaveFile(getApplicationContext(), workPath).getAbsolutePath();

            RecognizeService.recCustom(this, FileUtil.getSaveFile(getApplicationContext(), workPath).getAbsolutePath(), TEMPLATE_WORK_SIGN, 0,
                    new RecognizeService.ServiceListener() {
                        @Override
                        public void onResult(String result) {
                            ToastUtils.popUpToast("此识别结果仅供参考，请仔细比对检查");
                            Glide.with(getContext()).load(filePath).into(mIvWorkFront);
                            mPresenter.upload(new File(filePath), UPLOAD_WORK);
                            boolean json = StringUtils.isJson(result);

                            if (!json) {
                                ToastUtils.popUpToast("上传失败请重试");
                                return;
                            }
                            WorkBean workBean = new Gson().fromJson(result, WorkBean.class);
                            if (workBean != null) {
                                WorkBean.DataBean workBeanData = workBean.getData();
                                List<WorkBean.DataBean.RetBean> ret = workBeanData.getRet();
                                if (ret != null) {
                                    for (WorkBean.DataBean.RetBean retBean : ret) {
                                        String word_name = retBean.getWord_name();
                                        String word = retBean.getWord();
                                        if (word_name.equals("certificateNum")) {
                                            mEtPostCard.setText(word);
                                        } else if (word_name.equals("sendTime")) {
                                            if (word.contains("日")) {
                                                word = word.substring(0, word.indexOf("日") + 1);
                                            }
                                            if (word.contains("年")) {
                                                word.replace("年","-");
                                            }
                                            if (word.contains("月")) {
                                                word.replace("月","-");
                                            }
                                            if (word.contains("日")) {
                                                word.replace("日","-");
                                            }
                                            firstReceiveTime = word;
                                            mTvFirstReceive.setText(word);
                                        } else if (word_name.equals("validPeriod")) {
                                            mTvYouXiaoQi.setText(word);
                                        } else if (word_name.equals("category")){
                                            meTCategory.setText(word);
                                        }
                                    }
                                }
                            } else {
                                ToastUtils.popUpToast("识别失败请重新上传或者手动填写");
                            }
                        }
                    });
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    private void alertText(final String title, final String message) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.popUpToast(title);

                ToastUtils.popUpToast(message);
                Log.d("title", title);
                Log.d("message", message);

            }
        });
    }

    /**
     * 准驾车型选择
     */
    public void onOptionPicker() {
        OptionPicker picker = new OptionPicker(this, new String[]{
                "A1", "A2", "B1", "B2", "C1", "C2"
        });
        picker.setCanceledOnTouchOutside(false);
        picker.setDividerRatio(WheelView.DividerConfig.FILL);
//        picker.setShadowColor(Color.RED, 40);
        picker.setSelectedIndex(1);
        picker.setCycleDisable(true);
        picker.setTextSize(18);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
//                showToast("index=" + index + ", item=" + item);
                permitType = item;
                mEtPermitType.setText(permitType);
            }
        });
        picker.show();
    }

    public void onYearMonthDayPicker(final int type) {
        final DatePicker picker = new DatePicker(this);
        picker.setCanceledOnTouchOutside(true);
        picker.setTextSize(20);
        picker.setUseWeight(true);
        picker.setTopPadding(ConvertUtils.toPx(this, 10));
        picker.setRangeEnd(2200, 12, 30);
        picker.setRangeStart(1960, 1, 1);
        String today = DateUtil.getToday();
        picker.setSelectedItem(Integer.parseInt(today.substring(0,4)), Integer.parseInt(today.substring(5,7)), Integer.parseInt(today.substring(8,today.length())));
        picker.setResetWhileWheel(false);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                switch (type) {
                    case DATE_TYPE_START_DATE:
                        startTime = year + "-" + month + "-" + day;
                        mTvStartDate.setText(startTime);
                        break;
                    case DATE_TYPE_END_DATE:
                        endTime = year + "-" + month + "-" + day;
                        mEtEndDate.setText(endTime);
                        break;
                    case DATE_TYPE_FIRST_RECEIVE_DATE:
                        firstReceiveTime = year + "-" + month + "-" + day;
                        mTvFirstReceive.setText(firstReceiveTime);
                        break;
                    case DATE_TYPE_VALID_DATE:
                        youxiaoqiTime = year + "-" + month + "-" + day;
                        mTvYouXiaoQi.setText(youxiaoqiTime);
                        break;
                }
            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
        picker.show();
    }


    @Override
    public void imageUrl(String url, int type) {
        switch (type) {
            case UPLOAD_ID_CARD_FRONT:
                idCardFrontUrl = IMAGE_BASE_URL + url;
                break;
            case UPLOAD_ID_CARD_BACK:
                idCardBackUrl = IMAGE_BASE_URL + url;
                break;
            case UPLOAD_DRIVER:
                driverUrl = IMAGE_BASE_URL + url;
                break;
            case UPLOAD_DRIVER_REVERSE:
                driverReverseUrl = IMAGE_BASE_URL + url;
                break;
            case UPLOAD_WORK:
                workUrl = IMAGE_BASE_URL + url;
                break;
            case UPLOAD_SIGN:
                signUrl = IMAGE_BASE_URL + url;
                break;
        }
    }

    @Override
    public void getWrokIdDataSuc(WrodIdBean data) {
        WrodIdBean.DataBean.CertificateDriverBoBean certificateDriverBo = data.getData().getCertificateDriverBo();
        WrodIdBean.DataBean.CertificateIDBoBean certificateIDBo = data.getData().getCertificateIDBo();
        WrodIdBean.DataBean.CertificateWorkBoBean certificateWorkBo = data.getData().getCertificateWorkBo();
        mEtRealName.setText(certificateIDBo.getName());
        mEtCardNum.setText(certificateIDBo.getIdno());
        mEtDetailAddress.setText(certificateIDBo.getSix());
        mTvCurrentAddress.setText(certificateIDBo.getAddress());

        mEtPermitType.setText(certificateDriverBo.getClasss() + "");
        mTvStartDate.setText(certificateDriverBo.getStartTime());
        mEtEndDate.setText(certificateDriverBo.getTerm());

        if (!TextUtils.isEmpty(certificateIDBo.getPicUrl())) {
            idCardFrontUrl = certificateIDBo.getPicUrl();
            Glide.with(getContext()).load(certificateIDBo.getPicUrl()).into(mIvCardFront);
        }
        if (!TextUtils.isEmpty(certificateIDBo.getPicUrl2())) {
            idCardBackUrl = certificateIDBo.getPicUrl2();
            Glide.with(getContext()).load(certificateIDBo.getPicUrl2()).into(mIvCardRevers);
        }
        driverUrl = certificateDriverBo.getPicUrl();
        driverReverseUrl =certificateDriverBo.getPicUrl2();
        Glide.with(getContext()).load(certificateDriverBo.getPicUrl()).into(mIvDriverFront);
        Glide.with(getContext()).load(certificateDriverBo.getPicUrl2()).into(mIvDriverReverse);

        Glide.with(getContext()).load(certificateWorkBo.getPicUrl()).into(mIvWorkFront);
//        Glide.with(getContext()).load(certificateDriverBo.getPicUrl()).into(iv_card_front_qy);
        workUrl = certificateWorkBo.getPicUrl();
        signUrl = data.getData().getSignUrl();
       Glide.with(getContext()).load(signUrl).into(mImgSign);
       meTCategory.setText(certificateWorkBo.getCategory().toString());
        mEtPostCard.setText(certificateWorkBo.getGrantNo());
        mTvFirstReceive.setText(StringUtil.isNotEmpty(certificateWorkBo.getFirstTime())?certificateWorkBo.getFirstTime():"请选择");
        mTvYouXiaoQi.setText(StringUtil.isNotEmpty(certificateWorkBo.getValidityEndTime())?certificateWorkBo.getValidityEndTime():"请选择");
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
    public void success(String json) {
        BaseResponse baseResponse = new Gson().fromJson(json, BaseResponse.class);
        int code = baseResponse.getCode();
        if (code == 0) {
            ToastUtils.popUpToast("提交成功");

            if (getIntent().getStringExtra("flag").equals("0")) {
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
            }else {
                finish();
            }

        }
    }
//    private boolean checkTokenStatus() {
//        if (!hasGotToken) {
//            Toast.makeText(getApplicationContext(), "token还未成功获取", Toast.LENGTH_LONG).show();
//        }
//        return hasGotToken;
//    }

}
