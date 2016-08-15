package com.demos.zdx.androiddemos.view.view.exposure;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Describe:
 * Created by ${ZhaoDongXu}
 * Since Date 16/7/25.
 * Since Version :
 */
public class ExposureView extends View implements NestedScrollView.OnScrollChangeListener {
    public ExposureView(Context context){
        this(context,null);
    }

    public  ExposureView(Context context, AttributeSet attributeSet){
        this(context,attributeSet,0);
    }

    public ExposureView(Context context,AttributeSet attributeSet,int defStyle){
        super(context,attributeSet,defStyle);

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.v("zdxtest"," changed  l->  "+l+"   t-> "+t+"    oldl->"+oldl+"    oldt-> "+oldt);
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        Log.v("zdxtest1","   onscroll    scrollX-> "+scrollX+"   scrollY-> "+scrollY
                +"   oldScrollX->"+oldScrollX+"   oldScrollY -> "+oldScrollY);

    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        Log.v("zdxtest1", "   visibility changed  -> "+visibility);
    }
}
