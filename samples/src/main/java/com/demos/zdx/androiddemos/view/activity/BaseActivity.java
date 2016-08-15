package com.demos.zdx.androiddemos.view.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.zdx.libs.app.ApplicationManager;
import com.demos.zdx.androiddemos.R;
import com.demos.zdx.androiddemos.model.config.BaseTitleViewConfig;
import com.demos.zdx.androiddemos.presenter.BasePresenter;
import com.demos.zdx.androiddemos.view.view.BaseTitleView;
import java.lang.ref.WeakReference;
import java.util.Locale;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Describe：activity 的基类，提供了默认 titlebar，也可设置定义 titlebar,基于 MVP 模式设计，可在实现子类时绑定 Presenter
 * Created by ZhaoDongXu on 16/5/9.
 * Since Version : 1.0
 */
public abstract class BaseActivity<ViewInterface,T extends BasePresenter<ViewInterface>> extends FragmentActivity{
    @BindView(R.id.fl_titleBar)
    public FrameLayout fl_titleBar;
    @BindView(R.id.fl_content)
    public FrameLayout fl_content;

    // 是否固定屏幕方向
    boolean flagScreenFixed = true;
    //是否显示TitleBar
    boolean flagTitleBar = true;
    //默认头部视图
    BaseTitleView baseTitleView;

    /**
     * 和页面绑定的presenter
     */
    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInstance(this);
        mPresenter = createPresenter();
        if(mPresenter!=null){
            mPresenter.attachToView((ViewInterface) this);
        }
        initBaseView();
        ButterKnife.bind(this);
    }

    /**
     * 获取绑定的presenter
     * @return
     */
    public abstract T createPresenter();

    /**
     * 解除绑定
     */
    @Override
    public void finish() {
        super.finish();
        if(mPresenter!=null){
            mPresenter.detachFromView();
            mPresenter = null;
        }
    }

    /**
     * 保存栈记录
     * @param instance
     */
    public static void setInstance(Activity instance){
        ApplicationManager.instanceRef = new WeakReference<Activity>(instance);
        ApplicationManager.taskStack.put(instance.getTaskId(),new WeakReference<Activity>(instance));
    }

    /**
     * 初始化基类视图
     */
    private void initBaseView(){
        setContentView(R.layout.baseactivity);
        fl_titleBar = (FrameLayout) findViewById(R.id.fl_titleBar);
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
        initBaseTitleView();
        initBaseContentView();
        if (flagScreenFixed) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    /**
     * 初始化默认 titleview
     */
    private  void initBaseTitleView(){
        if(this.flagTitleBar){
            //显示默认的titleview
            baseTitleView = new BaseTitleView(this,
                    new BaseTitleViewConfig().setLeftResId(R.mipmap.back_btn).setRightStr("确定"));
            fl_titleBar.setVisibility(View.VISIBLE);
            fl_titleBar.addView(baseTitleView);
        }else{
            //使用自定义 titleview ，或者titleview 不显示
            fl_titleBar.setVisibility(View.GONE);
        }
    }

    /**
     * 初始化 contentview
     */
    private void initBaseContentView(){
        fl_content.addView(getContentView());
    }

    /**
     * 设置自定义 titleview
     * @param customView
     */
    public void setCustomTitleView(View customView){
        fl_titleBar.removeAllViews();
        if (customView != null) {
            fl_titleBar.addView(customView);
        }
    }

    /**
     * 根据类名称获取布局文件
     * @return
     */
    public View getContentView(){
        Class<?> clazz = this.getClass();
        View view = null;
        while (true) {
            try {
                view = (View) LayoutInflater.from(this).inflate(
                        R.layout.class.getField(clazz.getSimpleName().toLowerCase(Locale.getDefault()))
                                .getInt(R.layout.class), null);
                break;
            } catch (Exception e) {
                e.printStackTrace();
                if (clazz.getClass().equals(Activity.class)){
                    break;
                } else{
                    clazz = clazz.getSuperclass();
                }
            }
        }
        if(view !=null){
            Log.v("zdxtest","  "+view.getClass().getName());
        }
        return view;
    }

    public BaseTitleView getBaseTitleView(){
        return baseTitleView;
    }

}
