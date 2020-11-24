package com.example.freightmanagement.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.example.freightmanagement.Base.BaseActivity;
import com.example.freightmanagement.Bean.TokenBean;
import com.example.freightmanagement.Bean.VersionBean;
import com.example.freightmanagement.R;
import com.example.freightmanagement.Utils.Network.OnRequestResultForCommon;
import com.example.freightmanagement.Utils.Network.RestApi;
import com.example.freightmanagement.Utils.PrefUtilsData;
import com.example.freightmanagement.Utils.StringUtils;
import com.example.freightmanagement.Utils.ToastUtils;
import com.example.freightmanagement.Utils.VersionDialog;
import com.example.freightmanagement.enums.AdminTypeEnum;
import com.example.freightmanagement.model.AccountParam;
import com.example.freightmanagement.presenter.LoginPresenter;
import com.google.gson.Gson;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMChatRoom;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCursorResult;
import com.hyphenate.exceptions.HyphenateException;
import com.xuexiang.xupdate.XUpdate;
import com.xuexiang.xupdate._XUpdate;
import com.xuexiang.xupdate.service.OnFileDownloadListener;

import java.io.File;

import static com.example.freightmanagement.Base.BaseApiConstants.WEBVERSION;
import static com.example.freightmanagement.common.Constants.ADMIN_TYPE;

/**
 * Created by songdechuan on 2020/8/6.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginPresenter.View, View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    /**
     * 请输入手机号
     */
    private EditText mEdtTxtYzmImg;
    /**
     * 请输入验证码
     */
    private EditText mEdtTxtPasswordPhone;
    /**
     * 注册账号
     */
    private TextView mTvRegister;
    /**
     * 忘记密码
     */
    private TextView mTvForget;
    /**
     * 登录
     */
    private TextView mTvSrue;
    /**
     * 驾驶员
     */
    private RadioButton mLoginRb1;
    /**
     * 车主
     */
    private RadioButton mLoginRb2;
    /**
     * 企业
     */
    private RadioButton mLoginRb3;
    private RadioGroup mLoginRg;
    private String type = "1";
    private int code = 1;
    private String tel;
    private String password;

    /**
     * 版本检查
     */
    private VersionDialog dialog;
    private boolean flag;//是否检测版本初始化
    private TextView tv_Version;

    @Override
    public int setLayoutResource() {
        return R.layout.activity_login;
    }
    private boolean checkGalleryPermission() {
        int ret = ActivityCompat.checkSelfPermission(this, Manifest.permission
                .READ_EXTERNAL_STORAGE);
        int wet = ActivityCompat.checkSelfPermission(this, Manifest.permission
                .WRITE_EXTERNAL_STORAGE);
        if (ret != PackageManager.PERMISSION_GRANTED && wet != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1000);
            return false;
        }
        return true;
    }
    @Override
    protected void onInitView() {
        checkGalleryPermission();
        initView();
        flag=true;
        // 获取本版本号，是否更新
        // getVersion(VersionUtils.getVersion(this));
        String url ="https://xuexiangjys.oss-cn-shanghai.aliyuncs.com/apk/xupdate_demo_1.0.2.apk";
//        String url ="http://47.114.155.239:3005/app/Freightmanagement.apk";
//        try {
//        try {
//            downloadApk(url);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
            XUpdate.newBuild(getApplicationContext())
                    .apkCacheDir(Environment.getExternalStorageDirectory().getAbsolutePath()) //设置下载缓存的根目录
                    .build()
                    .download(url, new OnFileDownloadListener() {   //设置下载的地址和下载的监听
                        @Override
                        public void onStart() {
                            Log.e("zxz", "kaishi");
//                            HProgressDialogUtils.showHorizontalProgressDialog(getContext(), "下载进度", false);
                        }

                        @Override
                        public void onProgress(float progress, long total) {
//                            HProgressDialogUtils.setProgress(Math.round(progress * 100));
//                            ToastUtils.popUpToast(Math.round(progress * 100));
                            Log.e("zxz",""+Math.round(progress * 100));

                        }

                        @Override
                        public boolean onCompleted(File file) {
//                            HProgressDialogUtils.cancel();
                            Log.e("zxz", "onCompleted: ");
                            ToastUtils.popUpToast("apk下载完毕，文件路径：" + file.getPath());
                            _XUpdate.startInstallApk(getContext(), file); //填写文件所在的路径

                            return false;
                        }

                        @Override
                        public void onError(Throwable throwable) {
//                            HProgressDialogUtils.cancel();
                            Log.e("zxz", "error: ");

                        }
                    });
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
        // tv_Version.setText(VersionUtils.getVersionName(this) + "-" +VersionUtils.getVersion(this));
    }

    @Override
    protected void onLoadData2Remote() {
//        AccountParam accountParam = new AccountParam();
//        accountParam.setPass("1");
//        accountParam.setTel("15106619001");
//        accountParam.setType("1");
//        mPresenter.login(accountParam);
        PrefUtilsData.setType("1");
    }


    public void initView() {
        mEdtTxtYzmImg = (EditText) findViewById(R.id.edtTxt_yzm_img);
        mEdtTxtPasswordPhone = (EditText) findViewById(R.id.edtTxt_password_phone);
        mTvRegister = (TextView) findViewById(R.id.tv_register);
        mTvRegister.setOnClickListener(this);
        mTvForget = (TextView) findViewById(R.id.tv_forget);
        mTvForget.setOnClickListener(this);
        mTvSrue = (TextView) findViewById(R.id.tv_srue);
        mTvSrue.setOnClickListener(this);
        mLoginRb1 = (RadioButton) findViewById(R.id.login_rb1);
        mLoginRb2 = (RadioButton) findViewById(R.id.login_rb2);
        mLoginRb3 = (RadioButton) findViewById(R.id.login_rb3);
        mLoginRg = (RadioGroup) findViewById(R.id.login_rg);
        mLoginRg.setOnCheckedChangeListener(this);
        tv_Version = findViewById(R.id.tv_version);
        tv_Version.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_register:
                startActivity(this, RegisterActivity.class);
                break;
            case R.id.tv_forget:
                startActivity(this, ForgetPasswordActivity.class);
                break;
            case R.id.tv_srue:
                tel = mEdtTxtYzmImg.getText().toString();
                if (StringUtils.isEmpty(tel)) {
                    ToastUtils.popUpToast(R.string.tel_cannot_be_empty);
                    return;
                }
                password = mEdtTxtPasswordPhone.getText().toString();
                if (StringUtils.isEmpty(password)) {
                    ToastUtils.popUpToast(R.string.password_cannot_be_empty);
                    return;
                }
                tel = tel.trim();
                password = password.trim();
                AccountParam accountParam = new AccountParam();
                accountParam.setPass(password);
                accountParam.setTel(tel);
                accountParam.setType(type);
                mPresenter.login(accountParam);
                PrefUtilsData.setType(type);
                break;
            case R.id.tv_version:
                // 获取本版本号，是否更新
                // getVersion(VersionUtils.getVersion(this));
                break;
        }
    }

    @Override
    public void getDataSuc(TokenBean data) {
        PrefUtilsData.setMobile(tel);
        if (data != null) {
            TokenBean.DataBean dataBean = data.getData();
            TokenBean.DataBean.UserBean user = dataBean.getUser();
            int type = dataBean.getType();
            //登录过
            if(EMClient.getInstance().isLoggedInBefore()){
//                mView.getDataSuc(loginBean);
            }else {
                //环信默认密码：123abcdg
//                String emPassword = "123abcdg";
                EMClient.getInstance().login(tel,password,new EMCallBack() {//回调
                    @Override
                    public void onSuccess() {
                        EMClient.getInstance().groupManager().loadAllGroups();
                        EMClient.getInstance().chatManager().loadAllConversations();
                        Log.d("main", "登录聊天服务器成功！");
                        try {
                            EMCursorResult<EMChatRoom> result = EMClient.getInstance().chatroomManager().fetchPublicChatRoomsFromServer(100, null);
                        } catch (HyphenateException e) {
                            e.printStackTrace();
                        }
//                            mView.getDataSuc(loginBean);
                    }

                    @Override
                    public void onProgress(int progress, String status) {

                    }

                    @Override
                    public void onError(int code, String message) {
                        Log.d("main", "登录聊天服务器失败！");

                    }
                });
            }
            switch (type){
                case 0:
                    if(this.code == 1){
                        startActivity(DriverConfigActivity.class,type);
                    }else if(this.code == 2){
                        startActivity(CarOwnerActivity.class,type);
                    }else if(this.code == 3){
                        startActivity(CompanyRegisterActivity.class,type);
                    }
                    break;
                case 1:
                    startActivity( MainActivity.class,code);
                    break;
                case 2:
                    if(user.getIdcertificateId() == 0){
                        startActivity(CarOwnerActivity.class,type);
                        return;
                    }
                    startActivity( MainActivity.class,code);
                    break;
                case 3:

                    if(user.getIdcertificateId() == 0 && user.getCertificateBusinessId() == 0
                            && user.getCertificateOperationId() == 0){
                        startActivity(CompanyRegisterActivity.class,type);
                        return;
                    }
                    startActivity( MainActivity.class,code);
                    break;
            }

        }
    }

    @Override
    public void onFailed(String error) {
        ToastUtils.popUpToast("登录失败!");
    }

    @Override
    protected LoginPresenter onInitLogicImpl() {
        return new LoginPresenter();
    }

    private void startActivity(Class<?> cls, int type) {
        Intent intent = new Intent(this, cls);
        intent.putExtra("flag", "0");
        intent.putExtra(ADMIN_TYPE, type);
        startActivity(intent);
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

        switch (checkedId){
            case R.id.login_rb1:
                type = AdminTypeEnum.DRIVER.getValue();
                code = AdminTypeEnum.DRIVER.getCode();
                break;
            case R.id.login_rb2:
                type = AdminTypeEnum.CAR_OWNER.getValue();
                code = AdminTypeEnum.CAR_OWNER.getCode();
                break;
            case R.id.login_rb3:
                type = AdminTypeEnum.COMPANY.getValue();
                code = AdminTypeEnum.COMPANY.getCode();
                break;
        }
    }

    //版本更新弹出框
    public  void showUploadApkDialog(String content,String versionName) {
        Log.i("HomeActivity", "showUploadTaskDialog");
        dialog = new VersionDialog(this,0,this,R.layout.version_update_dialog,content,versionName);
        dialog.show();
    }



    // 获取更新版本号
    private void getVersion(final int vision) {
//        if (!NetWorkUtil.isNetworkAvalible(context)) {
//           // Toast.makeText(HomeActivity.this,"请检查网络",Toast.LENGTH_SHORT).show();
//            ToastUtils.popUpToast("请检查网络");
//            return;
//        }

        //RequestParams params = new RequestParams(ConfigurationUtil.WEBVERSION);
        RestApi.getInstance().get(WEBVERSION,  new OnRequestResultForCommon() {
            @Override
            public void onSuccess(String result) {
                Log.i("onSuccess", "onSuccess");
                try{
                    //JSONObject object = new JSONObject(result);
                    VersionBean versionBean =  new Gson().fromJson(result, VersionBean.class);
                    //返回状态
                    String status = "1";//object.optString("code");
                    //返回的更新内容
                    //返回的版本号
                    //返回版本
                    String nversion = versionBean.getData().getVersion();
                    if("1".equals(status)){
                        Long newversion = Long.parseLong(nversion);
                        if (newversion != vision) {//新旧版本比较
                            if (vision < newversion) {//旧版本低于新版本则更新
                                System.out.println("新版本：v"+newversion + "   旧版本v"+ vision);
                                // 版本号不同
                                showUploadApkDialog("新版本优化","版本号："+nversion);
                            }
                        }else if(!flag){
//                            Toast.makeText(this,"已经是最新版本",Toast.LENGTH_SHORT).show();
                            ToastUtils.popUpToast("已经是最新版本");
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    Log.e("onSuccess", "Error");
                }
            }

            @Override
            public void onFail() {
                super.onFail();
            }
        });
    }
}
