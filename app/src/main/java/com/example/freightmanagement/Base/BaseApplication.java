package com.example.freightmanagement.Base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.util.EMLog;
import com.qiniu.pili.droid.streaming.StreamingEnv;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.example.freightmanagement.common.ApiConstants.BAIDU_API_KEY;
import static com.example.freightmanagement.common.ApiConstants.BAIDU_SECRET_KEY;

/**
 * @author: ztc
 * 2019/8/2
 */

public class BaseApplication extends Application {
    private static BaseApplication mBaseApplication;
    private List<Activity> mActivityList = new ArrayList<>();

    @Override
    final protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApplication = this;
        StreamingEnv.init(getApplicationContext());
        initAccessTokenWithAkSk();
        initChatSdk();
//        PushStreamHelper.getInstance().init(this);
    }
    /**
     * 用明文ak，sk初始化
     */
    private void initAccessTokenWithAkSk() {
        OCR.getInstance(this).initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                String token = result.getAccessToken();
//                hasGotToken = true;
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
//                alertText("AK，SK方式获取token失败", error.getMessage());
            }
        }, getApplicationContext(),  BAIDU_API_KEY, BAIDU_SECRET_KEY);
    }
    private void initChatSdk(){
        EMOptions options = new EMOptions();
//    options.enableDNSConfig(false);
//    options.setRestServer("a1-hsb.easemob.com");
//    options.setIMServer("116.85.43.118");
//    options.setImPort(6717);
        EmClientInit(options);
        EMClient.getInstance().init(this, options);
//        EmClientInit(this, options);

        EMClient.getInstance().setDebugMode(true);
        //注册一个监听连接状态的listener
        EMClient.getInstance().addConnectionListener(new MyConnectionListener());

    }

    //实现ConnectionListener接口
    private class MyConnectionListener implements EMConnectionListener {
        @Override
        public void onConnected() {
        }
        @Override
        public void onDisconnected(int error) {
            EMLog.d("global listener", "onDisconnect" + error);
            if (error == EMError.USER_REMOVED) {
//                onUserException(Constant.ACCOUNT_REMOVED);
            } else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
//                onUserException(Constant.ACCOUNT_CONFLICT);
            } else if (error == EMError.SERVER_SERVICE_RESTRICTED) {
//                onUserException(Constant.ACCOUNT_FORBIDDEN);
            } else if (error == EMError.USER_KICKED_BY_CHANGE_PASSWORD) {
//                onUserException(Constant.ACCOUNT_KICKED_BY_CHANGE_PASSWORD);
            } else if (error == EMError.USER_KICKED_BY_OTHER_DEVICE) {
//                onUserException(Constant.ACCOUNT_KICKED_BY_OTHER_DEVICE);
            }
        }
    }


    private void EmClientInit(EMOptions options) {
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
// 如果APP启用了远程的service，此application:onCreate会被调用2次
// 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
// 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回

        if (processAppName == null ||!processAppName.equalsIgnoreCase(getApp().getPackageName())) {
//            Log.e(TAG, "enter the service process!");
            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }

    }


    private EMOptions initChatOptions() {
        Log.d("aaa", "init HuanXin Options");

        EMOptions options = new EMOptions();
        // change to need confirm contact invitation
        options.setAcceptInvitationAlways(false);
        // set if need read ack
        options.setRequireAck(true);
        // set if need delivery ack
        options.setRequireDeliveryAck(false);

        return options;
    }
    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }
    /**
     * 把界面添加到集合
     *
     * @param activity activity对象
     */
    final public void addActivity(Activity activity) {
        if (!mActivityList.contains(activity)) {
            mActivityList.add(activity);
        }
    }

    /**
     * 移除Activity
     *
     * @param activity activity对象
     */
    final public void removeActivity(Activity activity) {
        if (!mActivityList.isEmpty()) {
            mActivityList.remove(activity);
        }
    }

    /**
     * 移除所有Activity
     */
    final public void clearActivity() {
        for (int i = mActivityList.size(); i > 0; i--) {
            Activity activity = mActivityList.get(i - 1);
            if (activity != null) {
                activity.finish();
            }
            mActivityList.remove(i - 1);
        }
    }

    public static BaseApplication getApp() {
        return mBaseApplication;
    }

}