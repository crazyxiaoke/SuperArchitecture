package com.app.lib_common.base;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.app.lib_common.BuildConfig;

/**
 * 　　┏┓　　　　┏┓
 * 　┏┛┻━━━━┛┻┓
 * 　┃　　　　　　　　┃
 * 　┃　　　━　　　　┃
 * 　┃　┳┛　┗┳　　┃
 * 　┃　　　　　　　　┃
 * 　┃　　　┻　　　　┃
 * 　┃　　　　　　　　┃
 * 　┗━━┓　　　┏━┛
 * 　　　　┃　　　┃　　　神兽保佑
 * 　　　　┃　　　┃　　　代码无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * <p>
 * Created by zxk on 18-8-8.
 */
public class BaseApplication extends Application {
    private static BaseApplication instance;
    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = getApplicationContext();

        if (BuildConfig.DEBUG) { //如果在debug模式下
            // 打印日志,默认关闭
            ARouter.openLog();
            // 开启调试模式，默认关闭(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
            // 打印日志的时候打印线程堆栈
            ARouter.printStackTrace();
        }

        ARouter.init(this);
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    public Context getContext() {
        return mContext;
    }
}
