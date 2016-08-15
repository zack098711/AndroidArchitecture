package com.demos.zdx.androiddemos.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.demos.zdx.androiddemos.model.bean.ExposureItemBean;
import com.demos.zdx.androiddemos.model.enums.ExposureItemStyleEnum;
import com.demos.zdx.androiddemos.presenter.interfaces.IExposureInterface;
import com.demos.zdx.androiddemos.view.fragment.ExposureFragment1;

import java.util.ArrayList;

/**
 * Describe:
 * Created by ${ZhaoDongXu}
 * Since Date 16/7/25.
 * Since Version :
 */
public class ExposurePresenter extends BasePresenter<IExposureInterface> {
    private IExposureInterface iExposureInterface;
    public ExposurePresenter(IExposureInterface iExposureInterface){
        this.iExposureInterface = iExposureInterface;
    }

    public void setFragments(Context context){
        ArrayList<ExposureFragment1> fragmentsArray = new ArrayList<>();
        ArrayList<ExposureItemBean> exposureItemBeens = new ArrayList<>();
        for(int i = 0 ;i<10;i++){
            ExposureItemBean exposureItemBean = new ExposureItemBean();
            if(i==5){
                exposureItemBean.mStyleEnum = ExposureItemStyleEnum.EXPOSURE_ITEM_STYLE_TEST;
            }else{
                exposureItemBean.mStyleEnum = ExposureItemStyleEnum.EXPOSURE_ITEM_STYLE_NORMAL;
            }
            exposureItemBeens.add(exposureItemBean);
        }
        ExposureFragment1 exposureFragment1 = new ExposureFragment1(context,exposureItemBeens);
        fragmentsArray.add(exposureFragment1);
        this.iExposureInterface.setFragment(fragmentsArray);
    }

}
