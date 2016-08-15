package com.demos.zdx.androiddemos.view.view.surfaceframeview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.io.InputStream;

/**
 * Describe:
 * Created by ${ZhaoDongXu}
 * Since Date 16/8/11.
 * Since Version :
 */
public class SurfaceFrameView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    public static final String TAG = "zdxtest";
    private SurfaceHolder mSurfaceHolder;

    private boolean mIsThreadRunning = true; // 线程运行开关
    private boolean mIsDestroy = false;// 是否已经销毁

    private int[] mBitmapResourceIds;// 用于播放动画的图片资源数组
    private Canvas mCanvas;
    private Bitmap mBitmap;// 显示的图片
    private Bitmap mNextBitmap;// 显示的图片

    private int mCurrentIndext;// 当前动画播放的位置
    private int mGapTime = 1000 / 24;// 每帧动画持续存在的时间

    private OnFrameFinishedListener mOnFrameFinishedListener;// 动画监听事件

    private Context context;
    RectF rectF;

    public SurfaceFrameView(Context context) {
        this(context, null);
    }

    public SurfaceFrameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        mCurrentIndext = 0;
        rectF = new RectF(0, 0, getScreenWidth(), getScreenHeight() - dp2px(55));
        mSurfaceHolder = this.getHolder();
        mSurfaceHolder.addCallback(this);// 注册回调方法
        mSurfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
    }

    public SurfaceFrameView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // 创建surfaceView时启动线程
        // new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // 当surfaceView销毁时, 停止线程的运行. 避免surfaceView销毁了线程还在运行而报错.
        mIsThreadRunning = false;
        try {
            Thread.sleep(mGapTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mIsDestroy = true;
    }

    int preLoadTime;

    /**
     * 制图方法
     */
    private void drawView() {
//            long start2 = System.currentTimeMillis();
        // 无资源文件退出
        if (mBitmapResourceIds == null) {
            Log.e(TAG, "the bitmapsrcIDs is null");
            mIsThreadRunning = false;
            return;
        }

        // 锁定画布
        mCanvas = mSurfaceHolder.lockCanvas();
        try {
            if (mSurfaceHolder != null && mCanvas != null) {
                mBitmap = mNextBitmap;
                mCanvas.drawBitmap(mBitmap, null, rectF, null);
                // 播放到最后一张图片，停止线程
                if (mCurrentIndext == mBitmapResourceIds.length - 1) {
//                        mIsThreadRunning = false;
                    mCurrentIndext = 0;
                }
                //获取预加载图片的时间
//                    if(startShowTime!=0){
                preLoadTime = (int) (System.currentTimeMillis() - startShowTime);
                Log.v(TAG, "  预加载及渲染时间  " + preLoadTime);
                if (preLoadTime < mGapTime) {
                    Log.v(TAG, "   sleep time  -> " + (mGapTime - preLoadTime));
                    Thread.sleep(mGapTime - preLoadTime);
                } else {
                    Log.v(TAG, "   不需要 sleep  ");
                }
//                    }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mCurrentIndext++;

            if (mCanvas != null) {
                // 将画布解锁并显示在屏幕上
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }

            if (mBitmap != null) {
                // 收回图片
                mBitmap.recycle();
                mNextBitmap = null;
            }
            //标记缓存图片的时间
            startShowTime = System.currentTimeMillis();
            preLoadBitmap();
            Log.v(TAG, " 预加载时间 " + (System.currentTimeMillis() - startShowTime));
//                Log.d(TAG," index->"+mCurrentIndext+ "绘制时间  " + (System.currentTimeMillis() - start2));
        }
    }

    private long startShowTime = 0;

    @Override
    public void run() {
        if (mOnFrameFinishedListener != null) {
            mOnFrameFinishedListener.onStart();
        }

        while (mIsThreadRunning) {
            drawView();
        }

        if (mOnFrameFinishedListener != null) {
            mOnFrameFinishedListener.onStop();
        }
    }

    /**
     * 预加载bitmap
     */
    private void preLoadBitmap() {
        if (mCurrentIndext < mBitmapResourceIds.length && (mNextBitmap == null)) {
            TypedValue value = new TypedValue();
            InputStream in = context.getResources().openRawResource(mBitmapResourceIds[mCurrentIndext], value);
            mNextBitmap = BitmapFactory.decodeStream(in, null, null);
            Log.v(TAG, "  size-> " + mNextBitmap.getByteCount() / 1024 + "k ");
        }
    }

    /**
     * 开始动画
     */
    public void start() {
        if (!mIsDestroy) {
            mIsThreadRunning = true;
            preLoadBitmap();
            new Thread(this).start();
        } else {
            // 如果SurfaceHolder已经销毁抛出该异常
            try {
                throw new Exception("IllegalArgumentException:Are you sure the SurfaceHolder is not destroyed");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 设置动画播放素材
     * 图片资源id
     */
    public void setBitmapResoursID(int[] bitmapResourceIds) {
        this.mBitmapResourceIds = bitmapResourceIds;
    }

    /**
     * 设置每帧时间
     */
    public void setGapTime(int gapTime) {
        this.mGapTime = gapTime;
    }

    /**
     * 结束动画
     */
    public void stop() {
        mIsThreadRunning = false;
    }

    /**
     * 继续动画
     */
    public void reStart() {
        mIsThreadRunning = true;
    }

    /**
     * 设置动画监听器
     */
    public void setOnFrameFinisedListener(OnFrameFinishedListener onFrameFinishedListener) {
        this.mOnFrameFinishedListener = onFrameFinishedListener;
    }

    /**
     * 动画监听器
     *
     * @author qike
     */
    public interface OnFrameFinishedListener {
        /**
         * 动画开始
         */
        void onStart();

        /**
         * 动画结束
         */
        void onStop();
    }

    /**
     * 当用户点击返回按钮时，停止线程，反转内存溢出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 当按返回键时，将线程停止，避免surfaceView销毁了,而线程还在运行而报错
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mIsThreadRunning = false;
        }

        return super.onKeyDown(keyCode, event);
    }

    private void decodeImage(InputStream in) {
        BitmapFactory.Options optionsTmp = new BitmapFactory.Options();
        BitmapFactory.decodeStream(in, null, optionsTmp);
        Log.v(TAG, " outW-> " + optionsTmp.outWidth + "  outH ->  " + optionsTmp.outHeight);

    }

    private Bitmap decodeResource(Resources resources, int id) {
        TypedValue value = new TypedValue();
        resources.openRawResource(id, value);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inTargetDensity = value.density;
        return BitmapFactory.decodeResource(resources, id, opts);
    }

    public void recolyBitmap() {
        if (mBitmap != null) {
            mBitmap.recycle();
        }
        if (mNextBitmap != null) {
            mNextBitmap.recycle();
        }
    }

    /**
     * 获得屏幕高度
     */
    public int getScreenWidth() {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕宽度
     */
    public int getScreenHeight() {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * dp转px
     */
    public int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, getResources().getDisplayMetrics());
    }
}
