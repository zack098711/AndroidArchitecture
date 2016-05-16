package com.zdx.libs.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.SparseArray;

import java.lang.ref.WeakReference;

/**
 * TODO:
 * Created by zhaodongxu1 on 16/5/9.
 * Since Version :
 */
public class ApplicationManager extends Application {
    public static Context instance;
    /**
     * activity的弱引用
     */
    public static WeakReference<Activity> instanceRef;
    /**
     * 记录任务栈
     */
    public static SparseArray<WeakReference<Activity>> taskStack = new SparseArray<WeakReference<Activity>>();

    ApplicationManager(){
        super();
    }

    @Override
    public void onCreate() {
        try {
            Class.forName("android.os.AsyncTask");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        super.onCreate();
        instance = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        try {
            super.attachBaseContext(base);
//            MultiDex.install(base);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized Context getContext(){
        return instance;
    }

    public static synchronized Context getInstance(){
        if(instanceRef == null || instanceRef.get() == null){
            return ApplicationManager.getContext();
        }else {
            return instanceRef.get();
        }
    }

    public static synchronized Activity getActivity(){
        Activity result = null;
        if(instanceRef!=null && instanceRef.get()!=null){
            result = instanceRef.get();
        }
        return result;
    }

    public static synchronized SparseArray<WeakReference<Activity>> getTaskStack(){
        return taskStack;
    }

}
