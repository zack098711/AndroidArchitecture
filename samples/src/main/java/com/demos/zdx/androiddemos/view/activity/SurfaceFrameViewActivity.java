package com.demos.zdx.androiddemos.view.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.demos.zdx.androiddemos.R;
import com.demos.zdx.androiddemos.presenter.BasePresenter;
import com.demos.zdx.androiddemos.view.view.surfaceframeview.SurfaceFrameAnimationView;
import com.demos.zdx.androiddemos.view.view.surfaceframeview.SurfaceFrameView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Describe:使用 surfaceview 展示GIF，双线程机制
 * Created by ${ZhaoDongXu}
 * Since Date 16/8/11.
 * Since Version :
 */
public class SurfaceFrameViewActivity extends BaseActivity{
    //动画资源文件
    int[] sourceId = new int[158];
    private SurfaceFrameView mSurfaceFrameView;
    private SurfaceFrameAnimationView surfaceFrameAnimationView;

    public static  void deleteFile(File file) {
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
            file.delete();
            Log.v("zdxtest4","   删除文件-> "+file.getPath().toString());
        } else {
            Log.v("zdxtest4","文件不存在！"+"\n");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String path = Environment.getExternalStorageDirectory().toString()+"/moji/dynamicBg/";
        File file = new File(path);
////        deleteFile(file);
        if(!file.exists()){
            file.mkdirs();
        }
        Log.v("zdx5"," b1-> "+file.getPath());
        Bitmap b1 = BitmapFactory.decodeResource(getResources(),R.drawable.mjdemo0000);
        Bitmap b2 = b1.copy(b1.getConfig(),true);
        Log.v("zdx5"," b1-> "+b1);
        Log.v("zdx5","  b2-> "+b2);
        b1.recycle();
        b1 = null;
        System.gc();
        Log.v("zdx5"," recycle  b1-> "+b1);
        Log.v("zdx5"," recycle  b2-> "+b2);

        mSurfaceFrameView = (SurfaceFrameView) findViewById(R.id.surfaceframeview);
        surfaceFrameAnimationView = (SurfaceFrameAnimationView)findViewById(R.id.SurfaceFrameAnimationView);
        if(mSurfaceFrameView !=null && mSurfaceFrameView.getVisibility() == View.VISIBLE) {

            for (int i = 0; i < 158; i++) {
                String name = "mjdemo0";
                if (i < 10) {
                    name = name + "00" + i;
                } else if (i < 100) {
                    name = name + "0" + i;
                } else if (i < 1000) {
                    name = name + i;
                }
                int id = getResources().getIdentifier(name, "drawable", this.getPackageName());
                sourceId[i] = id;
            }

            mSurfaceFrameView.setOnFrameFinisedListener(new SurfaceFrameView.OnFrameFinishedListener() {
                @Override
                public void onStart() {
                    Log.v(mSurfaceFrameView.TAG, " animation start ");
                }

                @Override
                public void onStop() {
                    Log.v(mSurfaceFrameView.TAG, " animation end ");
                }
            });
            mSurfaceFrameView.setBitmapResoursID(sourceId);
            mSurfaceFrameView.start();
        }

        if(surfaceFrameAnimationView.getVisibility() == View.VISIBLE){
            surfaceFrameAnimationView.start();
        }
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }
}
