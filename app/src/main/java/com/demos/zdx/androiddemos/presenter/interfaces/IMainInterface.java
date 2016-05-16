package com.demos.zdx.androiddemos.presenter.interfaces;

import com.demos.zdx.androiddemos.model.bean.MainItemBean;

import java.util.ArrayList;

/**
 * Describe: mainpage interfaces
 * Created by ZhaoDongXu on 16/5/11.
 * Since Version : 1.0
 */
public interface IMainInterface {
    void setAdapterData(ArrayList<MainItemBean> dataSource);
}
