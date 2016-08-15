package com.demos.zdx.androiddemos.model.dataprovider;

import com.demos.zdx.androiddemos.R;
import com.demos.zdx.androiddemos.model.bean.MainItemBean;
import com.demos.zdx.androiddemos.model.enums.MainItemFlagEnum;
import com.demos.zdx.androiddemos.model.enums.MainItemStyleEnum;
import com.demos.zdx.androiddemos.model.logic.DemoUtil;

import java.util.ArrayList;

/**
 * Describe: mainpage页面数据提供者
 * Created by ZhaoDongXu on 16/5/11.
 * Since Version :1.00
 */
public class MainListModel {
    ArrayList<MainItemBean> dataSource = new ArrayList<MainItemBean>();
    public ArrayList<MainItemBean> getListData(){
        //添加曝光统计的数据
        dataSource.add((new MainItemBean()).withTitle("ExposureView")
                .withDesc(DemoUtil.getStringValue(R.string.exposure_desc))
                .withItemStyleEnum(MainItemStyleEnum.ITEM_STYLE_WITH_NO_PIC)
                .withFlagEnum(MainItemFlagEnum.LIST_TYPE_EXPOSURE));
        dataSource.add(new MainItemBean().withTitle("surfaceview帧动画")
                .withDesc(DemoUtil.getStringValue(R.string.surfaceview_desc))
                .withItemStyleEnum(MainItemStyleEnum.ITEM_STYLE_WITH_NO_PIC)
                .withItemFlagEnum(MainItemFlagEnum.LIST_TYPE_SURFACEVIEW_GIF));
        return dataSource;
    }
}
