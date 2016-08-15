package com.demos.zdx.androiddemos.view.activity;

import android.os.Bundle;
import android.util.Log;

import com.demos.zdx.androiddemos.R;
import com.demos.zdx.androiddemos.presenter.BasePresenter;
import com.demos.zdx.androiddemos.view.view.surfaceframeview.SurfaceFrameView;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSurfaceFrameView = (SurfaceFrameView) findViewById(R.id.surfaceframeview);
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

    @Override
    public BasePresenter createPresenter() {
        return null;
    }
}
