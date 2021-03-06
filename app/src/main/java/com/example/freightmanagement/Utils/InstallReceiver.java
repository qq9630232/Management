package com.example.freightmanagement.Utils;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import static com.example.freightmanagement.Base.BaseApiConstants.APK_PATH_ABSOULT;

public class InstallReceiver extends BroadcastReceiver {

    // 安装下载接收器
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
            installApk(context);
        }
    }

    // 安装Apk
    private void installApk(Context context) {
        try {
            Intent i = new Intent(Intent.ACTION_VIEW);
            String filePath = APK_PATH_ABSOULT+"GTLXKJ.apk";
            System.out.println(filePath);
            i.setDataAndType(Uri.parse("file://" + filePath), "application/vnd.android.package-archive");
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}