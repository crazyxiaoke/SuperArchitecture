package com.app.lib_common.http;

import com.app.lib_common.base.BaseView;

import java.io.IOError;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

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
 * Created by zxk on 18-8-9.
 */
public abstract class BaseObserver<T> implements Observer<HttpResult<T>> {
    private final int RESPONSE_CODE_OK = 0;

    private BaseView view;
    private boolean showProcess;

    public BaseObserver(BaseView view) {
        this(view, false);
    }

    public BaseObserver(BaseView view, boolean showProcess) {
        this.view = view;
        this.showProcess = showProcess; //是否显示加载dialog
        if (showProcess) {
            view.showProcessDialog();
        }
    }

    /**
     * 重写onSuccess方法，处理业务逻辑
     *
     * @param t
     */
    public abstract void onSuccess(T t);

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(HttpResult<T> httpResult) {
        if (httpResult.code == RESPONSE_CODE_OK) {
            onSuccess(httpResult.result);
        } else {
            onFailure(httpResult.code, httpResult.msg);
        }
    }

    @Override
    public void onError(Throwable e) {
        int code = -1;
        String errorMsg = "未知错误";
        if (e instanceof HttpException) {
            code = ((HttpException) e).code();
            errorMsg = ((HttpException) e).getMessage();
        } else if (e instanceof SocketTimeoutException) {
            code = ErrorCode.TIME_OUT;
            errorMsg = "服务器响应超时";
        } else if (e instanceof ConnectException) {
            code = ErrorCode.CONNECT_ERROR;
            errorMsg = "网络连接异常，请检查网络";
        } else if (e instanceof RuntimeException) {
            code = ErrorCode.RUNTIME_ERROR;
            errorMsg = "运行时错误";
        } else if (e instanceof UnknownHostException) {
            code = ErrorCode.UNKNOWHOST_ERROR;
            errorMsg = "无法解析主机，请检查网络连接";
        } else if (e instanceof UnknownServiceException) {
            code = ErrorCode.UNKNOWSERVICE_ERROR;
            errorMsg = "未知的服务器错误";
        } else if (e instanceof IOException) {
            code = ErrorCode.IO_ERROR;
            errorMsg = "没有网络，请检查网络连接";
        }
        onFailure(code, errorMsg);
    }


    @Override
    public void onComplete() {
        if (showProcess) {
            view.hideProcessDialog();
        }
    }

    /**
     * 处理错误逻辑
     *
     * @param code
     * @param errormsg
     */
    public void onFailure(int code, String errormsg) {

    }
}
