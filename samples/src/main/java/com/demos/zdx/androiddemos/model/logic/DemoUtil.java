package com.demos.zdx.androiddemos.model.logic;

import android.util.Log;

import com.zdx.libs.app.ApplicationManager;

/**
 * Describe:
 * Created by ${ZhaoDongXu}
 * Since Date 16/7/25.
 * Since Version :
 */
public class DemoUtil {

    public static  String getStringValue(int id){
        Log.v("zdxtest","  "+(ApplicationManager.getContext() == null));
        return ApplicationManager.getInstance().getString(id);
    }
}
