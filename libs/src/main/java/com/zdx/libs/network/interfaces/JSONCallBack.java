package com.zdx.libs.network.interfaces;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;

/**
 * Describe:获取json数据接口
 * Created by ZhaoDongXu on 16/5/13.
 * Since Version :1.00
 */
public interface JSONCallBack {
    void onSuccess(JSONObject json) throws JSONException, SecurityException, IllegalArgumentException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException;

    void onError(JSONObject json);
}
