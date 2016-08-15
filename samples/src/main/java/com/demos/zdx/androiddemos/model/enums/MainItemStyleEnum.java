package com.demos.zdx.androiddemos.model.enums;

/**
 * Describe:主目录 item 样式的标记
 * Created by ${ZhaoDongXu}
 * Since Date 16/7/25.
 * Since Version :1.0
 */
public enum MainItemStyleEnum {
    ITEM_STYLE_WITH_PIC(0),
    ITEM_STYLE_WITH_NO_PIC(1);
    public int mId;
    MainItemStyleEnum(int id){
        mId = id;
    }

    public static MainItemStyleEnum getItemStyleEnumById(int id){
        switch (id){
            case 0:
                return ITEM_STYLE_WITH_PIC;
            case 1:
                return ITEM_STYLE_WITH_NO_PIC;
            default:
                return null;
        }
    }
}
