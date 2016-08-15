package com.zdx.libs.app;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Describe:app工具类
 * Created by ZhaoDongXu on 16/5/16.
 * Since Version :1.00
 */
public class AppUtils {
    /**
     * toast提醒
     * @param str
     */
    public static void alert(String str){
        try{
            Toast toast = Toast.makeText(ApplicationManager.getContext(), str, Toast.LENGTH_SHORT);
            toast.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获得屏幕高度
     */
    public static int getScreenWidth() {
        WindowManager wm = (WindowManager) ApplicationManager.getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕宽度
     */
    public static int getScreenHeight() {
        WindowManager wm = (WindowManager) ApplicationManager.getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }
}
