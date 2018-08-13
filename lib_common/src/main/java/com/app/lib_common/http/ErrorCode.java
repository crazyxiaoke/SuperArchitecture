package com.app.lib_common.http;

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
public class ErrorCode {
    public static final int OK = 0;

    public static final int TIME_OUT = 10001; //超时

    public static final int CONNECT_ERROR = 10002;// 连接错误

    public static final int RUNTIME_ERROR = 10003; //运行时错误

    public static final int UNKNOWHOST_ERROR = 10004; //无法解析主机

    public static final int UNKNOWSERVICE_ERROR = 10005; //未知的服务器错误

    public static final int IO_ERROR = 10006; //读写错误


}
