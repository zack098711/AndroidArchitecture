package com.demos.zdx.androiddemos.model.enums;

/**
 * Describe:定义视图类型的枚举变量
 * Created by ${ZhaoDongXu}
 * Since Date 16/7/25.
 * Since Version :1.0
 */
public enum MainItemFlagEnum {
    /**
     * 默认样式
     */
    LIST_TYPE_DEFAULT(0),
    /**
     * 测试具有自动统计曝光次数的自定义试图
     */
    LIST_TYPE_EXPOSURE(1),
    /**
     * 测试 surfaceview 双线程展示 GIF
     */
    LIST_TYPE_SURFACEVIEW_GIF(2),
    /**
     * 测试 textrueView展示 GIF 流
     */
    LIST_TYPE_TEXTRUEVIEW_GIF(3);
    public int mId;
    MainItemFlagEnum(int mId){
        this.mId = mId;
    }

    public static MainItemFlagEnum getTypeById(int mId){
        switch (mId){
            case 0:
                return LIST_TYPE_DEFAULT;
            case 1:
                return LIST_TYPE_EXPOSURE;
            case 2:
                return LIST_TYPE_SURFACEVIEW_GIF;
            default:
                return LIST_TYPE_DEFAULT;

        }
    }
}
