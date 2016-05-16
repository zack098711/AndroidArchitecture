package com.demos.zdx.androiddemos.presenter;

import com.demos.zdx.androiddemos.model.dataprovider.MainListModel;
import com.demos.zdx.androiddemos.presenter.interfaces.IMainInterface;

import java.util.ArrayList;

/**
 * Describe: presenter for mainpage
 * Created by ZhaoDongXu on 16/5/11.
 * Since Version :1.0
 */
public class MainPagePresenter extends BasePresenter<IMainInterface> {
    private MainListModel mainListModel;
    private IMainInterface mainInterface;
    public MainPagePresenter(IMainInterface mainInterface){
        this.mainInterface = mainInterface;
        mainListModel = new MainListModel();
    }

    /**
     * 初始化数据
     */
    public void setAdapterData(){
        mainInterface.setAdapterData(mainListModel.getListData());
    }

}
