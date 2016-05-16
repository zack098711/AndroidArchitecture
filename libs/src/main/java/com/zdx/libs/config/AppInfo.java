package com.zdx.libs.config;

/**
 * Describe:
 * Created by ZhaoDongXu on 16/5/13.
 * Since Version :
 */
public class AppInfo {
    /**
     * 设置调试模式
     */
    private static boolean mDebug = true;
    public static boolean getDebug(){
        return mDebug;
    }

    public static void setDebug(boolean debug){
        mDebug = debug;
    }
}
