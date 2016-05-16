package com.zdx.libs.network.interfaces;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;

/**
 * Describe:
 * Created by ZhaoDongXu on 16/5/13.
 * Since Version :
 */
public interface NetWorkCallback  {
    void onSuccess(JSONObject json) throws JSONException, SecurityException, IllegalArgumentException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException;

    void onError(JSONObject json);
}
