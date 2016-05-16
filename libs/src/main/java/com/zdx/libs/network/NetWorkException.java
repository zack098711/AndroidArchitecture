package com.zdx.libs.network;

import android.util.Log;

import com.zdx.libs.config.AppInfo;

/**
 * Describe:
 * Created by ZhaoDongXu on 16/5/13.
 * Since Version :
 */
public class NetWorkException extends Exception {

    private static final long serialVersionUID = 1L;
    private Object object;
    private String url;
    private StringBuilder params;
    public String exceptionStr = "";
    /**
     * @param url
     *            异常的url
     * @param object
     *            异常信息
     */
    public NetWorkException(String url, StringBuilder stringBuilder, Object object) {
        this.url = url;
        this.params = stringBuilder;
        this.object = object;
    }

    @Override
    public void printStackTrace() {
        if (AppInfo.getDebug()) {
            Log.e("ServiceExceptionInfo:", "url:  "+url );
            Log.e("ServiceExceptionInfo:", "param:" + params);
            Log.e("ServiceExceptionInfo:", "info:" + object);
            if (!exceptionStr.equals("")) {
                Log.e("ServiceExceptionInfo:", exceptionStr);
            }
        }
        super.printStackTrace();
    }

}
