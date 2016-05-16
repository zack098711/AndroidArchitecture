package com.demos.zdx.androiddemos.presenter;

import java.lang.ref.WeakReference;
import rx.subscriptions.CompositeSubscription;

/**
 * TODO:presenter基类
 * Created by zhaodongxu1 on 16/5/11.
 * Since Version :1.0
 */
public class BasePresenter<T> {
    protected WeakReference<T> reference;
    protected CompositeSubscription compositeSubscription = new CompositeSubscription();

    /**
     * 绑定页面
     * @param view（activity fragment  or view）
     */
    public void attachToView(T view){
        reference = new WeakReference<>(view);
    }

    /**
     * 接触绑定
     */
    public void detachFromView(){
        compositeSubscription.unsubscribe();
        if(reference!=null){
            reference.clear();
            reference = null;
        }
    }
}

