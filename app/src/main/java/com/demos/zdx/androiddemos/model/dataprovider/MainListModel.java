package com.demos.zdx.androiddemos.model.dataprovider;

import android.util.Log;

import com.demos.zdx.androiddemos.R;
import com.demos.zdx.androiddemos.model.bean.MainItemBean;

import java.util.ArrayList;
import java.util.Random;

/**
 * Describe: mainpage页面数据提供者
 * Created by ZhaoDongXu on 16/5/11.
 * Since Version :1.00
 */
public class MainListModel {
    ArrayList<MainItemBean> dataSource = new ArrayList<MainItemBean>();
    public ArrayList<MainItemBean> getListData(){
        Random random = new Random();
        Log.v("zdxtest1","   "+random.nextInt(10));
        int count = random.nextInt(10);
        for(int i = 0;i<count;i++){
            MainItemBean mainItemBean = new MainItemBean(R.mipmap.ic_launcher
                    ,"描述很长标题太短，写的再多也不会超出两行"
                    ,(i%2 == 0)?MainItemBean.STYLE_0:MainItemBean.STYLE_1,"item "+i);
            dataSource.add(mainItemBean);
        }
        return dataSource;
    }
}
