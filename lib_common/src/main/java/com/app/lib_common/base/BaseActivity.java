package com.app.lib_common.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.app.lib_common.utils.StatusBarUtil;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

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
 * Created by zxk on 18-8-13.
 */
public abstract class BaseActivity extends SwipeBackActivity {
    private SwipeBackLayout mSwipeBackLayout;
    protected Context mContext;
    protected Activity mThisActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mThisActivity = this;
        setSwipeBackEnable(true); //开启滑动返回
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        initView(savedInstanceState); // 设置布局
        setStatusBar(); //设置状态栏
    }

    /**
     * 设置布局
     *
     * @param savedInstanceState
     */
    protected abstract void initView(@Nullable Bundle savedInstanceState);

    /**
     * 设置状态栏
     */
    protected void setStatusBar() {
        StatusBarUtil.fullScreen(this);
    }

}
