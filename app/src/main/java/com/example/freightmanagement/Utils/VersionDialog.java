package com.example.freightmanagement.Utils;


import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.freightmanagement.Base.BaseActivity;
import com.example.freightmanagement.R;

import static com.example.freightmanagement.Base.BaseApiConstants.APKHOST;

/**
 * 自定义dialog
 */
public class VersionDialog extends Dialog implements
        View.OnClickListener {

    private  BaseActivity homeActivity;
    /**
     * 布局文件
     **/
    int layoutRes;

    /**
     * 上下文对象
     **/
    Context context;


    /**
     * 取消按钮
     **/
    private Button bt_cancal;

    /**
     * 更新按钮
     **/
    private Button bt_delect;

    private String content;//版本内容

    private String versionName;//版本号



    public VersionDialog(Context context) {
        super(context);
        this.context = context;
    }

    /**
     * 自定义布局的构造方法
     *
     * @param context
     * @param resLayout
     */
    public VersionDialog(Context context, int resLayout) {
        super(context);
        this.context = context;
        this.layoutRes = resLayout;
    }

    /**
     * 自定义主题及布局的构造方法
     *
     * @param context
     * @param theme
     * @param resLayout
     * @param
     */
    public VersionDialog(Context context,int theme, BaseActivity activity,int resLayout,String content,String versionName) {
        super(context, theme);
        this.context = context;
        this.content = content;
        this.layoutRes = resLayout;
        this.homeActivity = activity;
        this.versionName = versionName;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 指定布局
        this.setContentView(layoutRes);
        TextView  textView=(TextView) findViewById(R.id.version_content);
        TextView  titleView=(TextView) findViewById(R.id.lay_view);
        textView.setText("更新内容：\n\n"+content);
        titleView.setText("发现新版本 "+versionName);
        // 根据id在布局中找到控件对象
        bt_cancal = (Button) findViewById(R.id.cancal);
        bt_delect = (Button) findViewById(R.id.update);
        // 为按钮绑定点击事件监听器
        bt_cancal.setOnClickListener(this);
        bt_delect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.update:
                optUpdateApk();
                this.dismiss();
                break;

            // 取消按钮
            case R.id.cancal:
                this.dismiss();

            default:
                break;
        }
    }
    /**
     * 操作  版本更新
     *
     */
    private void optUpdateApk( ) {
        try {
            homeActivity.downloadApk(APKHOST);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
